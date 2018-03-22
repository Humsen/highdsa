package pers.husen.highdsa.email.main;

import pers.husen.highdsa.service.email.AttachHtmlEmailImpl;

/**
 * @Desc 测试发送带有附件的邮件
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午8:34:37
 * 
 * @Version 1.0.0
 */
public class SendAttachmentEmail {
	public static void main(String[] args) {
		AttachHtmlEmailImpl se = new AttachHtmlEmailImpl();
		String filePath = "F:\\workspace\\workspace bishe\\highdsa\\pom.xml";
		// se.sendEmail2User("husen@hemingsheng.cn", "邮件主题", "邮件内容", file);//
		se.sendEmail2Admin("何明胜", "1123767053@qq.com", "18626422426", "测试发送附件给管理员，带附件", filePath);
	}
}