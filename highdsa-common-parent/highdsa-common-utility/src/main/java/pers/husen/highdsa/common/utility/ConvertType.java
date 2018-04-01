package pers.husen.highdsa.common.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.exception.codec.CodecException;

/**
 * @Desc 类型转换
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月1日 下午1:01:47
 * 
 * @Version 1.0.2
 */
public class ConvertType {
	private static final Logger logger = LogManager.getLogger(ConvertType.class.getName());

	/**
	 * 字节数组转输入流
	 * 
	 * @param byteArray
	 * @return
	 */
	public static InputStream byteArray2InStream(byte[] byteArray) {
		return new ByteArrayInputStream(byteArray);
	}

	/**
	 * 输入流转字节数组
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] inStream2ByteArray(InputStream inStream) throws IOException {
		logger.fatal(inStream.available());
		byte[] buffer = new byte[256 * 1024];
		ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();

		int length = 0;

		while ((length = inStream.read(buffer)) != -1) {
			logger.fatal(length);
			bOutputStream.write(buffer, 0, length);
		}

		bOutputStream.close();

		return bOutputStream.toByteArray();
	}

	/**
	 * 字符串转字节数组, 使用工程默认编码
	 * 
	 * @param source
	 * @return
	 * @throws CodecException
	 */
	public static byte[] str2ByteArray(String source) throws CodecException {
		return str2ByteArray(source, Encode.DEFAULT_ENCODE);
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param source
	 * @param encoding
	 * @return
	 * @throws CodecException
	 */
	public static byte[] str2ByteArray(String source, String encoding) throws CodecException {
		try {
			return source.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			String msg = "Unable to convert source [" + source + "] to byte array using " + "encoding '" + encoding + "'";
			throw new CodecException(msg, e);
		}
	}

	/**
	 * 字符串数组转String
	 * 
	 * @param strArray
	 * @return
	 */
	public static String strArray2String(String[] strArray) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			stringBuffer.append(strArray[i]);
		}

		return stringBuffer.toString();
	}

	/**
	 * 字节数组转String
	 * 
	 * @param byteArray
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String byteArray2String(byte[] byteArray) throws UnsupportedEncodingException {
		return new String(byteArray, Encode.DEFAULT_ENCODE);
	}

	/**
	 * 判断是否为空
	 * 
	 * @param byteArray
	 * @return
	 */
	public static boolean isEmpty(byte[] byteArray) {
		return (byteArray == null || byteArray.length == 0);
	}
}