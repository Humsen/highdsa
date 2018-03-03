package pers.husen.highdsa.service.fastdfs;

import java.io.IOException;

/**
 * @Desc FastDFS 文件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 上午9:23:39
 * 
 * @Version 1.0.0
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
	public String[] uploadFile(byte[] fileBuff, String uploadFileName) throws IOException;

	/**
	 * 下载文件
	 * 
	 * @param groupName
	 * @param filepath
	 * @throws Exception
	 */
	public void downloadFile(String groupName, String filepath) throws Exception;

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