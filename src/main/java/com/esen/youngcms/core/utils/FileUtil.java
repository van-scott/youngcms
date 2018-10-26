package com.esen.youngcms.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.esen.youngcms.vo.Source;

public class FileUtil {
	
    private  int depth=0;
	
	private  List<Source> sources=new ArrayList<>();
	
	public  List<Source> readfile(String root,Source parent) {
	   	    depth++;
	        try {
	                File file = new File(root);
	                if (!file.isDirectory()) {
	               	 String fileName=file.getName();
	               	 String documentType=fileName.substring(fileName.indexOf(".")+1,fileName.length());
	               	 if("css".equals(documentType) || "html".equals(documentType) || "js".equals(documentType) || "text".equals(documentType)){
	               		 Source source=new Source();
	                   	 source.setId(sources.size()+1);
	                   	 source.setName(fileName);
	                   	 source.setpId(parent.getId());
	                   	 source.setParentPath(parent.getAbsolutePath());
	                     source.setParent(parent);
	                   	 source.setAbsolutePath(file.getPath());
	                   	 if(parent.getRelativePath()==null){
	                   		source.setRelativePath("/"+source.getName());
	                   	 }else{
	                   		source.setRelativePath(parent.getRelativePath()+"/"+source.getName());
	                   	 }
	                     if(depth==1){
	                		 source.setOpen(true);
	                	 }
	                   	 sources.add(source);
	               	 }
	                }else{
		                	 Source source=new Source();
		                	 source.setId(sources.size()+1);
		                	 source.setName(file.getName());
		                	 source.setpId(parent.getId());
		                	 source.setParentPath(parent.getAbsolutePath());
		                	 source.setParent(parent);
		                	 source.setAbsolutePath(file.getPath());
		                	 if(parent.getRelativePath()==null){
			                   		source.setRelativePath("/"+source.getName());
			                 }else{
			                   		source.setRelativePath(parent.getRelativePath()+"/"+source.getName());
			                 }
		                	 if(depth==1){
		                		 source.setOpen(true);
		                	 }
		                	 sources.add(source);
	                        String[] filelist = file.list();
	                        for (int i = 0; i < filelist.length; i++) {
	                                readfile(root + File.separator + filelist[i],source);
	                        }
	                }

	        } catch (Exception e) {
	        }
	        return sources;
	   }
	
	public  List<Source> readfile(Source parent,String[] excludeSuffix,String[] excludeName) {
        try {
        	    if(parent.getParent()==null){
        	    	sources.add(parent);
        	    }
                File file = new File(parent.getAbsolutePath());
                if (file.isDirectory()) {
                	 String[] filelist = file.list();
                     for (int i = 0; i < filelist.length; i++) {
                    	 File child=new File(parent.getAbsolutePath()+ File.separator + filelist[i]);
                    	 Source source=new Source();
	                	 source.setId(sources.size()+1);
	                	 source.setName(child.getName());
	                	 source.setpId(parent.getId());
	                	 source.setParentPath(parent.getAbsolutePath()+ File.separator + filelist[i]);
	                	 source.setParent(parent);
	                	 source.setAbsolutePath(parent.getAbsolutePath()+ File.separator + filelist[i]);
		                 source.setRelativePath(parent.getRelativePath()+"/"+source.getName());
		                 String documentType=child.getName().substring(child.getName().indexOf(".")+1,child.getName().length());
		                 boolean b=true;
		                 for(String s:excludeSuffix){
		                	 if(documentType.equals(s)){
		                		 b=false;
		                		 break;
		                	 }
		                 }
		                 boolean c=true;
		                 for(String s:excludeName){
		                	 if(child.getName().equals(s)){
		                		 c=false;
		                		 break;
		                	 }
		                 }
		                 if(b && c){
		                	 sources.add(source); 
		                 }
                         readfile(source,excludeSuffix,excludeName);
                     }
                }
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
        return sources;
   }
	
	public  void deleteAllFilesOfDir(File file) {  
	    if (!file.exists())  
	        return;  
	    if (file.isFile()) {  
	    	file.delete();  
	        return;  
	    }  
	    File[] files = file.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        deleteAllFilesOfDir(files[i]);  
	    }  
	    file.delete();  
	}  
	
}
