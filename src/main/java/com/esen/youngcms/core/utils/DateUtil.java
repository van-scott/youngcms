package com.esen.youngcms.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 * @author chenwenbiao
 *
 */
public class DateUtil {
	
	
	
	/**
	 * 将日期串转为日期对象
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromString(String dateString , String format){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(dateString);
		} catch (Exception e) {
			//logger.error("date format exception!" , e);
		}
		
		/**
		 * 异常就返回当前时间
		 */
		return new Date();
	}
	
	
	/**
	 * 将date格式的数据转为字符串格式的日期数据
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateString(Date date , String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	
	/**
	 * 获取今天日期串
	 * @return
	 */
	public static String getTodayString(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_DAY);
		return simpleDateFormat.format(new Date());
	}

    public static String getTodayMonthString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_MONTH);
        return simpleDateFormat.format(new Date());
    }

    public static String getTodayTimeString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_TIME);
        return simpleDateFormat.format(new Date());
    }
	
	
	/**
	 * 获取昨天日期串
	 * @return
	 */
	public static String getYesterdayString(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_DAY);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE , -1);
		return simpleDateFormat.format(calendar.getTime());
	}
	
	
	/**
	 * 获取当前时间的日期数，即从1970年到今天是第几天了
	 * @return
	 */
	public static long getTodayDateLong(){
		long date = System.currentTimeMillis();
		return date / 86400000;
	}

    public static String getDateByMis(long m){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now+m);
        return formatter.format(calendar.getTime());


    }
	
	
	
	public static void main(String[] argv){
		System.out.println(getTodayDateLong());

//		long lastLoginTime = System.currentTimeMillis();
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		long currentTime = System.currentTimeMillis();
//		
//		System.out.println("===========:" + (currentTime - lastLoginTime));
//		
//		//24 * 3600 * 1000 = 86400000
//		if(currentTime - lastLoginTime > 172800000){//超过二天，重新计算
//			System.out.println("超过两天");
//		}
//		else {
//			long ld = lastLoginTime / 86400000;
//			long cd = currentTime / 86400000; 
//			
//			if(cd - ld == 1){//只相差一天，就是连续登陆的用户
//				System.out.println("相关一天");
//			}
//		}
				
	}


    public static List<String> dayForWeek(String weeks) throws Throwable {
        List<String> retDates = new ArrayList<String>();
        int day = 1;
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        while(retDates.size()<4){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            if(weeks.contains(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)-1))){
                System.out.println(cal.get(Calendar.DAY_OF_WEEK)-1);
                System.out.println(time.format(cal.getTime()));
                retDates.add(time.format(cal.getTime()));
            }
            day++;
        }
        return retDates;
    }


    public static List<String> getBothDate(String weeksOwn,String weekSecound){
        String[] weekarrs = StringUtils.split(weeksOwn,',');
        List<String> retDates = new ArrayList<String>();
        for(String week:weekarrs){
            if(weekSecound.contains(week)){
                retDates.add(week);
            }
        }
        try {
            return dayForWeek(StringUtils.join(retDates.iterator(),','));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new ArrayList<String>();
        }
    }


    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int getWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static String dateToStr(Date date, int type) {
        switch (type)
        {
            case 0:
                return dateToStr(date);
            case 1:
                return dateToStr(date, "yyyy/MM");
            case 2:
                return dateToStr(date, "yyyyMMdd");
            case 11:
                return dateToStr(date, "yyyy-MM-dd");
            case 3:
                return dateToStr(date, "yyyyMM");
            case 4:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case 5:
                return dateToStr(date, "yyyyMMddHHmmss");
            case 6:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case 7:
                return dateToStr(date, "HH:mm:ss");
            case 8:
                return dateToStr(date, "HH:mm");
            case 9:
                return dateToStr(date, "HHmmss");
            case 10:
                return dateToStr(date, "HHmm");
            case 12:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
        }
        throw new IllegalArgumentException("Type undefined : " + type);
    }

    public static String dateToStr(Date date, String pattern){
        if ((date == null) || (date.equals("")))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }
    
}
