package com.esen.youngcms.core.utils.reptile;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *     
 * 项目名称：sp2    
 * 类名称：LinkQueue    
 * 类描述： 爬虫记录类   
 * 创建人：buyuer    
 * 创建时间：2015年4月21日 上午11:16:43      
 *
 */
public class LinkQueue {

    
    @SuppressWarnings("rawtypes")
    private static Set visitedUrl=new HashSet(); //已经访问的url
    
    private static Queue unVisitedUrl=new Queue();  //待访问的URL

    /**
     * getUnVisitedUrl(获取url队列)    
     * @return String   URL队列    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public static Queue getUnVisitedUrl() {
        return unVisitedUrl;
    }
    
    
    /**
     * setVisitedUrl(添加到访问过的url队列中)    
     * @param  访问过的url
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    @SuppressWarnings("unchecked")
    public static void setVisitedUrl(String visitedUrl) {
        LinkQueue.visitedUrl.add(visitedUrl);
    }
    
    /**
     * removeVisitedUrl(移除访问过的URL)    
     * @param   访问过的url 
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public static void  removeVisitedUrl(String url) {
        visitedUrl.remove(url);
    }
    
    /**
     * unVisitedUrlDeQueue(未访问的url出队列)    
     * @return String 未访问的URL   
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public static Object unVisitedUrlDeQueue() {
        return unVisitedUrl.deQueue();
    }
    
    /**
     * addUnvisitedUrl(确保每个url只能访问一次)    
     * @param   url 访问的url
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public static void  addUnvisitedUrl(String url) {
        if (StringUtils.isNotBlank(url) && !visitedUrl.contains(url) && !unVisitedUrl.contians(url)) {
            unVisitedUrl.enQueue(url);
        }
    }
    
    //获取已经访问的url数目
    public static  int  getVisitedUrlNum() {
        return visitedUrl.size();
    }
    
    //判断未访问的URL队列中是否为空
    public static boolean unVisitedUrlsEmpty() {
        return unVisitedUrl.empty();
    }
    
    //清空已经访问的队列
    public static  void  clearVisitedUrl() {
         visitedUrl.clear();
    }
    
    //清空未访问的队列
    public static void clearUnVisitedUrl() {
         unVisitedUrl.clearQueue();
    }
    
    
}
