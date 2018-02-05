package pers.husen.highdsa.test;

/**
 * @Desc    TODO
 *
 * @Author  何明胜
 *
 * @Created at 2018年2月5日 下午2:31:01
 * 
 * @Version 1.0.0
 */
public class SimpleTest {
	public static void main(String[] args) {
		String string = "husen@hemingsheng.cn";
		
		String[] result = string.trim().split(",|，");
		
		for(String str : result) {
			System.out.println(str);
		}
		
		String string2 = " ";
		System.out.println(string2 == " ");
	}
}
