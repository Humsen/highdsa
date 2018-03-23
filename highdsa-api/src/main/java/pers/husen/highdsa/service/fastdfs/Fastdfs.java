package pers.husen.highdsa.service.fastdfs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Desc FastDFS 文件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 上午9:23:39
 * 
 * @Version 1.0.1
 */
public interface Fastdfs {
	/**
	 * 上传文件
	 * 
	 * @param fileBuff
	 * @param uploadFileName
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> uploadFile(byte[] fileBuff, String uploadFileName) throws IOException;

	/**
	 * 下载文件,保存到指定路径
	 * 
	 * @param groupName
	 * @param remoteFilenNme
	 * @param saveFilePath
	 * @throws Exception
	 */
	public void downloadFile(String groupName, String remoteFilenNme, String saveFilePath) throws Exception;

	/**
	 * 下载文件,返回输入流
	 * 
	 * @param groupName
	 * @param remoteFilenNme
	 * @return
	 * @throws Exception
	 */
	public InputStream downloadFile(String groupName, String remoteFilenNme) throws Exception;

	/**
	 * 删除文件
	 * 
	 * @param groupName
	 * @param filepath
	 * @throws Exception
	 */
	public void deleteFile(String groupName, String filepath) throws Exception;

	/**
	 * 文件保存信息
	 * 
	 * @param groupName
	 * @param filepath
	 * @throws Exception
	 */
	public void getFileInfo(String groupName, String filepath) throws Exception;

	/**
	 * 文件mate信息
	 * 
	 * @param groupName
	 * @param filepath
	 * @throws Exception
	 */
	public void getFileMate(String groupName, String filepath) throws Exception;
}