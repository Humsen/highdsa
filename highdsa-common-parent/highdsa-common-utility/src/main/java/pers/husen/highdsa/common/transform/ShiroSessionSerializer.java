package pers.husen.highdsa.common.transform;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

/**
 * @Desc shiro session 序列化工具类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午10:12:47
 * 
 * @Version 1.0.0
 */
public class ShiroSessionSerializer {
	/**
	 * 序列化shiro session
	 * 
	 * @param session
	 * @return
	 */
	public static String serialize(Session session) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(session);

			return Base64.encodeToString(bos.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("serialize session error", e);
		}
	}

	/**
	 * 反序列化shiro session
	 * 
	 * @param sessionStr
	 * @return
	 */
	public static Session deserialize(String sessionStr) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
			ObjectInputStream ois = new ObjectInputStream(bis);

			return (Session) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("deserialize session error", e);
		}
	}
}