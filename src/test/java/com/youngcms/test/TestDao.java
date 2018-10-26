package com.
youngcms.test;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.junit.Test;

public class TestDao {
	
	/*@Test
	public void test(){
			String[] ctxFileName = new String[] {"resource/applicationContext-hibernate.xml", "resource/applicationContext.xml" };
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileName);
			UserDao userDao =  (UserDao) ctx.getBean("userDaoImpl");
			User user=new User("fumiao", 24);
			userDao.save(user);
	}
	@Test
	public void test1(){
		File file=new File(Constants.CODE_BEAN_PATH);
		File[] files=file.listFiles();
		List<CodeVO> list=new ArrayList<CodeVO>();
		for(int i=0;i<files.length;i++){
			String fileName=files[i].getName();
			int len=fileName.lastIndexOf(".");
			String SimpleFileName=fileName.substring(0, len);
			String serviceName=SimpleFileName+"Service.java";
			String serviceImplName=SimpleFileName+"ServiceImpl.java";
			String daoName=SimpleFileName+"Dao.java";
			String daoImplName=SimpleFileName+"DaoImpl.java";
			File serviceFile=new File(Constants.CODE_SERVICE_PATH+serviceName);
			File serviceImplFile=new File(Constants.CODE_SERVICE_PATH+"impl\\"+serviceImplName);
			File daoFile=new File(Constants.CODE_DAO_PATH+daoName);
			File daoImplFile=new File(Constants.CODE_DAO_PATH+"impl\\"+daoImplName);
			CodeVO codeVO=new CodeVO();
			codeVO.setClazzName(SimpleFileName);
			try {
				if(serviceFile.exists()){
					codeVO.setServiceExist(1);
				}
				if(serviceImplFile.exists()){
					codeVO.setServiceImplExist(1);
				}
				if(daoFile.exists()){
					codeVO.setDaoExist(1);
				}
				if(daoImplFile.exists()){
					codeVO.setDaoImplExist(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			System.out.println(codeVO.toString());
			list.add(codeVO);
		}
	}
	
	@Test
	public void test2() {
		try {
			Class clazz=  Class.forName("com.esen.youngcms.bean.User");
			Field[] fields=clazz.getDeclaredFields();
			for (int i=0;i<fields.length;i++){
				System.out.println(fields[i].getName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
*/
 @Test
 public void test(){
	 try {
		 File file=new File("D:\\project\\youngcms\\data\\tpl\\default\\channel\\test.txt");
		  if(!file.exists()){
			  file.createNewFile(); 
		  }
		  String content="付苗11111111111111";
		  //FileOutputStream  o = new FileOutputStream(file);  
		  FileWriter fw=new FileWriter(file);
		  BufferedWriter bf=new BufferedWriter(fw);
		  bf.write(content);
		  bf.flush();
		  bf.close();
	 }catch (Exception e) {
		// TODO: handle exception
	}
  }
}
