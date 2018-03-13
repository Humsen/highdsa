package pers.husen.highdsa.web.fastdfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.fastdfs.Fastdfs;

/**
 * @Desc 文件存储服务 业务调用
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:41:55
 * 
 * @Version 1.0.0
 */
@Service
public class FastdfsSvc {
	private final Logger logger = LogManager.getLogger(FastdfsSvc.class.getName());
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private Fastdfs fastDFS;
	
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		Map<String, String> replyMap = new HashMap<>(20);
		
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return null;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					logger.info("普通表单（暂不处理）：" + name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);

					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();

					replyMap = fastDFS.uploadFile(getFileBuffer(in), filename);

					// 关闭输入流
					in.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					logger.info("上传文件名称：" + filename);
					
					return objectMapper.writeValueAsString(replyMap);
				}
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		return objectMapper.writeValueAsString(replyMap);
	}
	
	/**
	 * 将文件输入流转换成字节数组
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	private byte[] getFileBuffer(InputStream inStream) throws IOException {
		byte[] buffer = new byte[256 * 1024];
		ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();

		int length = 0;

		while ((length = inStream.read(buffer)) != -1) {
			bOutputStream.write(buffer, 0, length);
		}

		bOutputStream.close();

		return bOutputStream.toByteArray();
	}
}