package com.esen.youngcms.core.utils.reptile;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

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
public class HtmlParseTool {

    public static Set<String> extracLinks(String url,LinkFilter filter,String coding) {
        Set<String > links=new HashSet<String>();
        Parser parser=null;
        try {
        	    parser=new Parser(url);
                parser.setEncoding(coding);
                //过滤<frame> 标签的filtr,用来提取frame标签里的src属性
                @SuppressWarnings("serial")
                NodeFilter frameFilter=new NodeFilter() {
                    @Override
                    public boolean accept(Node node) {
                        if (node.getText().startsWith("frame src=")) {
                            return true;
                        }
                        return false;
                    }
                };
                //OfFilter来设置过滤<a>标签和<frame>标签
                OrFilter linkFilter=new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
                //得到所有经过过滤的标签
                NodeList list=parser.extractAllNodesThatMatch(linkFilter);
                for (int i = 0; i < list.size(); i++) {
                   Node tag=list.elementAt(i);
                   if (tag instanceof LinkTag) { //<a> 标签
                       LinkTag link=(LinkTag) tag;
                       String linkUrl=link.getLink(); //URL
                       if (filter.accept(linkUrl)) 
                        links.add(linkUrl);
                     }else {
                         //提取Frame里面的SRC属性的连接，<frame src="text.html">
                        String frame=tag.getText();
                        int start=frame.indexOf("src=");
                        frame=frame.substring(start);
                        int end =frame.indexOf(" ");
                        if (end ==-1) {
                            end=frame.indexOf(">");
                            String frameUrl=frame.substring(5,end-1);
                            if (filter.accept(frameUrl)) {
                                links.add(frameUrl);
                            }
                        }
                     }
                }
        } catch (ParserException e) {
            e.printStackTrace();
            parser.reset();
        }
        return links;
    }
    
    public static void getContent(String url,LinkFilter filter) {
        try {
            Parser parser =new Parser(url);
            parser.setEncoding("UTF-8");
            //过滤<frame> 标签的filtr,用来提取frame标签里的src属性
           /* for (NodeIterator i = parser.elements (); i.hasMoreNodes(); ) {
                Node node = i.nextNode();
                System.out.println("getText:"+node.getText());
                System.out.println("getPlainText:"+node.toPlainTextString());
                System.out.println("toHtml:"+node.toHtml());
                System.out.println("toHtml(true):"+node.toHtml(true));
                System.out.println("toHtml(false):"+node.toHtml(false));
                System.out.println("toString:"+node.toString());
                System.out.println("=================================================");
            }     */   
            
            AndFilter andFilter =  new AndFilter( 
                                   new TagNameFilter("div"), 
                                   new HasAttributeFilter("id","contentText") 
                  ); 
            RegexFilter regexFilter=new RegexFilter("<!-- 正文 -->");
            StringFilter stringFilter=new StringFilter("<!-- 正文 -->");
            NodeList nodes = parser.parse(andFilter);
            /*NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if((node instanceof Tag) 
					    && "contentText".equals(((Tag)node).getAttribute("id"))
						&& ((Tag)node).getTagName().equals("DIV")
					  ){
						return true;
					}else{
						return false;
					}
				}
			});*/
           for (int i = 0; i < nodes.size(); i++) {
                Node textnode = (Node) nodes.elementAt(i);
                 System.out.println("getText:"+textnode.toHtml());
                System.out.println("=================================================");
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    
    public static void getContent(String url,ReptileRule reptileRule,ContentReptileService contentReptileService) throws Exception{
    	try {
    		Parser parser =new Parser(url);
            parser.setEncoding("UTF-8");
    			ContentReptile contentReptile=new ContentReptile();
    			/**
    			 * 标题
    			 */
    			if(StringUtils.isNotBlank(reptileRule.getTitleStart())){//通过正则匹配: <!-- 正文 -->([\\s\\S]*)<!-- seo标签描述 -->
    				String title="";
    				RegexFilter regexFilter=new RegexFilter(""+reptileRule.getContentStart()+"([\\s\\S]*)"+reptileRule.getContentEnd()+"");
    				NodeList nodes = parser.parse(regexFilter);
    				for (int i = 0; i < nodes.size(); i++) {
    	                Node textnode = (Node) nodes.elementAt(i);
    	                 System.out.println("getText:"+textnode.toHtml());
    	                System.out.println("=================================================");
    	            }
    				contentReptile.setTitle(title);
    			}
    			
		} catch (Exception e) {
			throw new Exception("异常");
		}
    }
}
