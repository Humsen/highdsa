package pers.husen.highdsa.common.utility;

/**
 * @Desc 测试时间格式转换
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 上午11:24:36
 * 
 * @Version 1.0.0
 */
public class TestDateFormat {
	public static void main(String[] args) {
		System.out.println(DateFormat.formatNowDate());
		System.out.println(DateFormat.formatNowDate("MM-dd-yyyy HH:mm:ss"));
		System.out.println(DateFormat.formatDateYMD());
		
		System.out.println(DateFormat.secondsTodayTotal());
		System.out.println((11 * 60 + 30) * 60 * 1000);
		System.out.println(DateFormat.dateNumberFormat());
	}
}