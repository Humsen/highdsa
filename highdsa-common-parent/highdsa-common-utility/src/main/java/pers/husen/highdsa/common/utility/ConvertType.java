package pers.husen.highdsa.common.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.exception.StackTrace2Str;
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

	/**
	 * 序列化对象
	 * 
	 * @param object
	 * @return 字节数组
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;

		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			byte[] byteArray = byteArrayOutputStream.toByteArray();

			logger.info("序列化成功, 结果字节数组长度={}", byteArray.length);

			return byteArray;
		} catch (IOException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("序列化失败", e));
		}
		return null;
	}

	/**
	 * 反序列化成对象
	 * 
	 * @param byteArray
	 * @return 对象
	 */
	public static Object unserialize(byte[] byteArray) {
		ObjectInputStream objectInputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;

		if (byteArray == null) {
			return null;
		}

		try {
			byteArrayInputStream = new ByteArrayInputStream(byteArray);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object object = objectInputStream.readObject();

			logger.info("反序列化成功, 结果为：{}", object.toString());

			return object;
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("反序列化失败", e));
		}

		return null;
	}

	/**
	 * 序列化 list 集合
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] serializeList(List<Object> list) {
		if (list.isEmpty()) {
			return null;
		}
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] byteArray = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : list) {
				oos.writeObject(obj);
			}
			byteArray = baos.toByteArray();
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("序列化失败", e));
		}

		logger.info("序列化成功, 结果字节数组长度={}", byteArray.length);

		return byteArray;
	}

	/**
	 * 反序列化 list 集合
	 * 
	 * @param
	 * @return
	 */
	public static List<Object> unserializeList(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		List<Object> list = new ArrayList<Object>();
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			while (bais.available() > 0) {
				Object obj = (Object) ois.readObject();
				if (obj == null) {
					break;
				}
				list.add(obj);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("反序列化List<Object>失败", e));
		}

		logger.info("反序列化成功, 结果为：{}", list.toString());

		return list;
	}

	/**
	 * 序列化 set 集合
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] serializeSet(Set<Object> set) {
		if (set.isEmpty()) {
			return null;
		}
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] byteArray = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : set) {
				oos.writeObject(obj);
			}
			byteArray = baos.toByteArray();
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("序列化失败", e));
		}

		logger.info("序列化成功, 结果字节数组长度={}", byteArray.length);

		return byteArray;
	}

	/**
	 * 反序列化 set 集合
	 * 
	 * @param lb
	 * @return
	 */
	public static Set<Object> unserializeSet(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		Set<Object> set = new HashSet<Object>();
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			while (bais.available() > 0) {
				Object obj = (Object) ois.readObject();
				if (obj == null) {
					break;
				}
				set.add(obj);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("反序列化Set<Object>失败", e));
		}

		logger.info("反序列化成功, 结果为：{}", set.toString());

		return set;
	}
}