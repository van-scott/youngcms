package com.esen.youngcms.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esen.youngcms.core.utils.CodeUtil;
import com.esen.youngcms.core.utils.Constants;
import com.esen.youngcms.service.CodeService;
import com.esen.youngcms.vo.CodeVO;
import com.esen.youngcms.vo.QueryResult;
@Service
public class CodeService  {

	public QueryResult<CodeVO> getBeanList() {
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
			list.add(codeVO);
		}
		QueryResult<CodeVO> queryResult=new QueryResult<CodeVO>();
		queryResult.setQueryResult(list);
		queryResult.setCount(list.size());
		return queryResult;
	}

	public void createDao(String clazzName) {
		String code=CodeUtil.getDaoCode(clazzName);
		String filePath=Constants.CODE_DAO_PATH+clazzName+"Dao.java";
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(code);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

	public void createDaoImpl(String clazzName) {
		String code=CodeUtil.getDaoImplCode(clazzName);
		String filePath=Constants.CODE_DAO_PATH+"impl\\"+clazzName+"DaoImpl.java";
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(code);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

	public void createService(String clazzName) {
		String code=CodeUtil.getServiceCode(clazzName);
		String filePath=Constants.CODE_SERVICE_PATH+clazzName+"Service.java";
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(code);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

	public void createServiceImpl(String clazzName) {
		String code=CodeUtil.getServiceImplCode(clazzName);
		String filePath=Constants.CODE_SERVICE_PATH+"impl\\"+clazzName+"ServiceImpl.java";
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(code);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

	public void createController(String clazzName) {
		String code=CodeUtil.getControllerCode(clazzName);
		String filePath=Constants.CODE_CONTROLLLER_PATH+clazzName+"Controller.java";
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(code);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		
	}

	public void createListView(String clazzName) {
		String code=CodeUtil.getListView(clazzName);
		String clazzLowerName=clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase());

		String dirPath=Constants.CODE_VIEW_PATH+clazzLowerName;
		String filePath=dirPath+"\\list.jsp";
		File dirFile=new File(dirPath);
		File file=new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(new String(code.getBytes("utf-8"),"GBK"));
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

	public void createFormView(String clazzName) {
		String code=CodeUtil.getFormView(clazzName);
		String clazzLowerName=clazzName.replaceFirst(clazzName.substring(0, 1),clazzName.substring(0, 1).toLowerCase());
		
		String dirPath=Constants.CODE_VIEW_PATH+clazzLowerName;
		String filePath=dirPath+"\\form.jsp";
		File dirFile=new File(dirPath);
		File file=new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		if(!file.exists()){
			try {
				file.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(new String(code.getBytes("utf-8"),"GBK"));
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
}