package com.esen.youngcms.core.utils.mail;

public class MailTest {
	
	public static void main(String[] args){   
     //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setToAddress("3019243@qq.com");    
     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
     //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
     sms.sendTextMail(mailInfo);//发送文体格式    
     sms.sendHtmlMail(mailInfo);//发送html格式   
   }  

}
