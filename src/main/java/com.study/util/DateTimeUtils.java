package com.study.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateTimeUtils {

    private static TimeZone gmt = new SimpleTimeZone(0, "GMT");
    private static final String EX_GMT_TO_DATE = "gmtToDate: invalid gmt date string!";
    private static final String EX_GET_MONTH_NAME = "getMonthName : month value must be between 1 and 12!";
    private static final String EX_GET_ELAPSED_TIME = "getElapsedTime : begin date must be inferior to end date !";
    private static final String EX_GET_DAY_NAME = "getDayName: day value must be between 1 and 7!";
    private static final String EX_FORMAT_MONTH = "Invalid month name";
    private static final String EX_DATE_TO_GMT = "dateToGmt: unknown RFC format";

    public DateTimeUtils() {
    }

    public static String format(Date d, String fmt) {
        return format(d, fmt, (TimeZone)null);
    }

    public static String format(Date d, String fmt, TimeZone timezone) {
        return format(d, fmt, timezone, (Locale)null);
    }

    public static String format(Date d, String fmt, TimeZone timezone, Locale locale) {
        if (fmt == null) {
            fmt = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat df;
        if (locale == null) {
            df = new SimpleDateFormat(fmt);
        } else {
            df = new SimpleDateFormat(fmt, locale);
        }

        if (timezone == null) {
            df.setTimeZone(TimeZone.getDefault());
        } else {
            df.setTimeZone(timezone);
        }

        return df.format(d);
    }

    public static String format(Date d) {
        return format(d, (String)null, (TimeZone)null);
    }

    public static String format(Date d, TimeZone timezone) {
        return format(d, (String)null, timezone);
    }

    public static String formatDate(Date d, TimeZone timezone) {
        return format(d, "yyyy-MM-dd", timezone);
    }

    public static String formatDate(Date d) {
        return formatDate(d, (TimeZone)null);
    }

    public static String formatTime(Date d, TimeZone timezone) {
        return format(d, "HH:mm:ss", timezone);
    }

    public static String formatTime(Date d) {
        return formatTime(d, (TimeZone)null);
    }

    public static String defaultDateString(Date d) {
        return defaultDateString(d, (TimeZone)null);
    }

    public static String defaultDateString(Date d, TimeZone timezone) {
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }

        long l = (long)timezone.getRawOffset();
        StringBuffer temp = new StringBuffer(format(d, timezone));
        if (l > 0L) {
            temp.append(" +");
        } else {
            temp.append(" -");
            l = -l;
        }

        temp.append(format(new Date(l), "HH:mm", gmt));
        return temp.toString();
    }

    public static Date parseDate(String s) throws ParseException {
        try {
            return parseDate(s, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException var10) {
            try {
                return parseDate(s, "yyyy-MM-dd");
            } catch (ParseException var9) {
                try {
                    return parseDate(s, "MM/dd/yyyy HH:mm:ss");
                } catch (ParseException var8) {
                    try {
                        return parseDate(s, "MM/dd/yyyy");
                    } catch (ParseException var7) {
                        try {
                            return parseDate(s, "HH:mm:ss");
                        } catch (ParseException var6) {
                            throw new ParseException("can not understand your format", -1);
                        }
                    }
                }
            }
        }
    }

    public static Date parseDate(String s, String fmt) throws ParseException {
        return parseDate(s, fmt, (TimeZone)null);
    }

    public static Date parseDate(String s, String fmt, TimeZone timezone) throws ParseException {
        return parseDate(s, fmt, timezone, (Locale)null);
    }

    public static Date parseDate(String s, String fmt, TimeZone timezone, Locale locale) throws ParseException {
        SimpleDateFormat df;
        if (locale == null) {
            df = new SimpleDateFormat(fmt);
        } else {
            df = new SimpleDateFormat(fmt, locale);
        }

        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }

        df.setTimeZone(timezone);
        df.setLenient(false);
        return df.parse(s);
    }

    public static Date parseDefaultDate(String s) throws ParseException {
        int i = s.lastIndexOf(32);
        char c = s.charAt(i + 1);
        long l = parseDate(s.substring(i + 2), "HH:mm", gmt).getTime();
        if (c == '-') {
            l = -l;
        }

        return parseDate(s.substring(0, i), "yyyy-MM-dd HH:mm:ss", new SimpleTimeZone((int)l, "XXX"));
    }

    public static String emailDate(Date d) {
        return emailDate(d, (TimeZone)null);
    }

    public static String emailDate(Date d, TimeZone timezone) {
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }

        long l = (long)timezone.getRawOffset();
        StringBuffer temp = new StringBuffer(format(d, "E, d MMM yyyy HH:mm:ss", timezone, Locale.UK));
        if (l > 0L) {
            temp.append(" +");
        } else {
            temp.append(" -");
            l = -l;
        }

        temp.append(format(new Date(l), "HHmm", gmt));
        return temp.toString();
    }

    public static Date parseEmailDate(String s) throws ParseException {
        try {
            int i = s.lastIndexOf(32);
            char c = s.charAt(i + 1);
            long l = parseDate(s.substring(i + 2), "HHmm", gmt).getTime();
            if (c == '-') {
                l = -l;
            }

            return parseDate(s.substring(0, i), "E, d MMM yyyy HH:mm:ss", new SimpleTimeZone((int)l, "XXX"), Locale.UK);
        } catch (Exception var5) {
            return parseDate(s, "E, d MMM yyyy HH:mm:ss z", gmt, Locale.UK);
        }
    }

    public static boolean dayBefore(Date dt, Date dt1) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(1);
        int date = c.get(6);
        c.setTime(dt1);
        int year1 = c.get(1);
        int date1 = c.get(6);
        return year < year1 || year == year1 && date < date1;
    }

    public static boolean dayAfter(Date dt, Date dt1) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(1);
        int date = c.get(6);
        c.setTime(dt1);
        int year1 = c.get(1);
        int date1 = c.get(6);
        return year > year1 || year == year1 && date > date1;
    }

    public static boolean dayEquals(Date dt, Date dt1) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int year = c.get(1);
        int date = c.get(6);
        c.setTime(dt1);
        int year1 = c.get(1);
        int date1 = c.get(6);
        return year == year1 && date == date1;
    }

    public static int getQuarter(Date date) {
        int quarter = 0;
        int month = getMonth(date);
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else if (month >= 10 && month <= 12) {
            quarter = 4;
        }

        return quarter;
    }

    public static int daysOfMonth(int year, int month) {
        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeap(year)) {
                    return 29;
                }

                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    public static Date addDate(Date dDate1, Date dDate2) {
        long date1Ms = dDate1.getTime();
        long date2Ms = dDate2.getTime();
        long add = date1Ms + date2Ms;
        return new Date(add);
    }

    public static Date addDay(Date dDate, long iNbDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        cal.add(5, (int)iNbDay);
        Date result = cal.getTime();
        return result;
    }

    public static Date addDuration(Date dDate, int iNbYear, int iNbMonth) {
        Date result = addMonth(dDate, iNbMonth);
        result = addYear(result, iNbYear);
        return result;
    }

    public static Date addDuration(Date dDate, int iNbYear, int iNbMonth, int iNbDay) {
        Date result = addDay(dDate, (long)iNbDay);
        result = addMonth(result, iNbMonth);
        result = addYear(result, iNbYear);
        return result;
    }

    public static Date addDuration(Date dDate, int iNbYear, int iNbMonth, int iNbDay, int iNbHour) {
        Date result = addHour(dDate, (long)iNbHour);
        result = addDay(result, (long)iNbDay);
        result = addMonth(result, iNbMonth);
        result = addYear(result, iNbYear);
        return result;
    }

    public static Date addDuration(Date dDate, int iNbYear, int iNbMonth, int iNbDay, int iNbHour, int iNbMinute) {
        Date result = addMinute(dDate, (long)iNbMinute);
        result = addHour(result, (long)iNbHour);
        result = addDay(result, (long)iNbDay);
        result = addMonth(result, iNbMonth);
        result = addYear(result, iNbYear);
        return result;
    }

    public static Date addDuration(Date dDate, int iNbYear, int iNbMonth, int iNbDay, int iNbHour, int iNbMinute, int iNbSecond) {
        Date result = addSecond(dDate, (long)iNbSecond);
        result = addMinute(result, (long)iNbMinute);
        result = addHour(result, (long)iNbHour);
        result = addDay(result, (long)iNbDay);
        result = addMonth(result, iNbMonth);
        result = addYear(result, iNbYear);
        return result;
    }

    public static Date addHour(Date dDate, long iNbHour) {
        long datems = dDate.getTime();
        long hourMs = iNbHour * 60L * 60L * 1000L;
        long newDateMs = datems + hourMs;
        Date result = new Date(newDateMs);
        return result;
    }

    public static Date addMinute(Date dDate, long iNbMinute) {
        long datems = dDate.getTime();
        long minuteMs = iNbMinute * 60L * 1000L;
        long newDateMs = datems + minuteMs;
        Date result = new Date(newDateMs);
        return result;
    }

    public static Date addMonth(Date dDate, int iNbMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        int month = cal.get(2);
        month += iNbMonth;
        int year = month / 12;
        month %= 12;
        cal.set(2, month);
        if (year != 0) {
            int oldYear = cal.get(1);
            cal.set(1, year + oldYear);
        }

        return cal.getTime();
    }

    public static Date addSecond(Date dDate, long iNbSecond) {
        long datems = dDate.getTime();
        long secondms = iNbSecond * 1000L;
        long newDateMs = datems + secondms;
        Date result = new Date(newDateMs);
        return result;
    }

    public static Date addYear(Date dDate, int iNbYear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        int oldYear = cal.get(1);
        cal.set(1, iNbYear + oldYear);
        return cal.getTime();
    }

    public static long dateDiff(String interval, Date dDate1, Date dDate2) {
        int desiredField = 0;
        int coef = 1;
        Date date2;
        Date date11;
        if (dDate1.getTime() > dDate2.getTime()) {
            coef = -1;
            date11 = dDate2;
            date2 = dDate1;
        } else {
            date11 = dDate1;
            date2 = dDate2;
        }

        byte field;
        if (interval.equals("yyyy")) {
            field = 1;
        } else if (interval.equals("m")) {
            field = 2;
        } else if (interval.equals("d")) {
            field = 5;
        } else if (interval.equals("y")) {
            field = 5;
        } else if (interval.equals("w")) {
            field = 4;
        } else if (interval.equals("ww")) {
            field = 3;
        } else if (interval.equals("h")) {
            field = 5;
            desiredField = 11;
        } else if (interval.equals("n")) {
            field = 5;
            desiredField = 12;
        } else {
            if (!interval.equals("s")) {
                return -1L;
            }

            field = 5;
            desiredField = 13;
        }

        Calendar calTmp = Calendar.getInstance();
        calTmp.setTime(date11);
        long nbOccurence = 0L;
        calTmp.add(field, 1);

        Date dateTemp;
        for(dateTemp = calTmp.getTime(); dateTemp.getTime() <= date2.getTime(); ++nbOccurence) {
            calTmp.add(field, 1);
            dateTemp = calTmp.getTime();
        }

        if (desiredField == 11 || desiredField == 12 || desiredField == 13) {
            calTmp.setTime(date11);
            calTmp.add(field, (int)nbOccurence);
            dateTemp = calTmp.getTime();
            switch(desiredField) {
                case 11:
                    nbOccurence *= 24L;
                    break;
                case 12:
                    nbOccurence = nbOccurence * 24L * 60L;
                    break;
                case 13:
                    nbOccurence = nbOccurence * 24L * 60L * 60L;
            }

            calTmp.add(desiredField, 1);

            for(dateTemp = calTmp.getTime(); dateTemp.getTime() <= date2.getTime(); ++nbOccurence) {
                calTmp.add(desiredField, 1);
                dateTemp = calTmp.getTime();
            }
        }

        return nbOccurence * (long)coef;
    }

    public static long dateDiff(Date date1, Date date2) {
        long date1ms = date1.getTime();
        long date2ms = date2.getTime();
        return date2ms - date1ms;
    }

    public static String dateToGMT(Date vDate, String sFormat) throws Exception {
        String resultString = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(vDate);
        int iDay = cal.get(7);
        int iMonth = cal.get(2);
        String sWeekDay = "";
        String sMonth = "";
        String pattern = "";
        switch(iDay) {
            case 1:
                sWeekDay = "Sunday";
                break;
            case 2:
                sWeekDay = "Monday";
                break;
            case 3:
                sWeekDay = "Tuesday";
                break;
            case 4:
                sWeekDay = "Wednesday";
                break;
            case 5:
                sWeekDay = "Thursday";
                break;
            case 6:
                sWeekDay = "Friday";
                break;
            case 7:
                sWeekDay = "Saturday";
        }

        switch(iMonth) {
            case 0:
                sMonth = "Jan";
                break;
            case 1:
                sMonth = "Feb";
                break;
            case 2:
                sMonth = "Mar";
                break;
            case 3:
                sMonth = "Apr";
                break;
            case 4:
                sMonth = "May";
                break;
            case 5:
                sMonth = "Jun";
                break;
            case 6:
                sMonth = "Jul";
                break;
            case 7:
                sMonth = "Aug";
                break;
            case 8:
                sMonth = "Sep";
                break;
            case 9:
                sMonth = "Oct";
                break;
            case 10:
                sMonth = "Nov";
                break;
            case 11:
                sMonth = "Dec";
        }

        if (!sFormat.equals("RFC822") && !sFormat.equals("RFC1123")) {
            if (!sFormat.equals("RFC850") && !sFormat.equals("RFC1036")) {
                if (!sFormat.equals("ASCTIME")) {
                    throw new Exception("dateToGmt: unknown RFC format");
                }

                pattern = "'" + sWeekDay.substring(0, 3) + " " + sMonth + "' d HH:mm:ss yyyy";
                resultString = format(vDate, pattern);
            } else {
                pattern = "'" + sWeekDay + "', dd-'" + sMonth + "'-yy HH:mm:ss 'GMT'";
                resultString = format(vDate, pattern);
            }
        } else {
            pattern = "'" + sWeekDay.substring(0, 3) + "', dd '" + sMonth + "' yyyy HH:mm:ss 'GMT'";
            resultString = format(vDate, pattern);
        }

        return resultString;
    }

    public static String dateToSQL(Date vDate) {
        String sqlDate = "#" + format(vDate, "M/d/yyyy H:m:s") + "#";
        return sqlDate;
    }

    private static int formatMonth(String sMonth) throws Exception {
        if (sMonth.equalsIgnoreCase("jan")) {
            return 0;
        } else if (sMonth.equalsIgnoreCase("feb")) {
            return 1;
        } else if (sMonth.equalsIgnoreCase("mar")) {
            return 2;
        } else if (sMonth.equalsIgnoreCase("apr")) {
            return 3;
        } else if (sMonth.equalsIgnoreCase("may")) {
            return 4;
        } else if (sMonth.equalsIgnoreCase("jun")) {
            return 5;
        } else if (sMonth.equalsIgnoreCase("jul")) {
            return 6;
        } else if (sMonth.equalsIgnoreCase("aug")) {
            return 7;
        } else if (sMonth.equalsIgnoreCase("sep")) {
            return 8;
        } else if (sMonth.equalsIgnoreCase("oct")) {
            return 9;
        } else if (sMonth.equalsIgnoreCase("nov")) {
            return 10;
        } else if (sMonth.equalsIgnoreCase("dec")) {
            return 11;
        } else {
            throw new Exception("Invalid month name");
        }
    }

    public static int getDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(5);
    }

    public static String getDayName(int iNDay) throws Exception {
        if (iNDay >= 1 && iNDay <= 7) {
            Calendar cal = Calendar.getInstance();
            cal.set(7, iNDay);
            return format(cal.getTime(), "EEEE");
        } else {
            throw new Exception("getDayName: day value must be between 1 and 7!");
        }
    }

    public static String getDayName(Date vDate) {
        return format(vDate, "EEEE");
    }

    public static int getElapsedTime(Date vDateBegin, Date vDateEnd, Date vDate) throws Exception {
        long begin = vDateBegin.getTime();
        long end = vDateEnd.getTime();
        long current = vDate.getTime();
        if (begin > end) {
            throw new Exception("getElapsedTime : begin date must be inferior to end date !");
        } else if (current <= begin) {
            return 0;
        } else if (current >= end) {
            return 100;
        } else {
            long result = 100L * dateDiff(vDateBegin, vDate) / dateDiff(vDateBegin, vDateEnd);
            return (int)result;
        }
    }

    public static Date getGMTDate() {
        Date now = new Date();
        long nowMs = now.getTime();
        long gmtOffetsMs = (long)(getServerGMTOffset() * 60 * 1000);
        Date nowGMT = new Date(nowMs - gmtOffetsMs);
        return nowGMT;
    }

    public static int getGMTHour() {
        Date nowGMT = getGMTDate();
        int gmtHour = Integer.parseInt(format(nowGMT, "H"));
        return gmtHour;
    }

    public static long getGMTOffset(Date vDate) {
        Date nowGMT = getGMTDate();
        long nowGMTMs = nowGMT.getTime();
        long vDateMs = vDate.getTime();
        double diffMs = (double)(vDateMs - nowGMTMs);
        double diffSec = diffMs / 1000.0D;
        double diffMinute = (double)(Math.round(diffSec) / 60L);
        return Math.round(diffMinute);
    }

    public static int getMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(2) + 1;
    }

    public static String getMonthName(int iMonth) throws Exception {
        --iMonth;
        if (iMonth >= 0 && iMonth <= 11) {
            Calendar cal = Calendar.getInstance();
            cal.set(5, 1);
            cal.set(2, iMonth);
            return format(cal.getTime(), "MMMM");
        } else {
            throw new Exception("getMonthName : month value must be between 1 and 12!");
        }
    }

    public static String getMonthName(Date vDate) {
        return format(vDate, "MMMM");
    }

    public static Hashtable getRemainTime(Date vDate) {
        Date now = new Date();
        Hashtable numbers = new Hashtable();
        long iNbMilisecSeconds = vDate.getTime() - now.getTime();
        int m_iYears = 0;
        int m_iMonths = 0;
        int m_iDays = 0;
        int m_iHours = 0;
        int m_iMinutes = 0;
        int m_iSeconds = 0;
        int m_iMillisec = 0;
        byte coef;
        if (iNbMilisecSeconds < 0L) {
            coef = -1;
            iNbMilisecSeconds = -iNbMilisecSeconds;
        } else {
            coef = 1;
        }

        Date m_date = new Date(now.getTime() + iNbMilisecSeconds);
        m_iYears = (int)dateDiff("yyyy", now, m_date);
        m_date = addYear(m_date, -m_iYears);
        if ((dateDiff("m", now, m_date) < 0L || dateDiff("d", now, m_date) < 0L || dateDiff("h", now, m_date) < 0L || dateDiff("n", now, m_date) < 0L || dateDiff("s", now, m_date) < 0L) && m_iYears != 0) {
            --m_iYears;
            m_date = addYear(m_date, 1);
        }

        m_iMonths = (int)dateDiff("m", now, m_date);
        m_date = addMonth(m_date, -m_iMonths);
        if ((dateDiff("d", now, m_date) < 0L || dateDiff("h", now, m_date) < 0L || dateDiff("n", now, m_date) < 0L || dateDiff("s", now, m_date) < 0L) && m_iMonths != 0) {
            --m_iMonths;
            m_date = addMonth(m_date, 1);
        }

        m_iDays = (int)dateDiff("d", now, m_date);
        m_date = addDay(m_date, (long)(-m_iDays));
        if ((dateDiff("h", now, m_date) < 0L || dateDiff("n", now, m_date) < 0L || dateDiff("s", now, m_date) < 0L) && m_iDays != 0) {
            --m_iDays;
            m_date = addDay(m_date, 1L);
        }

        m_iHours = (int)dateDiff("h", now, m_date);
        m_date = addHour(m_date, (long)(-m_iHours));
        if ((dateDiff("n", now, m_date) < 0L || dateDiff("s", now, m_date) < 0L) && m_iHours != 0) {
            --m_iHours;
            m_date = addHour(m_date, 1L);
        }

        m_iMinutes = (int)dateDiff("n", now, m_date);
        m_date = addMinute(m_date, (long)(-m_iMinutes));
        if (dateDiff("s", now, m_date) < 0L && m_iMinutes != 0) {
            --m_iMinutes;
            m_date = addMinute(m_date, 1L);
        }

        m_iSeconds = (int)dateDiff("s", now, m_date);
        m_date = addSecond(m_date, (long)(-m_iSeconds));
        m_iMillisec = (int)(m_date.getTime() - now.getTime());
        if (m_iMillisec < 0 && m_iSeconds != 0) {
            --m_iSeconds;
            m_date = addSecond(m_date, 1L);
            int var10000 = (int)(m_date.getTime() - now.getTime());
        }

        numbers.put("year", m_iYears * coef);
        numbers.put("month", m_iMonths * coef);
        numbers.put("day", m_iDays * coef);
        numbers.put("hour", m_iHours * coef);
        numbers.put("minute", m_iMinutes * coef);
        numbers.put("second", m_iSeconds * coef);
        return numbers;
    }

    public static int getServerGMTOffset() {
        Calendar now = Calendar.getInstance();
        int era = now.get(0);
        int year = now.get(1);
        int month = now.get(2);
        int day = now.get(5);
        int dayofweek = now.get(7);
        int milliseconds = now.get(14);
        int resultMs = Calendar.getInstance().getTimeZone().getOffset(era, year, month, day, dayofweek, milliseconds);
        return resultMs / 1000 / 60;
    }

    public static String getServerTimeZone() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return tz.getID();
    }

    public static int getWeek(Date vDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(vDate);
        int week = cal.get(3);
        return week;
    }

    public static int getWeekDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(7);
    }

    public static int getYear(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int year = cal.get(1);
        int era = cal.get(0);
        return era == 0 ? -1 * year : year;
    }

    public static Date gmtToDate(String sDate) throws Exception {
        int totalNbSpace = 0;
        String month = "";

        int beginSpace;
        for(beginSpace = 0; beginSpace < sDate.length(); ++beginSpace) {
            if (sDate.charAt(beginSpace) == ' ') {
                ++totalNbSpace;
            }
        }

        int endSpace;
        int iDay;
        int year;
        int hours;
        int minutes;
        int seconds;
        if (totalNbSpace == 5) {
            beginSpace = 1 + sDate.indexOf(" ");
            endSpace = sDate.indexOf(" ", beginSpace);
            iDay = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            month = sDate.substring(beginSpace, endSpace);
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            year = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            hours = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            minutes = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            seconds = Integer.parseInt(sDate.substring(beginSpace, endSpace));
        } else if (totalNbSpace == 3) {
            beginSpace = 1 + sDate.indexOf(" ");
            endSpace = sDate.indexOf("-", beginSpace);
            iDay = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf("-", beginSpace);
            month = sDate.substring(beginSpace, endSpace);
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            year = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            if (year < 70) {
                year += 2000;
            } else {
                year += 1900;
            }

            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            hours = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            minutes = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            seconds = Integer.parseInt(sDate.substring(beginSpace, endSpace));
        } else {
            if (totalNbSpace != 4) {
                throw new Exception("gmtToDate: invalid gmt date string!");
            }

            beginSpace = 1 + sDate.indexOf(" ");
            endSpace = sDate.indexOf(" ", beginSpace);
            month = sDate.substring(beginSpace, endSpace);
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            iDay = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            hours = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(":", beginSpace);
            minutes = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.indexOf(" ", beginSpace);
            seconds = Integer.parseInt(sDate.substring(beginSpace, endSpace));
            beginSpace = 1 + endSpace;
            endSpace = sDate.length();
            year = Integer.parseInt(sDate.substring(beginSpace, endSpace));
        }

        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, formatMonth(month));
        cal.set(5, iDay);
        cal.set(11, hours);
        cal.set(12, minutes);
        cal.set(13, seconds);
        return cal.getTime();
    }

    public static boolean isLeap(int year) {
        boolean div4 = year % 4 == 0;
        boolean div100 = year % 100 == 0;
        boolean div400 = year % 400 == 0;
        return div4 && (!div100 || div400);
    }

    public static Date secondsTo(long lNbSeconds) {
        return new Date(lNbSeconds * 1000L);
    }

    public static Date subDate(Date dDate1, Date dDate2) {
        long date1Ms = dDate1.getTime();
        long date2Ms = dDate2.getTime();
        long diff = date1Ms - date2Ms;
        return new Date(diff);
    }

    public static Date truncateDate(Date dt) {
        if (dt == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
            return new Date(cal.getTimeInMillis() / 1000L * 1000L);
        }
    }

}