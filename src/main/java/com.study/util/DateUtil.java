package com.study.util;

import com.study.blockchain.blockchain1.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期格式化工具类
 * 2019-06-28
 * yc
 */
public class DateUtil {
    public static final String DATE_YEAR = "yyyy";
    public static final String DATE_SHORTS = "yyyyMMdd";
    public static final String DATE_SHORT = "yyyy-MM-dd";
    public static final String DATE_MONTH = "yyyy-MM";
    public static final String DATE_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat SDF_SHORTS = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SDF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss");
    public static final String [] monthArr = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};

    /**
     * @description  计算两个之间的小时数  保留小数位
     * @param  starAmp  结束时间     时间格式HH:mm:ss
     * @param  endAmp  结束时间    时间格式HH:mm:ss
     * @param  scalse  小数位
     * @date  2019/10/18 16:19
     * @author  wanghb
     * @edit
     */
    public static BigDecimal getHourCount(String starAmp,String endAmp,Integer scalse){
        if("".equals(starAmp) || "".equals(endAmp)){
            return BigDecimal.ZERO;
        }
        String nowDate = toString(new Date(), DATE_SHORT);
        starAmp = nowDate + " " + starAmp;
        endAmp = nowDate + " " + endAmp;
        long diff = 0;
        try {
            diff = stringToDate(endAmp, DATE_LONG).getTime() - stringToDate(starAmp, DATE_LONG).getTime();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        DecimalFormat bDecimalFmt = new DecimalFormat("0.00");
        BigDecimal hour = new BigDecimal(diff).divide(new BigDecimal(60 * 60 * 1000) ,scalse, BigDecimal.ROUND_CEILING);
        return hour;
    }

    /**
     * @description  计算两个之间的小时数  保留小数位
     * @param  startDate  结束时间     时间格式HH:mm:ss
     * @param  endDate  结束时间    时间格式HH:mm:ss
     * @param  scalse  小数位
     * @date  2019/10/18 16:19
     * @author  wanghb
     * @edit
     */
    public static BigDecimal getHourCount(Date startDate,Date endDate,Integer scalse){
        if(PowerUtil.isNull( startDate ) || PowerUtil.isNull( endDate ) ){
            return BigDecimal.ZERO;
        }
        long diff = 0;
        try {
            diff = endDate.getTime() - startDate.getTime();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        if(diff < 0){
            return BigDecimal.ZERO;
        }
        BigDecimal hour = new BigDecimal(diff).divide(new BigDecimal(60 * 60 * 1000) ,scalse, BigDecimal.ROUND_CEILING);
        return hour;
    }

    /**
     * 获取当年年分
     *
     * @param
     * @return
     */
    public static String getCurrentDate() {
        return toString(new Date(), DATE_YEAR);
    }

    /**
     * 获取某个月份的的第一天
     * @return index 0 为当前学  1为下个月  -1为上个月  以此类推
     * @author wanghb
     * @date 2019-06-25
     */
    public static Date getMonthFirstDay(Integer index){
        if(index == null){
            return null;
        }
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, index);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();//当前月的第一天
    }
    public static final String[] weeksI = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    public static final String[] weeksII = {"日","一","二","三","四","五","六"};
    /**
     * 通过日期获取星期
     * @return String  date
     * @author wanghb
     * @date 2019-06-25
     */
    public static String getWeek(Date date ,String[] weeks){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return PowerUtil.getString(weeks[week_index]);
    }


    /**
     * @description  两个时间的大小比较
     * @param  time1  开始时间     时间格式HH:mm:ss
     * @param  time2  结束时间    时间格式HH:mm:ss
     * @date  2019/10/18 16:19
     * @author  wanghb
     * @edit  开始  >=  结束 返回true  否则返回false
     */
    public static Boolean hourDiff(String time1,String time2){
        if("".equals(time1) || "".equals(time2)){
            return false;
        }
        String nowDate = toString(new Date(), DATE_SHORT);
        time1 = nowDate + " " + time1;
        time2 = nowDate + " " + time2;
        return stringToDate(time1, DATE_LONG).getTime() >= stringToDate(time2, DATE_LONG).getTime();
    }

    /**
     * 获取中间日期
     * @return start 开始日期
     * @return end 结束日期
     * @author wanghb
     */
    public static List<String> getMiddleDate(Date start, Date end) {
        List<String> list = new ArrayList<>();
        long s = start.getTime();
        long e = end.getTime();
        Long oneDay = 1000 * 60 * 60 * 24l;
        while (s <= e) {
            start = new Date(s);
            list.add(new SimpleDateFormat("yyyy-MM-dd").format(start));
            s += oneDay;
        }
        return list;
    }


    /**
     * 日期计算 单位天
     * @return date 要计算的日期
     * @return end 结束日期
     * @author wanghb
     */
    public static Date dateCount(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);//当前时间减去一年，即一年前的时间
        return calendar.getTime();
    }



    /**
     * 日期计算 单位天
     * @return date 要计算的日期
     * @return end 结束日期
     * @return calendarType Calendar类的常量   Calendar.YEAR 年  Calendar.DATE 天 Calendar.MONTH 月
     * @author wanghb
     */
    public static Date dateCount(Date date, Integer days,int calendarType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarType, days);//当前时间减去一年，即一年前的时间
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间的天数
     * @return date 要计算的开始日期
     * @return end 结束日期
     * @author wanghb
     */
    public static long daysCount(Date startDate, Date endDate) {
        if("".equals(PowerUtil.getString(startDate)) || "".equals(PowerUtil.getString(startDate))){
            return 0;
        }
        //出生时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //转为毫秒
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long daysConut = endTime - startTime;
        //转为天
        long i = (daysConut/1000/60/60/24);
        return i;
    }

    /**
     * 将Date日期转换为String
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String toString(Date date, String formatStr) {
        if (null == date || null == formatStr) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(formatStr);

        return df.format(date);
    }

    /**
     * long 日期转 String
     *
     * @param date
     * @param format
     * @return
     */
    public static String longToDate(long date, String format) {
        Date d = new Date(date);
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 判断一个时间是上午还是下午
     * 0：上午
     * 1：下午
     * -1 无法识别
     * 格式一定是24小时制
     ** @param format
     * @return
     */
    public static int amEnum = 0;
    public static int pmEnum = 1;
    public static int getAMPM(long aLong, String format) {
        try {
            //Long aLong = Long.valueOf(str);
            String s = longToDate(aLong, "yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(s);
            GregorianCalendar ca = new GregorianCalendar();
            ca.setTime(date);
            return ca.get(GregorianCalendar.AM_PM);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public static int daysBetween(Date smdate,Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 
     * @return
     */
    public static long getMs(String endTime,String startTime){
    	SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
		long nm = 1000 * 60;// 一分钟的毫秒数  
		long ns = 1000;// 一秒钟的毫秒数  
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异  
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天  
			hour = diff % nd / nh + day * 24;// 计算差多少小时  
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟  
			sec = diff % nd % nh % nm / ns;// 计算差多少秒  
			//输出结果  
//			System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
//					+ (min - day * 24 * 60) + "分钟" + sec + "秒。");
//			System.out.println("hour=" + hour + ",min=" + min);
			long s = (hour - day * 24)*60;
			return (min - day * 24 * 60) + s;
		} catch (ParseException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		return -1;
    }


    /**
     * @description  计算两个时间相差的分钟数
     * @param  endTime  结束时间
     * @param  startTime  开始时间
     * return  bigDecimal2  分钟数
     * @date  2019/11/22 16:30
     * @author
     * @edit  wanghb  代码整理
     */
    public static BigDecimal getdifferenceTime(String endTime, String startTime){
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            long s = (hour - day * 24)*60*60;
            long s1 = (min - day * 24 * 60)*60 + s+sec;
            BigDecimal bigDecimal2 = new BigDecimal(s1);
            bigDecimal2=bigDecimal2.divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP);
            return bigDecimal2;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
    
    /**
     * @description  判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param  nowTime  当前时间
     * @param  startTime  开始时间
     * @param  endTime  结束时间
     * @date  2019/10/21 11:32
     * @author  jqlin
     * @edit
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {

        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * String 转 date
     * @param str
     * @param format
     * @return
     */
    public static Date stringToDate(String str,String format){
        if("".equals(PowerUtil.getString(str))){
            return null;
        }
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        try {
            return format1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 本月第一天
     * @return
     */
    public static String getOneDay(int index){

        Calendar cale = Calendar.getInstance();

        cale.add(Calendar.MONTH, 0);

        cale.set(Calendar.DAY_OF_MONTH, index);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(cale.getTime());
    }

    /**
     * @description  给日期往前或往后增加或减少天数
     * @param  date  日期
     * @param  count  正数增加,负数减少
     * return  返回的日期
     * @date  20/04/01 11:18
     * @author  wanghb
     * @edit  .
     */
    public static Date dateExtrapolate(Date date,Integer count){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,count); //把日期往后增加一天,整数  往后推,负数往前移动
        Date returnDate = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return returnDate;
    }

    /**
     * 获取往前多少天的日期
     * @return
     */
    public static String getFirstday(int index){
        Calendar cale = Calendar.getInstance();

        //获取昨天的日期
        cale = Calendar.getInstance();

        cale.add(Calendar.DATE,1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(cale.getTime());
    }

    /**
     * 获取往前多少天的日期
     * @return
     */
    public static String getYestoday(int index){
        Calendar cale = Calendar.getInstance();

        //获取昨天的日期
        cale = Calendar.getInstance();

        cale.add(Calendar.DATE,index);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(cale.getTime());
    }


    public static List<Date> findDates(Date dBegin, Date dEnd)  
    {  
     List lDate = new ArrayList();  
     lDate.add(dBegin);  
     Calendar calBegin = Calendar.getInstance();  
     // 使用给定的 Date 设置此 Calendar 的时间  
     calBegin.setTime(dBegin);  
     Calendar calEnd = Calendar.getInstance();  
     // 使用给定的 Date 设置此 Calendar 的时间  
     calEnd.setTime(dEnd);  
     // 测试此日期是否在指定日期之后  
     while (dEnd.after(calBegin.getTime()))  
     {  
      // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
      calBegin.add(Calendar.DAY_OF_MONTH, 1);  
      lDate.add(calBegin.getTime());  
     }  
     return lDate;  
    }

    /**
     * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
     * 比如：2011-02-02 到  2017-03-02
     *                                以年为单位相差为：6年
     *                                以月为单位相差为：73个月
     *                                以日为单位相差为：2220天
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int dayCompare(Date fromDate,Date toDate,String type){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int year = toYear  -  fromYear;
        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        int day = (int) ((to.getTimeInMillis()  -  from.getTimeInMillis())  /  (24  *  3600  *  1000));
        //System.out.println("年："+year+"..."+"月："+month+"..."+"天："+day);
        if("year".equals(type)) {
            return day;
        }
        if("month".equals(type)) {
            return month;
        }
        if("day".equals(type)) {
            return day;
        }
        return  0;
    }

    /**
     * 上一个月的今天
     * @param date
     * @param format1
     * @return
     */
    public static String  getLastMonth(Date date,String format1){
    	SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
    	  Calendar c = Calendar.getInstance();
    	  c.setTime(date);
    	  c.add(Calendar.MONTH, -1);
    	  Date m = c.getTime();
    	  return fmat.format(m);
    }
    //获取系统的当前年份
    public static String getSysYear() {

        Calendar date = Calendar.getInstance();

        String year = String.valueOf(date.get(Calendar.YEAR));

        return year;
    }


    /**
     * 某年某月有多少天
     * @param
     * @return
     */
    public static int getDays(Date date){
       	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());//设置时间
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
    	cal.set(Calendar.YEAR,year);
    	cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
    	return cal.getActualMaximum(Calendar.DATE);
    }
    
    /**
     * 获取某一年一共多少天
     * @return
     */
    public static int getcoutDays(int year){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, year);
    	int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    	return actualMaximum;
    }

    /**
     * 获取某年最后一天日期
     * @return
     */
    public static Date getCurrYearLast(){
    	Calendar currCal=Calendar.getInstance();
    	int currentYear = currCal.get(Calendar.YEAR);
    	return getYearLast(currentYear);
    	}



    public static Date getYearLast(int year){
    	Calendar calendar = Calendar.getInstance();
    	calendar.clear();
    	calendar.set(Calendar.YEAR, year);
    	calendar.roll(Calendar.DAY_OF_YEAR, -1);
    	Date currYearLast = calendar.getTime();
    	return currYearLast;
    	}

    /**
     * 获取两个日期间的日期集合
     * @param bigtime
     * @param endtime
     * @return
     */
        public static List<Date> getDayList(Date bigtime,Date endtime){
            //定义一个接受时间的集合
            List<Date> lDate = new ArrayList<Date>();
            lDate.add(bigtime);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(bigtime);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(endtime);
            // 测试此日期是否在指定日期之后
            while (endtime.after(calBegin.getTime()))  {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }
            return lDate;
        }
}
