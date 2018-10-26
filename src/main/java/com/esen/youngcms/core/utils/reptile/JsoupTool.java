package com.esen.youngcms.core.utils.reptile;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.esen.youngcms.bean.ContentReptile;
import com.esen.youngcms.bean.ReptileRule;
import com.esen.youngcms.service.ContentReptileService;

/**
 * 项目名称：sp2    
 * 类名称：HtmlParseTool    
 * 类描述： 提取html工具类   
 * 创建人：buyuer    
 * 创建时间：2015年4月21日 下午2:32:46      
 *
 */
public class JsoupTool {
	
    public static Boolean getContent(String url,ReptileRule reptileRule,ContentReptileService contentReptileService){
    	try {
    		Connection.Response response = Jsoup.connect(url).timeout(5000).execute();
    		if(response.statusCode()==200){
    			ContentReptile contentReptile=new ContentReptile();
    			Document doc=response.parse();
    			/**
    			 * 标题
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getTitleStart())){//通过正则匹配: <!-- 正文 -->([\\s\\S]*)<!-- seo标签描述 -->
    				String title="";
    				if(StringUtils.isNotBlank(reptileRule.getTitleStart()) && StringUtils.isNotBlank(reptileRule.getTitleEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getTitleStart()+"(.*)"+reptileRule.getTitleEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				title=matcher.group().replaceAll(reptileRule.getTitleStart(), "").replaceAll(reptileRule.getTitleEnd(), "");
            				if(StringUtils.isNotBlank(title)){
    	    					break;
    	    				}
            			}
    			    }else{//通过标签获取
    			    	String[] params=StringUtils.split(reptileRule.getTitleStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				title=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(title)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setTitle(title);
    			}
    			/**
    			 * 内容
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getContentStart())){
    				String content="";
    				if(StringUtils.isNotBlank(reptileRule.getContentStart()) && StringUtils.isNotBlank(reptileRule.getContentEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getContentStart()+"([\\s\\S]*)"+reptileRule.getContentEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				content=matcher.group().replaceAll(reptileRule.getContentStart(), "").replaceAll(reptileRule.getContentEnd(), "");
            				if(StringUtils.isNotBlank(content)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getContentStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				content=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(content)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setContent(content);
    			}
    			/**
    			 * 描述
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getDescriptionStart())){
    				String description="";
    				if(StringUtils.isNotBlank(reptileRule.getDescriptionStart()) && StringUtils.isNotBlank(reptileRule.getDescriptionEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getDescriptionStart()+"(.*)"+reptileRule.getDescriptionEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				description=matcher.group().replaceAll(reptileRule.getDescriptionStart(), "").replaceAll(reptileRule.getDescriptionEnd(), "");
            				if(StringUtils.isNotBlank(description)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getDescriptionStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				description=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(description)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setDescription(description);
    			}
    			
    			/**
    			 * keywords
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getKeywordsStart())){
    				String keywords="";
    				if(StringUtils.isNotBlank(reptileRule.getKeywordsStart()) && StringUtils.isNotBlank(reptileRule.getKeywordsEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getKeywordsStart()+"(.*)"+reptileRule.getKeywordsEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				keywords=matcher.group().replaceAll(reptileRule.getKeywordsStart(), "").replaceAll(reptileRule.getKeywordsEnd(), "");
            				if(StringUtils.isNotBlank(keywords)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getKeywordsStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				keywords=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(keywords)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setKeywords(keywords);
    			}
    			
    			/**
    			 * 来源
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getSourceStart())){
    				String source="";
    				if(StringUtils.isNotBlank(reptileRule.getSourceStart()) && StringUtils.isNotBlank(reptileRule.getSourceEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getSourceStart()+"(.*)"+reptileRule.getSourceEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				source=matcher.group().replaceAll(reptileRule.getSourceStart(), "").replaceAll(reptileRule.getSourceEnd(), "");
            				if(StringUtils.isNotBlank(source)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getSourceStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				source=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(source)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setSource(source);
    			}
    			
    			/**
    			 * 作者
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getAuthorStart())){
    				String author="";
    				if(StringUtils.isNotBlank(reptileRule.getAuthorStart()) && StringUtils.isNotBlank(reptileRule.getAuthorEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getAuthorStart()+"(.*)"+reptileRule.getAuthorEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				author=matcher.group().replaceAll(reptileRule.getAuthorStart(), "").replaceAll(reptileRule.getAuthorEnd(), "");
            				if(StringUtils.isNotBlank(author)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getAuthorStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				author=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(author)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setAuthor(author);
    			}
    			
    			/**
    			 * 发布时间
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getPublishTimeStart())){
    				String publishTime="";
    				if(StringUtils.isNotBlank(reptileRule.getPublishTimeStart()) && StringUtils.isNotBlank(reptileRule.getPublishTimeEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getPublishTimeStart()+"(.*)"+reptileRule.getPublishTimeEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				publishTime=matcher.group().replaceAll(reptileRule.getPublishTimeStart(), "").replaceAll(reptileRule.getPublishTimeEnd(), "");
            				if(StringUtils.isNotBlank(publishTime)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getPublishTimeStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				publishTime=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(publishTime)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setPublishTime(publishTime);
    			}
    			/**
    			 * 阅读人数
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getReadNumberStart())){
    				String readNumber="0";
    				if(StringUtils.isNotBlank(reptileRule.getReadNumberStart()) && StringUtils.isNotBlank(reptileRule.getReadNumberEnd())){
        				Pattern pattern = Pattern.compile(""+reptileRule.getReadNumberStart()+"(.*)"+reptileRule.getReadNumberEnd()+"");
            			Matcher matcher=pattern.matcher(doc.html());
            			while(matcher.find()){
            				readNumber=matcher.group().replaceAll(reptileRule.getReadNumberStart(), "").replaceAll(reptileRule.getReadNumberEnd(), "");
            				if(StringUtils.isNotBlank(readNumber)){
    	    					break;
    	    				}
            			}
    			    }else{
    			    	String[] params=StringUtils.split(reptileRule.getReadNumberStart(), "=");
    			    	Elements elements=doc.getElementsByAttributeValue(params[0], params[1]);
    	    			for(int i=0 ;i<elements.size();i++){
    	    				readNumber=elements.get(i).html();
    	    				if(StringUtils.isNotBlank(readNumber)){
    	    					break;
    	    				}
    	    			}
    			    }
    				contentReptile.setReadNumber(Integer.valueOf(readNumber));
    			}
    			if(StringUtils.isNotBlank(contentReptile.getContent())){
    				contentReptile.setCreateTime(new Date());
    				contentReptile.setStatus(1);
    				contentReptileService.insert(contentReptile);
    				return true;
				}
    		}
		} catch (IOException e) {
			System.out.println("HTML解析失败");
		}
		return false;
    }
    
    public static Set<String> getUrls(LinkFilter filter,String url){
    	Set<String > urls=new HashSet<String>();
    	try {
    		Connection.Response response = Jsoup.connect(url).timeout(5000).execute();
    		if(response.statusCode()==200){
	    		Document doc=response.parse();
	            Elements links = doc.select("a[href]");
	            Elements media = doc.select("[src]");
	            Elements imports = doc.select("link[href]");
	           /* print("\nMedia: (%d)", media.size());
	            for (Element src : media) {
	                if (src.tagName().equals("img"))
	                    print(" * %s: <%s> %sx%s (%s)",
	                            src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	                            trim(src.attr("alt"), 20));
	                else
	                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
	            }
	
	            print("\nImports: (%d)", imports.size());
	            for (Element link : imports) {
	                print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
	            }*/
	
	            //print("\nLinks: (%d)", links.size());
	            for (Element link : links) {
	                //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
	                if (filter.accept(link.attr("abs:href"))) {
	                    urls.add(link.attr("abs:href"));
	                }
	            }
    		}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return urls;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    public static void main(String[] args) {
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
        try {
			Set<String> set=getUrls(filter, "http://news.qq.com/world_index.shtml");
			for(String s:set){
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
