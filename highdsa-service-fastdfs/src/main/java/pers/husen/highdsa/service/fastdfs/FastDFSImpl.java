package pers.husen.highdsa.service.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc fastdfs 文件存储
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月13日 下午2:58:20
 * 
 * @Version 1.0.0
 */
public class FastDFSImpl {
	private final Logger logger = LogManager.getLogger(FastDFSImpl.class.getName());

	public void init() {
		try {
			ClientGlobal.init("fdfs_client.conf");
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		/*
		 * 也可以使用代码设置配置 // 连接超时的时限 ClientGlobal.setG_connect_timeout(2); // 网络超时的时限，单位为秒
		 * ClientGlobal.setG_network_timeout(30);
		 * ClientGlobal.setG_anti_steal_token(false); // 字符集
		 * ClientGlobal.setG_charset("UTF-8"); ClientGlobal.setG_secret_key(null); //
		 * HTTP访问服务的端口号 ClientGlobal.setG_tracker_http_port(8088); // Tracker服务器列表
		 * InetSocketAddress[] tracker_servers = new InetSocketAddress[1];
		 * tracker_servers[0] = new InetSocketAddress("192.168.18.43", 22122);
		 * ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
		 */
		
		logger.info("初始化完成");
		logger.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
		logger.info("charset=" + ClientGlobal.g_charset);
	}

	/**
	 * 上传文件
	 */
	public String[] uploadFile(File file, String uploadFileName, long fileLength) throws IOException {
		logger.info("开始上传文件...");
		byte[] fileBuff = getFileBuffer(new FileInputStream(file), fileLength);
		String[] files = null;
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
		} else {
			logger.error("Fail to upload file, because the format of filename is illegal.");

			return null;
		}

		// 建立连接
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient client = new StorageClient(trackerServer, storageServer);

		// 设置元信息
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

		// 上传文件
		try {
			files = client.upload_file(fileBuff, fileExtName, metaList);
		} catch (Exception e) {
			logger.error("Upload file \" {} \"fails\n{}", uploadFileName, StackTrace2Str.exceptionStackTrace2Str(e));
		}
		
		if (files == null) {
			logger.error("upload file fail, error code: " + client.getErrorCode());
			return null;
		} else {
			logger.info("group_name: " + files[0] + ", remote_filename: " + files[1]);
			try {
				logger.info(client.get_file_info(files[0], files[1]));
			} catch (MyException e) {
				StackTrace2Str.exceptionStackTrace2Str(e);
			}

			ServerInfo[] servers = tracker.getFetchStorages(trackerServer, files[0], files[1]);
			if (servers == null) {
				logger.error("get storage servers fail, error code: " + tracker.getErrorCode());
			} else {
				logger.info("storage servers count: " + servers.length);
				for (int k = 0; k < servers.length; k++) {
					logger.info((k + 1) + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
				}
			}
		}
		
		if (files != null) {
			String file_id;
			int ts;
			String token = null;
			String file_url;
			InetSocketAddress inetSockAddr;

			String group_name = files[0];
			String remote_filename = files[1];
			file_id = group_name + StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR + remote_filename;

			inetSockAddr = trackerServer.getInetSocketAddress();
			file_url = "http://" + inetSockAddr.getAddress().getHostAddress();
			if (ClientGlobal.g_tracker_http_port != 80) {
				file_url += ":" + ClientGlobal.g_tracker_http_port;
			}
			file_url += "/" + file_id;
			if (ClientGlobal.g_anti_steal_token) {
				ts = (int) (System.currentTimeMillis() / 1000);
				try {
					token = ProtoCommon.getToken(file_id, ts, ClientGlobal.g_secret_key);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (MyException e) {
					e.printStackTrace();
				}
				file_url += "?token=" + token + "&ts=" + ts;
			}

			logger.info("file url: " + file_url);

		}
		trackerServer.close();

		return files;
	}

	private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {
		byte[] buffer = new byte[256 * 1024];
		byte[] fileBuffer = new byte[(int) fileLength];

		int count = 0;
		int length = 0;

		while ((length = inStream.read(buffer)) != -1) {
			for (int i = 0; i < length; ++i) {
				fileBuffer[count + i] = buffer[i];
			}
			count += length;
		}
		return fileBuffer;
	}

	// 下载文件
	public void downloadFile(String groupName, String filepath) throws Exception {
		logger.info("开始下载文件...");
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;

		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		byte[] b = storageClient.download_file(groupName, filepath);
		
		if(b == null) {
			logger.error("下载失败");
		}else {
			logger.info("下载成功, 文件大小: "+ b.length);
		}
		
		String fileName = filepath.substring(filepath.lastIndexOf(".") + 1);
		FileOutputStream out = new FileOutputStream("src/main/resources/result." +fileName);
		out.write(b);
		out.flush();
		out.close();
	}

	// 获取文件信息
	public void getFileInfo(String groupName, String filepath) throws Exception {
		logger.info("开始获取文件信息");
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;

		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		FileInfo fi = storageClient.get_file_info(groupName, filepath);
		logger.info("所在服务器地址: {}", fi.getSourceIpAddr());
		logger.info("文件大小: {}", fi.getFileSize());
		logger.info("文件创建时间: {}", fi.getCreateTimestamp());
		logger.info("文件CRC32 signature: {}", fi.getCrc32());
	}

	//获取文件Mate
	public void getFileMate(String groupName, String filepath) throws Exception {
		logger.info("开始获取文件meta");
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;

		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		NameValuePair nvps[] = storageClient.get_metadata(groupName, filepath);
		for (NameValuePair nvp : nvps) {
			logger.info(nvp.getName() + ":" + nvp.getValue());
		}
	}
	// 删除文件
	public void deleteFile(String groupName, String filepath) throws Exception {
		logger.info("开始删除文件");
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		int i = storageClient.delete_file(groupName, filepath);
		logger.info(i == 0 ? "删除成功" : "删除失败:" + i);
	}
}