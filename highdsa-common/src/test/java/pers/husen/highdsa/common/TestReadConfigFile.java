package pers.husen.highdsa.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.utils.ReadConfigFile;

/**
 * @Desc 测试读取配置文件
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 上午10:20:42
 * 
 * @Version 1.0.0
 */
public class TestReadConfigFile {
	public static void main(String[] args) {
		try {
			String relativePath = "redis.properties";
			String classPath = "pers.husen.highdsa.common.utils.redis";
			String absolutePath = "F:\\Eclipse\\workspaces\\workspace bishe\\highdsa\\highdsa-common\\src\\main\\java\\redis.properties";
			
			Properties properties1 = ReadConfigFile.readByRelativePath(relativePath);
			System.out.println(properties1.getProperty("redis.name"));
			
			ResourceBundle properties2 = ReadConfigFile.readByClassPath(classPath);
			System.out.println(Encode.iso2UTF8(properties2.getString("redis.name")));
			
			Properties properties3 = ReadConfigFile.readByAbsolutePath(absolutePath);
			System.out.println(properties3.getProperty("redis.name"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}