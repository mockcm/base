package com.mock.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {

    public static final String SIMPLE_PATTERN                = "yyyy-MM-dd";
    public static final String SIMPLE_PATTERN_NON_YEAR       = "MM-dd";
    public static final String SIMPLE_PATTERN_NON_DELIMITERS = "yyyyMMdd";
    public static final String FULL_PATTERN                  = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_PATTERN_NON_DELIMITERS   = "yyyyMMddHHmmss";
    public static final long   SECOND                        = 1000L;
    public static final long   MINUTE                        = 60 * SECOND;
    public static final long   HOUR                          = 60 * MINUTE;
    public static final long   DAY                           = 24 * HOUR;
    public static final long   WEEK                          = 7 * DAY;
    
    private static final Calendar calendar = Calendar.getInstance();
    private static final SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
    
    private static Long timeOffset = 0L;
    
    public static Long getTimeOffset() {
		return timeOffset;
	}

	public static void setTimeOffset(Long timeOffset) {
		DateUtil.timeOffset = timeOffset;
	}

	public static String getMonDayOfWeek(String pattern) {
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	return sdf.format(calendar.getTime());
    }
	
	public static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate());
		return cal;
	}
	
	public static Calendar getRealCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(currentRealTimeMillis()));
		return cal;
	}
    
    public static boolean isWeekend(){
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1; 
        //0代表周日，6代表周六  
        if(week == 6 || week == 0) {
            return true;  
        }  
        return false;  
    } 
    
    /**
      * @Title: getYesterdayBeginTime
      * @Description: 获取前一天的开始时间
      * @return Date
      * @throws
    */
    public static Date getYesterdayBeginTime() {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
    }
    
    public static Long getTomorrowZeroTimeStamp() {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
    	cal.add(Calendar.DAY_OF_MONTH, 1);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	return cal.getTime().getTime();
    }
    
    public static Long getDayBefore(int numberOfDay) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
    	cal.add(Calendar.DAY_OF_MONTH, -numberOfDay);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	return cal.getTime().getTime();
    }
    
    
    
    /**
      * @Title: getYesterdayEndTime
      * @Description: 获取前一天的结果时间
      * @return
      * @return Date
      * @throws
    */
    public static Date getYesterdayEndTime() {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
    }
    
    public static boolean inYesToday(Long time) {
    	return time < getYesterdayEndTime().getTime();
    }
    
    public static Date getCurrentBeginTime() {
    	return getYesterdayEndTime();
    }
    
    public static Date getCurrentEndTime() {
    	return addDate(1);
    }
    
    public static Date addDate(int offset) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
    	cal.add(Calendar.DAY_OF_MONTH, offset);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
    }
    
    public static Date addHour(Date date,int offset){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.HOUR_OF_DAY,offset);
		return cal.getTime();
    }
    
    public static Date addMinute(Date date,int offset){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MINUTE,offset);
		return cal.getTime();
    }
    
    public static Date getCurrentMonthBeginDay() {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getDate());
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
    	return cal.getTime();
    }
    
    /**
     * 把一个日期时间对象转换成字符串格式
     */
    public static String getFormatString(Date currentDate, String pattern) {
        if (currentDate == null) {
            return "";
        }
        else {
            SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
            return dateformat == null ? "" : dateformat.format(currentDate);
        }
    }
    
    public static String getLastMonthWithYear(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate());
    	calendar.add(Calendar.MONTH, -1);;
    	SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
        return dateformat == null ? "" : dateformat.format(calendar.getTime());
    }
    
    /**
     * 把一个日期时间格式的字符串转化成想要的字符串格式
     * @throws Exception 
     */
    public static String getFormatString(String currentDateStr, String pattern) {
        if (currentDateStr == null) {
            return "";
        }
        else {
            try {
                SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
                return dateformat == null ? "" : dateformat.format(DateUtil.parseToDate(currentDateStr));
            }
            catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static Date parseToDate(String dateStr) throws ParseException {
        return parseToDate(dateStr, FULL_PATTERN);
    }

    public static Date parseToDate(String dateStr, String pattern) {
        String orginalPattern = pattern;
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        if (StringUtil.isEmpty(pattern)) {
            pattern = SIMPLE_PATTERN;
        }
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat();
        try {
            format.applyPattern(pattern);
            date = format.parse(dateStr);
        }
        catch (ParseException e) {
            // nothing to do
        }
        if (date == null) {
            if (FULL_PATTERN.equals(pattern)) {
                format.applyPattern(SIMPLE_PATTERN);
            }
            else {
                format.applyPattern(FULL_PATTERN);
            }
            try {
                date = format.parse(dateStr);
            }
            catch (ParseException e) {
                throw new UnsupportedOperationException("DateUtil doesn't support the date pattern : " + orginalPattern);
            }
        }
        return date;
    }

    public static String format(Date date) {
        return format(date, SIMPLE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtil.isEmpty(pattern)) {
            pattern = SIMPLE_PATTERN;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    public static String format(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return "";
        }
        if (StringUtil.isEmpty(pattern)) {
            pattern = SIMPLE_PATTERN;
        }
        Date date = parseToDate(dateStr, pattern);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String formatNonDelimiters(String simplePatternDate) {
        if (simplePatternDate != null) {
            try {
                Date date = DateUtil.parseToDate(simplePatternDate, SIMPLE_PATTERN);
                simplePatternDate = DateUtil.getFormatString(date, SIMPLE_PATTERN);
                return simplePatternDate.replace("-", "");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }

    public static Date getCurrentDate() {
        return getDate();
    }

    /**
     * 获取当前时间正负N天后的时间
     * @Author:laigengbiao
     * @Description: 
     * @param days 正数为前推N天，负数为后推N天
     * @return   
     * @throws
     */
    public static Date addDateByDays(int days) {
        return addDateByDays(getCurrentDate(), days);
    }

    /**
     * 获取指定时间的正负N天后的时间
     * @Author:laigengbiao
     * @Description: 
     * @param source	指定时间
     * @param days		正数为前推N天，负数为后推N天
     * @return   
     * @throws
     */
    public static Date addDateByDays(Date source, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate());
        calendar.setTime(source);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获取当前时间正负N月后的时间
     * @Description: 
     * @param months	正数为前推N月，负数为后推N月
     * @return   
     * @throws
     */
    public static Date addDateByMonths(int months) {
        return addDateByMonths(new Date(), months);
    }

    /**
     * 获取指定时间的正负N月后的时间
     * @Description: 
     * @param source	指定时间
     * @param days		正数为前推N月，负数为后推N月
     * @return   
     * @throws
     */
    public static Date addDateByMonths(Date source, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate());
        calendar.setTime(source);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * @throws ParseException 
     * 获取短日期（2012-12-21）
     * @Description: 
     * @param date
     * @return   
     * @throws
     */
    public static Date getShortDate(Date date) throws ParseException {
        return parseToDate(format(date, SIMPLE_PATTERN));
    }

    public static String[] getPreviousNDays(int n) {
        String[] days = new String[Math.abs(n)];

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int i = 0;

        for (int j = n + 1; j <= -1; j++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDate());
            calendar.add(Calendar.DATE, j);

            days[i] = df.format(calendar.getTime());

            i++;
        }

        days[days.length - 1] = df.format(new Date());

        return days;
    }

    public static Date strToDate(String input, String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);

            return df.parse(input);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得Year年份列表。根据参数增量来，正整数获取从今年起(包括今年)后面多少年，附整数从今年(包括今年)算起的前多少年。
     * 
     * @param increment
     * @return
     */
    public static List<Integer> getYearList(int increment, boolean isContainThisYear) {

        List<Integer> result = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate());
        int thisYear = calendar.get(Calendar.YEAR);

        if (isContainThisYear) {
            result.add(thisYear);
        }
        else {
            increment = increment > 0 ? increment + 1 : increment - 1;
        }

        if (increment > 0) {

            for (int x = 1; x < increment; x++) {
                result.add(++thisYear);
            }

        }
        else {

            for (int x = -1; x > increment; x--) {
                result.add(--thisYear);
            }

        }

        return result;

    }

    /**
     * 获得newDate - oldDate的时间差，单位为秒
     * @param newDate
     * @param oldDate
     * @return
     */
    public static int getTimeDifference(Date newDate, Date oldDate) {
        long timeDifference = newDate.getTime() - oldDate.getTime();
        return (int) (timeDifference / 1000);
    }

    /**
     * 两个日期的间隔天数
     * 
     * @param beginDate 起始日期
     * @param endDate 截止日期
     * @return 间隔天数
     */
    public static int getBetweenDays(Date beginDate, Date endDate) {
        return Math.abs(new Long((setTime(endDate, 0, 0, 0).getTime() - setTime(beginDate, 0, 0, 0).getTime()) / DAY).intValue());
    }

    /**
     * 设置时间
     * 
     * @param date 日期实例
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 设置后的日期时间
     */
    public static Date setTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    /**
     * 判断当前年份是否是晕年
     * 
     * @param year 年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 400) == 0 ? true : ((year % 4) == 0 ? ((year % 100) != 0 ? true : false) : false);
    }
    
    /**
     * 当前时刻 + 5位数随机码相加生成唯一的序列号
     * 
     * @return
     */
    public static String getSequence() {
        Long currentTimeMillis = DateUtil.currentTimeMillis();
        Long randomNumb = Long.parseLong(getRandomNumb(5));
        // 当前时刻 + 5位数随机码相加生成唯一的序列号
        return String.valueOf(currentTimeMillis + randomNumb);
    }
    
    public static Long toMilliSeconds(Long seconds) {
		return seconds*1000;
	}
	
	public static Long toSeconds(Long milliSeconds) {
		return milliSeconds/1000;
	}
	
	// 当前时间, 单位秒
	public static Long currentTimeSeconds() {
		return toSeconds(System.currentTimeMillis() + timeOffset);
	}
		
	// 当前时间, 单位毫秒
	public static Long currentTimeMillis() {
		return System.currentTimeMillis() + timeOffset;
	}
	
	// 当前真实时间, 单位秒, 不受时间偏移因子影响
	public static Long currentRealTimeSeconds() {
		return toSeconds(currentRealTimeMillis());
	}
	
	//当前真实时间, 单位毫秒, 不受时间偏移因子影响
	public static Long currentRealTimeMillis() {
		return System.currentTimeMillis();
	}
	
	// 向真实时间转换
	public static Long toRealTimeStampSeconds(Long timeStamp) {
		return toSeconds(toRealTimeStampMillis(toMilliSeconds(timeStamp)));
	}
	
	// 向真实时间转换
	public static Long toRealTimeStampMillis(Long timeStamp) {
		return (timeStamp - timeOffset);
	}
	
	// 获取日期, 单位毫秒
	public static Date getDate() {
		return new Date(currentTimeMillis());
	}
	
	public static Date getDate(Long timeStamp) {
		return new Date(timeStamp);
	}    
    /**
     * 生成制定长度的随机码
     */
    public static String getRandomNumb(int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            code = code.concat(String.valueOf(Math.round(Math.random() * 9)));
        }
        return code;
    }
    
    /**
	 * 昨天
	 * @return
	 */
	public static String yesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat dbFormat = new SimpleDateFormat(SIMPLE_PATTERN_NON_DELIMITERS);
		try {
			return dbFormat.format(cal.getTime());
		}catch (Exception ex) {
			
		}
		
		return "19700101";
	}

    public static void main(String[] args) {
        // System.out.println(DateUtil.format("2014-12-30 23:59:23.0", DateUtil.FULL_PATTERN));
        /*
         *  实验目标：统计目标对象新增到集合后再读取出来所消耗的时间（毫秒数）。
         *  实验的列表长度：3,000,000
         *  单位：毫秒  
         *  新增     数据结构                    读取     读取方式                        [新增耗时|读取耗时]
         *  ------------------------------------------------------------------------------------------
         *  add LinkedList get Iterator(): [1825|1904] [1887|1966] [1903|1982] [1841|1920] [1872|1951]
         *  add LinkedList get for():      [1825|放弃]  [1825|放弃]   [1825|放弃]  [1825|放弃]   [1825|放弃]
         *  add ArrayList  get for():      [1342|1342] [1280|1295] [1217|1233] [1263|1263] [1248|1248]
         *  add HashMap    get for():      [2496|3995] [2480|3948] [2480|4135] [2480|4104] [2496|3948]
         *  add Hashtable  get for():      [2714|4327] [2620|4166] [2714|4353] [2512|4088] [2403|4638]
         *  add HashSet    get Iterator(): [2028|2093] [2059|2107] [2031|2094] [2013|2076] [2059|2122]
         *  ------------------------------------------------------------------------------------------
         */
    	System.out.println(getTomorrowZeroTimeStamp());
    	System.out.println(DateUtil.format(DateUtil.getYesterdayEndTime(), "yyyy-MM-dd HH:mm:ss"));
    	System.out.println(format(addDate(1)));
    }

}