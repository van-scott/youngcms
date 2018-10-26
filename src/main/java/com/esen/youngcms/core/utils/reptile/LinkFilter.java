package com.esen.youngcms.core.utils.reptile;

/**
 * 项目名称：sp2    
 * 类名称：LinkFilter    
 * 类描述：    过滤url 保证url是我们需要爬取得内容
 * 创建人：buyuer    
 * 创建时间：2015年4月21日 下午2:39:50      
 */
public interface LinkFilter {

    public boolean accept(String url) ;
}
