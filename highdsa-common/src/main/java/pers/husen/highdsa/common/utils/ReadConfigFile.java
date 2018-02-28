package pers.husen.highdsa.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import pers.husen.highdsa.common.constant.Encode;

/**
 * @Desc 读取配置文件工具类
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 上午10:10:33
 * 
 * @Version 1.0.0
 */
public class ReadConfigFile {
	/**
	 * 读取类路径下的文件（只要在类路径下即可）
	 * 
	 * @param fileRelativePath
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static Properties readByRelativePath(String fileRelativePath)
			throws UnsupportedEncodingException, IOException {
		Properties properties = new Properties();

		// 使用ClassLoader加载properties配置文件生成对应的输入流
		InputStream in = ReadConfigFile.class.getClassLoader().getResourceAsStream(fileRelativePath);
		// 使用properties对象加载输入流
		properties.load(new InputStreamReader(in, Encode.DEFAULT_ENCODE));

		return properties;
	}

	/**
	 * 读取类路径下的文件（需要指定相对类根目录路径）
	 * 
	 * @param fileRelativePath
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static ResourceBundle readByClassPath(String fileClassPath)
			throws UnsupportedEncodingException, IOException {
		// config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
		ResourceBundle resourceBundle = ResourceBundle.getBundle(fileClassPath);

		return resourceBundle;
	}

	/**
	 * 读取绝对路径下的文件
	 * 
	 * @param fileAbsolutePath
	 * @return
	 * @throws IOException
	 */
	public static Properties readByAbsolutePath(String fileAbsolutePath) throws IOException {
		Properties properties = new Properties();

		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileAbsolutePath),
				Encode.DEFAULT_ENCODE);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		// 使用InPutStream流读取properties文件
		properties.load(bufferedReader);

		return properties;
	}
}