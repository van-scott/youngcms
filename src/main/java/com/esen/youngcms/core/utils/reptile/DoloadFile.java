package com.esen.youngcms.core.utils.reptile;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;




/**
 * 项目名称：sp2    
 * 类名称：DoloadFile    
 * 类描述：   网页下载的工具类 
 * 创建人：buyuer    
 * 创建时间：2015年4月21日 上午11:34:06      
 */
public class DoloadFile {
    /**
     * getFileNameByUrl(根据url和网页生成需要保存的网页的文件名，去除url的非文件名字符)    
     * @param   url：需要访问的url
     * @param contentTyle 网页类型   
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public String  getFileNameByUrl(String url,String contentType) {
        //移除HTTP
        url=url.substring(7);
        
        //text/html类型
        if (contentType.indexOf("html")!=-1) {
            url=url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
            return url; 
        }
        //application/pdf 
        else {
            return url.replaceAll("[\\?/:*|<>\"]", "_")+contentType.substring(contentType.lastIndexOf("/")+1);
        }
    }
    
    /**
     * saveToLocal(保存网页字节数组到本地文件中)    
     * @param   filePath 要保存的文件地址   
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public void saveToLocal(byte[] data,String filePath) {
        DataOutputStream out=null;
        try {
           File file= new File(filePath);
           if (!file.exists()) {
             file.createNewFile();
           }
             out=new DataOutputStream(new FileOutputStream(file));
            for (int i = 0; i < data.length; i++) {
                out.write(data[i]);
                out.flush();
              
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    @SuppressWarnings("deprecation")
    public String  downloadFile(String url) {
        String filePath=null;
        //生成httpclient
        HttpClient client =new HttpClient();
        client.getHttpConnectionManager().getParams();  
        client.setConnectionTimeout(5000);
        
        //获取method 并且设置相关参数
        GetMethod getMethod=new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        
        try {
            int statusCode=client.executeMethod(getMethod);
            //如果请求状态码不为OK 则抛出异常
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception("Method failed !" +getMethod.getStatusLine());
            }
            byte[] responseBody=getMethod.getResponseBody(); //读取为字节数组
            //根据网页信息生成保存的
            filePath= "d:\\temp\\"+getFileNameByUrl(url, getMethod.getResponseHeader("Content-Type").getValue());
            saveToLocal(responseBody, filePath);
        } catch (HttpException e) {
            System.out.println("请检查地址信息");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发生网络异常");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            getMethod.releaseConnection();
        }
        return filePath;
        
    }
    
}
