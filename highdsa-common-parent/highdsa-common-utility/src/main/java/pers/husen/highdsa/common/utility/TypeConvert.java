package pers.husen.highdsa.common.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

/**
 * @Desc 类型转换
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月1日 下午1:01:47
 * 
 * @Version 1.0.0
 */
public class TypeConvert {
	private static final Logger logger = LogManager.getLogger(TypeConvert.class.getName());

	/**
	 * 序列化
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
	 * 反序列化
	 * 
	 * @param byteArray
	 * @return 对象
	 */
	public static Object unserialize(byte[] byteArray) {
		ObjectInputStream objectInputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;

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
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : list) {
				oos.writeObject(obj);
			}
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bytes;
	}

	/**
	 * 反序列化 list 集合
	 * 
	 * @param lb
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
			e.printStackTrace();
		} 
		
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
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : set) {
				oos.writeObject(obj);
			}
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bytes;
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
			e.printStackTrace();
		} 
		
		return set;
	}
}