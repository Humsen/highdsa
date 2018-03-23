package pers.husen.highdsa.email.main;

import java.io.File;

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
		AttachHtmlEmailImpl aImpl = new AttachHtmlEmailImpl();
		File file = new File("F:\\Eclipse\\workspaces\\workspace bishe\\shiro-demo\\pom.xml");
		aImpl.sendEmail2User("husen@hemingsheng.cn", "邮件主题", "邮件内容", file);
		
		String attachUrl = "https://www.hemingsheng.cn/file/download.hms?filename=%E5%88%A0%E9%99%A4%E2%80%9C%E8%BF%99%E5%8F%B0%E7%94%B5%E8%84%91%E2%80%9D%E4%B8%AD%E5%AF%BC%E8%88%AA%E6%A0%8F%E7%9A%84%E6%96%87%E6%A1%A3%E3%80%81%E4%B8%8B%E8%BD%BD%E3%80%81%E8%A7%86%E9%A2%91.reg";
		aImpl.sendEmail2User("husen@hemingsheng.cn", "邮件主题", "邮件内容", attachUrl, "删除.reg");
		//aImpl.sendEmail2Admin("何明胜", "1123767053@qq.com", "18626422426", "测试发送附件给管理员，带附件", file);
	}
}