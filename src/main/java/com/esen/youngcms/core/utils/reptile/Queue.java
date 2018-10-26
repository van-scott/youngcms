package com.esen.youngcms.core.utils.reptile;
import java.util.LinkedList;

/**
 * 
 *     
 * 项目名称：sp2    
 * 类名称：Queue    
 * 类描述： 队列，保存要访问的url   
 * 创建人：ASUS User    
 * 创建时间：2015年4月21日 上午11:09:26      
 *
 */
public class Queue {

    
    @SuppressWarnings("rawtypes")
    private LinkedList queue=new LinkedList();
    
    /**
     * enQueue(入队列)    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     */
    @SuppressWarnings("unchecked")
    public void  enQueue(Object o) {
        queue.add(o);
    }
    
    /**
     * deQueue(出队列)    
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public Object deQueue() {
        return queue.removeFirst();
    }
    
    /**
     * deQueue(清空队列)    
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public void clearQueue() {
        queue.clear();
    }
    
    /**
     * isQueueEmpty(判断队列是否为空)    
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public boolean  isQueueEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * contians(判断队列中是否存在)    
     * @return String    DOM对象    
     * @Exception 异常对象    
     * @since  CodingExample　Ver(编码范例查看) 1.1    
     *
     */
    public boolean contians(Object o) {
        return queue.contains(o);
    }
    
    public boolean  empty() {
        return queue.isEmpty();
    }
}
