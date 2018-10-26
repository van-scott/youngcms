package com.esen.youngcms.core.utils;

/***
 * 比较两个实体类的属性值的变化，返回字符串
 * @author fumiao
 */
public class Compare {
/*	public static String CompareTo(Object model, Object changeModel)
			throws Exception {
		// 获取实体类的所有属性，返回Field数组
		Field[] field = model.getClass().getDeclaredFields();
		StringBuffer bf=new StringBuffer();
		// 遍历所有属性
		for (int j = 0; j < field.length; j++) {
			// 获取属性的名字
			String s_name = field[j].getName();
			// 将属性的首字符大写，方便构造get，set方法
			String name = s_name.substring(0, 1).toUpperCase() + s_name.substring(1);
			// 获取属性的类型
			String type = field[j].getGenericType().toString();
			// 如果type是类类型，则前面包含"class "，后面跟类名
			System.out.println("属性为：" + s_name);
			if (type.equals("class java.lang.String")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				String value = (String) method.invoke(model);
				String cValue = (String) cMethod.invoke(changeModel);
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + value + "");
				System.out.println("改变后的值为：" + cValue + "");
				if(Util.nullOrBlank(value)){
				   value="";
				}
				if(Util.nullOrBlank(cValue)){
					cValue="";
					}
				if(!value.equals(cValue)){
					if(!"".equals(getCnName(s_name))){
						  bf.append(getCnName(s_name)+":“"+value.trim()+"”~“"+cValue.trim()+"”;");	
					}else{
							  bf.append(name+":“"+value.trim()+"”~“"+cValue.trim()+"”;");
						}
					}
				 
				}
			if (type.equals("class java.lang.Integer")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				Integer value = (Integer) method.invoke(model);
				Integer cValue = (Integer) cMethod.invoke(changeModel);
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + value + "");
				System.out.println("改变后的值为：" + cValue + "");
				if(value==null){
					value=0;
				}
				if(cValue==null){
					cValue=0;
				}
				if(!value.equals(cValue)){
					if(DictUtil.getInstence().getContentById(value)!=null||DictUtil.getInstence().getContentById(cValue)!=null){
						if(!"".equals(getCnName(s_name))){
							   if(DictUtil.getInstence().getContentById(value)==null){
								   bf.append(""+getCnName(s_name)+":“”~"+DictUtil.getInstence().getContentById(cValue)+";");
							    }else if(DictUtil.getInstence().getContentById(cValue)==null){
							       bf.append(""+getCnName(s_name)+":"+DictUtil.getInstence().getContentById(value)+"~“”;");
							    }else{
							       bf.append(""+getCnName(s_name)+":"+DictUtil.getInstence().getContentById(value)+"~"+DictUtil.getInstence().getContentById(cValue)+";");
							    }
							}else{
								if(DictUtil.getInstence().getContentById(value)==null){
								   bf.append(""+name+":“”~"+DictUtil.getInstence().getContentById(cValue)+";");
								}else if(DictUtil.getInstence().getContentById(cValue)==null){
								   bf.append(""+name+":"+DictUtil.getInstence().getContentById(value)+"~“”;");
								}else{
								   bf.append(""+name+":"+DictUtil.getInstence().getContentById(value)+"~"+DictUtil.getInstence().getContentById(cValue)+";");
								}
						    }
					}else{
						if(!"".equals(getCnName(s_name))){
								bf.append(""+getCnName(s_name)+":"+value+"~"+cValue+";");
							}else{
								bf.append(""+name+":"+value+"~"+cValue+";");
							   }
					}
				}
			}
			if (type.equals("class java.lang.Short")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				Short value = (Short) method.invoke(model);
				Short cValue = (Short) cMethod.invoke(changeModel);
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + value + "");
				System.out.println("改变后的值为：" + cValue + "");
			}
			if (type.equals("class java.lang.Double")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				Double value = (Double) method.invoke(model);
				Double cValue = (Double) cMethod.invoke(changeModel);
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + value + "");
				System.out.println("改变后的值为：" + cValue + "");
				if(value==null){
					value=0.0;
				}
				if(cValue==null){
					cValue=0.0;
				}
				if(!value.equals(cValue)){
					if(!"".equals(getCnName(s_name))){
					       bf.append(""+getCnName(s_name)+":"+value+"~"+cValue+";");
						}else{
						   bf.append(""+name+":"+value+"~"+cValue+";");
						   }
						}
				}
			if (type.equals("class java.lang.Boolean")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				Boolean value = (Boolean) method.invoke(model);
				Boolean cValue = (Boolean) cMethod.invoke(changeModel);
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + value + "");
				System.out.println("改变后的值为：" + cValue + "");
			}
			if (type.equals("class java.util.Date")) {
				Method method = model.getClass().getMethod("get" + name);
				Method cMethod = changeModel.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				Date value = (Date) method.invoke(model);
				Date cValue = (Date) cMethod.invoke(changeModel);
				String a="";
				String b="";
				if(value!=null){
					a=DateUtil.formatDate(value, "yyyy-MM-dd").toString();
				}
				if(cValue!=null){
					b=DateUtil.formatDate(cValue, "yyyy-MM-dd").toString();
				}
				if(!a.equals(b)){
					if(!"".equals(getCnName(s_name))){
					       bf.append(""+getCnName(s_name)+":"+a+"~"+b+";");
						}else{
						   bf.append(""+name+":"+a+"~"+b+";");
						   }
						}
				System.out.println("数据类型为：" + type + "");
				System.out.println("改变前的值为：" + a + "");
				System.out.println("改变后的值为：" + b + "");
			}
		}
		System.out.println(bf);
		return bf.toString();
	}
	
	private static String getCnName(String EnName){
		List<LabelValue> labelValues=ListTypeParameter.customerClassify;
		String cnName="";
		for(int i=0;i<labelValues.size();i++){
			LabelValue labelValue=labelValues.get(i);
			if(labelValue.getValue().equals(EnName)){
		      cnName=labelValue.getLabel();
			}
		}
		return cnName;
		
	}*/
}
