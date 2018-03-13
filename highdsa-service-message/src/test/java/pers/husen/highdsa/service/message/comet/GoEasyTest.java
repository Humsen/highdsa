package pers.husen.highdsa.service.message.comet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @Desc 测试go easy
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午2:16:01
 * 
 * @Version 1.0.0
 */
public class GoEasyTest {
	@Test
	public void testGoeasy() {
		GoEasyPushMsg goEasyPush = new GoEasyPushMsg();
		
		try {
			goEasyPush.publish("uuid", "测试goeasy");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}