package com.esen.youngcms.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortList<E>{
 
@SuppressWarnings("unchecked")
public void Sort(List<E> list, final String method, final String sort){
	System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    Collections.sort(list, new Comparator() {
      public int compare(Object a, Object b) {
        int ret = 0;
        try{
          Method m1 = ((E)a).getClass().getMethod(method, null);
          Method m2 = ((E)b).getClass().getMethod(method, null);
          if(sort != null && "desc".equals(sort))//倒序
          {
        		 Double value1 = Double.valueOf(m1.invoke(((E)a), null).toString());
            	 Double value2=Double.valueOf(m2.invoke(((E)b), null).toString());
                 ret = value2.compareTo(value1);
          }else{
        		 Double value1 = Double.valueOf(m1.invoke(((E)a), null).toString());
             	 Double value2=Double.valueOf(m2.invoke(((E)b), null).toString());
             	 ret = value1.compareTo(value2);
          }
        }catch(NoSuchMethodException ne){
          System.out.println(ne);
        }catch(IllegalAccessException ie){
          System.out.println(ie);
        }catch(InvocationTargetException it){
          System.out.println(it);
        }
        return ret;
      }
     });
  }
}