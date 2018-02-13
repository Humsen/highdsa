package pers.husen.highdsa.service.fastdfs;

import java.io.File;

import pers.husen.highdsa.service.fastdfs.FastDFSImpl;

/**
 * @Desc 测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月13日 下午3:05:30
 * 
 * @Version 1.0.0
 */
public class Main {
	public static void main(String[] args) throws Exception {
		FastDFSImpl fastDFSImpl = new FastDFSImpl();
		// 加载配置文件的方式
		fastDFSImpl.init();

		File file = new File("src/main/resources/fdfs_client.conf");
		// 返回储存路径:group1 M00/00/00/wKhuW1Vmj6KAZ09pAAC9przUxEk788.jpg
		String[] files = fastDFSImpl.uploadFile(file, "fdfs_client.conf", file.length());

		// 下载文件
		//fastDFSImpl.downloadFile(files[0], files[1]);
		// 查看文件信息
		//fastDFSImpl.getFileInfo(files[0], files[1]);
		//fastDFSImpl.getFileMate(files[0], files[1]);
		fastDFSImpl.deleteFile(files[0], files[1]);
	}
}