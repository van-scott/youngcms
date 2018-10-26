package com.esen.youngcms.core.utils.reptile;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.esen.youngcms.bean.ReptileRule;
import com.esen.youngcms.service.ContentReptileService;

/**
 * 
 *     
 * 项目名称：sp2    
 * 类名称：MyCrawler    
 * 类描述：宽度爬虫测试类    
 * 创建人：buyuer    
 * 创建时间：2015年4月21日 下午3:08:33      
 *
 */
public class Reptile implements Runnable {

	private ReptileRule reptileRule;
	
	private ContentReptileService contentReptileService;
	
	public Reptile(ReptileRule reptileRule,ContentReptileService contentReptileService) {
		this.reptileRule = reptileRule;
		this.contentReptileService=contentReptileService;
	}
    /**
     * initCrawlerWithSeeds(初始化种子队列)    
     * @param seeds:种子url   
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    private void initCrawlerWithSeeds(String [] seeds){
        for (int i = 0; i < seeds.length; i++) {
            LinkQueue.addUnvisitedUrl(seeds[i]);
            
        }
    }
    
    /**
     * crawling(抓取过程)    
     * @param  seeds url种子 
     * @param rule 种子的规则
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public void  crawling() {
    	//清空访问列表
    	LinkQueue.clearVisitedUrl();
    	LinkQueue.clearUnVisitedUrl();
    	//过滤URL
        LinkFilter filter=new LinkFilter() {
            @Override
            public boolean accept(String url) {
            	 Pattern pattern=Pattern.compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*", Pattern.CASE_INSENSITIVE);
            	 if(pattern.matcher(url).matches()){
            		 return true;
            	 }
                return false;
            }
        };
        //初始化URL队列
        String[] rootUrl=StringUtils.split(reptileRule.getReptileUrl(), "\r\n");
        initCrawlerWithSeeds(rootUrl);
        int count=0;
        while (!LinkQueue.unVisitedUrlsEmpty() && count <=reptileRule.getNumber() && LinkQueue.getVisitedUrlNum() <= reptileRule.getNumber()*3 ) {
            String visitUrl=(String) LinkQueue.unVisitedUrlDeQueue();
            //判断URL是否为空
            if (StringUtils.isBlank(visitUrl)) {
                continue;
            }
            //解析html，获取有用信息
            if(JsoupTool.getContent(visitUrl,reptileRule,contentReptileService)){
            	count++;
            }
            //将访问过的url放入已经访问过的队列
            LinkQueue.setVisitedUrl(visitUrl);
            //提取下载网页中的URL,通过htmlparser
            //Set<String> links=HtmlParseTool.extracLinks(visitUrl, filter,reptileRule.getCoding());
            //提取下载网页中的URL,通过jsoup
            Set<String> links = JsoupTool.getUrls(filter, visitUrl);
            //将新的未访问的URL入队
            for (String link : links) {
                LinkQueue.addUnvisitedUrl(link);
            }
        }
    }
    
	@Override
	public void run() {
		try {
			this.crawling();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
}
