package nest.hava.edutills.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/** 날짜(년월일) 표현 패턴 */
	public final static String PATTERN_DATE = "yyyy-MM-dd";

	/** 날짜(년월일) 표현 패턴 */
	public final static String PATTERN_SDATE = "yyyyMMdd";

	/** 날짜(년월일) 표현 패턴 */
	public final static String PATTERN_SDATE2 = "yyMMdd";

	/** 날짜(년월일) 한글 표현 패턴 */
	public final static String PATTERN_DATE_KOR = "yyyy년 MM월 dd일";

	/** 날짜(년월일) 시간 표현 패턴 */
	public final static String PATTERN_DATE_TIME = "yyyy-MM-dd HH.mm.ss";

	/** 날짜(년월) 표현 패턴 */
	public final static String PATTERN_DATE_YM = "yyyy-MM";

	/** 날짜(년월) 표현 패턴 */
	public final static String PATTERN_SDATE_YM = "yyyyMM";

	/** 시간 표현 패턴 */
	public final static String PATTERN_TIME = "HH.mm.ss";

	/** 시간 표현 패턴 */
	public final static String PATTERN_TIME_COL = "HH:mm:ss";

	/** 시간 표현 패턴 */
	public final static String PATTERN_STIME = "HHmmss";

	/** 날짜(년월일) DB 등록 패턴 */
	public final static String PATTERN_REGIDB_DATETIME = "yyyy-MM-dd HH:mm:ss";

	public final static String PATTERN_DATETIME = "yyyy/MM/dd HH:mm:ss";

	public final static String PATTERN_YMD = "yyyy/MM/dd";

	public final static String PATTERN_YMD2 = "yy/MM/dd";
	public final static String PATTERN_YMD3 = "yyMMddHHmm";

	public final static String PATTERN_YMD2_KOR = "yy년 MM개월 dd일";

	public final static String THE_FIRST_DAY = "01";

	/**
	 * String객체를 Date 형식으로 반환한다.
	 * 
	 * @param arg
	 *            : 날짜(문자열중 숫자만 8,12,14자리가 되어야함) 8자리 : 20110901 (yyyyMMdd) 12자리
	 *            : 201109011300 (yyyyMMddhhmi) 14자리 : 20110901130030
	 *            (yyyyMMddhhmiss)
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String arg) {
		Calendar cal = parseCalendar(arg);
		return cal.getTime();
	}

	/**
	 * String객체를 Calendar 형식으로 반환한다.
	 * 
	 * @param arg
	 *            : 날짜(문자열중 숫자만 8,12,14자리가 되어야함) 8자리 : 20110901 (yyyyMMdd) 12자리
	 *            : 201109011300 (yyyyMMddhhmi) 14자리 : 20110901130030
	 *            (yyyyMMddhhmiss)
	 * @return
	 * @throws Exception
	 */
	public static Calendar parseCalendar(String arg) {
		Calendar cal = Calendar.getInstance();
		int year, month, day;
		int hour = 0;
		int minute = 0;
		int second = 0;

		String date = arg.replaceAll("\\D", "");

		if (date.length() == 8) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
		} else if (date.length() == 12) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
			hour = Integer.parseInt(date.substring(8, 10));
			minute = Integer.parseInt(date.substring(10, 12));
		} else if (date.length() == 14) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
			hour = Integer.parseInt(date.substring(8, 10));
			minute = Integer.parseInt(date.substring(10, 12));
			second = Integer.parseInt(date.substring(12, 14));
		} else {
			throw new IllegalArgumentException("Unable to parse the date: " + arg);
		}
		cal.set(year, month - 1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}

	/**
	 * 날자포맷 yyyy-mm-dd
	 * 
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 * @throws ParseException
	 */
	public static String formatDate(String yyyyMMddHHmmssSSS) throws ParseException {
		return format(PATTERN_DATE, yyyyMMddHHmmssSSS);

	}

	public static String formatDateHistory(String yyyyMMddHHmmssSSS) throws ParseException {
		return format(PATTERN_REGIDB_DATETIME, yyyyMMddHHmmssSSS);

	}

	/**
	 * 시간포맷 시분초
	 * 
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 * @throws ParseException
	 */
	public static String formatTime(String yyyyMMddHHmmssSSS) throws ParseException {
		return format(PATTERN_TIME, yyyyMMddHHmmssSSS);
	}

	/**
	 * 날짜 text format 형식으로 변환.
	 *
	 * @param date
	 *            날짜 date
	 * @param pattern
	 *            날짜 형식
	 * @return 날짜 text format 된 string
	 */
	public static String formatDate(Date date, String pattern) {

		if (date == null)
			throw new NullPointerException("날짜 date 가 null 입니다.");
		if (pattern == null)
			throw new NullPointerException("날짜 패턴 string 이 null 입니다.");

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 
	 * @param pattern
	 *            일자패턴
	 * @param inDate
	 *            일자
	 * @return 포맷팅된 일자
	 * @throws ParseException
	 *             일자변환오류입니다.
	 */
	public static String format(String pattern, String inDate) throws ParseException {

		String strDate = "";

		if (!CommonUtils.isEmpty(inDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			if (inDate.trim().length() > 14) {
				inDate = inDate.substring(0, 14);
			}

			Date date = sdf.parse(inDate);
			strDate = format(pattern, date);
		}

		return strDate;
	}

	/**
	 * 
	 * @param pattern
	 * @param date
	 * @return String 날자
	 * @throws ParseException
	 */
	public static String format(String pattern, Date date) throws ParseException {

		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(pattern);

		return formatter.format(date);
	}

	/**
	 * 날자포맷
	 * 
	 * @param inDate
	 * @return
	 * @throws ParseException
	 */
	public static String format(String inDate) throws ParseException {
		Date date;
		String strDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		date = sdf.parse(inDate);
		strDate = format(PATTERN_DATE_TIME, date);

		return strDate;

	}

	/**
	 * 
	 * @param inPattern
	 *            변환전일자패턴
	 * @param outPattern
	 *            변환후일자패턴
	 * @param inDate
	 *            일자
	 * @return 포맷팅된 일자
	 * @throws ParseException
	 *             일자변환오류입니다.
	 */
	public static String format(String inPattern, String outPattern, String inDate) throws ParseException {

		String strDate = "";

		if (!CommonUtils.isEmpty(inDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat(inPattern);

			inDate = inDate.substring(0, inPattern.length());

			Date date = sdf.parse(inDate);
			strDate = format(outPattern, date);
		}

		return strDate;
	}

	/**
	 * 2007-06-10 형대로 오늘의 날짜를 보여준다.
	 * 
	 * @return 오늘날짜 (yyyy-MM-dd 형태)
	 */
	public static String getToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * @return 현재날짜(yyyyMMdd)
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		return formatter.format(new Date());
	}

	/**
	 * @return 현재날짜(yyMMdd)
	 */
	public static String getCurrentDate2() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");

		return formatter.format(new Date());
	}

	/**
	 * @return 어제날짜(yyyyMMdd)
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(cal.getTime());
	}

	/**
	 * @return 현재시각(HHmmss)
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		return formatter.format(new Date());
	}

	/**
	 * @return 현재일시(yyyyMMddHHmmssSSS)
	 */
	public static String getCurrentTimeMilli() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return formatter.format(new Date());
	}

	/**
	 * @return 현재일시(yyyyMMddHHmmssSSS)
	 */
	public static String getCurrentTimeMilli(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(new Date());
	}

	/**
	 * @return 현재일시(yyyyMMddHHmmssSSS)
	 * @throws ParseException
	 */
	public static String getDatePatternString(String inDate) throws ParseException {
		Date date;
		String strDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		date = sdf.parse(inDate);
		strDate = format(PATTERN_YMD, date);

		return strDate;
	}

	/**
	 * 날짜에 일수를 더한다.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * Calendar today = Calendar.getInstance();
	 * Date tomorrow = DateUtil.addDate(today.getTime(), 1);
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 *
	 * @param date
	 *            변경하려는 날짜
	 * @param amount
	 *            변경하려는 시간
	 * @return 변경된 날짜
	 */
	public static Date addDate(Date date, int amount) {

		return DateUtils.add(date, Calendar.DATE, amount);
	}

	public static Date addHour(Date date, int amount) {
		return DateUtils.add(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date getTruncateDate(Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}

	public static Date getTruncateDate(Date date, int type) {
		return DateUtils.truncate(date, type);
	}

	/**
	 * 날자에 일자수 만큼 더함
	 * 
	 * @param str
	 * @param field
	 * @param amount
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date add(String str, int field, int amount, String pattern) throws ParseException {

		Date date = parseDate(str, pattern);

		return DateUtils.add(date, field, amount);
	}

	/**
	 * 날짜에 특정시간을 더한다.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * Calendar today = Calendar.getInstance();
	 * Date tomorrow = DateUtil.add(today.getTime(), Calendar.DATE, 1);
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * <p>
	 * 날짜의 종류를 바꿔 더하려면 time field 를 변경해주면 되는데, 그 내용은 아래와 같다.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 *     Calendar.YEAR 연도
	 *     Calendar.MONTH 월
	 *     Calendar.DATE 일
	 *     Calendar.HOUR 시
	 *     Calendar.MINUTE 분
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 *
	 * @param date
	 *            변경하려는 날짜
	 * @param field
	 *            변경하려는 날짜의 종류
	 * @param amount
	 *            변경하려는 시간
	 * @return 변경된 날짜
	 */
	public static Date add(Date date, int field, int amount) {

		if (date == null)
			throw new NullPointerException("날짜 date 가 null 입니다.");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 날짜 string 을 날짜 date 로 변환.
	 *
	 * @param str
	 *            날짜 string
	 * @param pattern
	 *            날짜 형식
	 * @return 날짜 date
	 * @throws ParseException
	 *             변환할 string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static Date parseDate(String str, String pattern) throws ParseException {

		// DateUtil.isDate 에서 null check 한다.
		Date date;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		date = sdf.parse(str);

		return date;
	}

	/**
	 * 날짜 string 올바른 날짜인지 확인.
	 *
	 * @param str
	 *            확인할 날짜 string
	 * @param pattern
	 *            날짜 형식
	 * @return 날짜가 올바른지 여부
	 * @throws ParseException
	 *             string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static boolean isDate(String str, String pattern) throws ParseException {
		if (str == null)
			throw new NullPointerException("날짜 string 이 null 입니다.");
		if (pattern == null)
			throw new NullPointerException("날짜 패턴 string 이 null 입니다.");

		boolean isDate = false;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(str);

		if (str.equals(sdf.format(date)))
			isDate = true;

		return isDate;
	}

	/**
	 * 특정 날짜가 현재보다 미래인지 여부.
	 *
	 * @param date
	 *            특정 날짜
	 * @return 현재보다 미래인지 여부
	 */
	public static boolean isFuture(Date date) {
		Date rightNow = DateUtils.rightNow();
		boolean isFuture = DateUtils.isCompareToDate(rightNow, date);

		// 년월일로만 체크하기 위한 로직
		if (isFuture) {
			if (formatDate(date).equals(formatDate(rightNow))) {
				isFuture = false;
			}
		}

		return isFuture;
	}

	/**
	 * 특정 날짜 string 이 현재보다 미래인지 여부.
	 * <p>
	 * 변경하려는 날짜 string 의 날짜 형식은 "yyyy-MM-dd" 형태이어야 한다.
	 *
	 * @param str
	 *            특정 날짜 string
	 * @return 현재보다 미래인지 여부
	 * @throws ParseException
	 *             string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static boolean isFuture(String str) throws ParseException {
		return DateUtils.isFuture(str, PATTERN_SDATE);
	}

	/**
	 * 특정 날짜 string 이 현재보다 미래인지 여부.
	 *
	 * @param str
	 *            특정 날짜 string
	 * @param pattern
	 *            날짜 형식
	 * @return 현재보다 미래인지 여부
	 * @throws ParseException
	 *             string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static boolean isFuture(String str, String pattern) throws ParseException {

		Date date = parseDate(str, pattern);

		return DateUtils.isFuture(date);
	}

	/**
	 * 특정 날짜가 현재보다 과거인지 여부.
	 *
	 * @param date
	 *            특정 날짜
	 * @return 현재보다 과거인지 여부
	 */
	public static boolean isPast(Date date) {
		Date rightNow = DateUtils.rightNow();
		boolean isPast = DateUtils.isCompareToDate(date, rightNow);

		// 년월일로만 체크하기 위한 로직
		if (isPast) {
			if (formatDate(date).equals(formatDate(rightNow))) {
				isPast = false;
			}
		}

		return isPast;
	}

	public static Date parseDateWithoutException(String str, String... parsePatterns) {
		try {

			return parseDate(str, parsePatterns);

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 특정 날짜 string 이 현재보다 과거인지 여부.
	 * <p>
	 * 변경하려는 날짜 string 의 날짜 형식은 "yyyy-MM-dd" 형태이어야 한다.
	 *
	 * @param str
	 *            특정 날짜 string
	 * @return 현재보다 과거인지 여부
	 * @throws ParseException
	 *             string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static boolean isPast(String str) throws ParseException {
		return DateUtils.isPast(str, PATTERN_SDATE);
	}

	/**
	 * 특정 날짜 string 이 현재보다 과거인지 여부.
	 *
	 * @param str
	 *            특정 날짜 string
	 * @param pattern
	 *            날짜 형식
	 * @return 현재보다 과거인지 여부
	 * @throws ParseException
	 *             string 날짜가 정상적인 날짜가 아니거나 string 날짜의 형태가 잘못되었을 경우.
	 */
	public static boolean isPast(String str, String pattern) throws ParseException {

		Date date = parseDate(str, pattern);

		return DateUtils.isPast(date);
	}

	/**
	 * 날짜간의 선후관계.
	 *
	 * @param from
	 *            기준 날짜
	 * @param to
	 *            비교될 날짜
	 * @return 날짜간의 선후관계. 기준 날짜가 비교될 날짜 보다 과거일때만 true, 그렇지 않으면 false 를 반환한다.
	 */
	public static boolean isCompareToDate(Date from, Date to) {

		if (from == null)
			throw new NullPointerException("from날짜 date 가 null 입니다.");
		if (to == null)
			throw new NullPointerException("to날짜 date 가 null 입니다.");

		boolean isCompare = true;

		int diff = from.compareTo(to);
		if (diff >= 0)
			isCompare = false;

		return isCompare;
	}

	public static boolean isEqualDate(String date, String date1) throws ParseException {

		boolean isCompare = false;
		Date toDate = parseDate(date);
		Date fromDate = parseDate(date1);
		isCompare = isEqualDate(toDate, fromDate);

		return isCompare;
	}

	public static boolean isEqualDate(Date from, Date to) {

		if (from == null)
			throw new NullPointerException("from날짜 date 가 null 입니다.");
		if (to == null)
			throw new NullPointerException("to날짜 date 가 null 입니다.");

		boolean isCompare = true;

		int diff = from.compareTo(to);
		if (diff != 0)
			isCompare = false;

		return isCompare;
	}

	/**
	 * 날짜간의 선후관계.
	 * 
	 * @param from
	 *            기준 날짜
	 * @param to
	 *            비교될 날짜
	 * @return 날짜간의 선후관계. 기준 날짜가 비교될 날짜 보다 과거일때만 true, 그렇지 않으면 false 를 반환.
	 */
	public static boolean isCompareToDate(String date, String date1) throws ParseException {

		boolean isCompare = false;
		Date toDate = parseDate(date);
		Date fromDate = parseDate(date1);
		isCompare = isCompareToDate(toDate, fromDate);

		return isCompare;
	}

	/**
	 * 날짜년월간의 선후관계.
	 * 
	 * @param from
	 *            기준 날짜년월
	 * @param to
	 *            비교될 날짜년월
	 * @return 날짜년월간의 선후관계. 기준 날짜년월이 비교될 날짜년월 보다 과거일때만 true, 그렇지 않으면 false 를 반환.
	 */
	public static boolean isCompareToDateYM(String dateYM, String date1YM) throws ParseException {
		return isCompareToDate(dateYM + THE_FIRST_DAY, date1YM + THE_FIRST_DAY);
	}

	/**
	 * 날짜간의 선후관계.
	 *
	 * @param from
	 *            기준 날짜
	 * @param to
	 *            비교될 날짜
	 * @return 날짜간의 선후관계. <br>
	 *         from == to :0 <br>
	 *         from > to :less than 0 <br>
	 *         from < to :greater than 0
	 */
	public static int compareToDate(String from, String to) throws ParseException {

		Date fromDate = parseDate(from);
		Date toDate = parseDate(to);

		int diff = toDate.compareTo(fromDate);

		return diff;
	}

	/**
	 * 날짜간의 선후관계.
	 *
	 * @param from
	 *            기준 날짜
	 * @param to
	 *            비교될 날짜
	 * @return 날짜간의 선후관계. <br>
	 *         from == to :0 <br>
	 *         from > to :less than 0 <br>
	 *         from < to :greater than 0
	 */
	public static int compareToDate(String from, String to, String pattern) throws ParseException {

		Date fromDate = parseDate(from, pattern);
		Date toDate = parseDate(to, pattern);

		int diff = toDate.compareTo(fromDate);

		return diff;
	}

	/**
	 * 날짜년월 간의 선후관계.
	 *
	 * @param fromYM
	 *            기준 날짜년월 (YYYYMM)
	 * @param toYM
	 *            비교될 날짜년월 (YYYYMM)
	 * @return 날짜간의 선후관계. <br>
	 *         fromYM == to :0 <br>
	 *         fromYM > to :less than 0 <br>
	 *         fromYM < to :greater than 0
	 */
	public static int compareToDateYM(String fromYM, String toYM) throws ParseException {
		return compareToDate(fromYM + THE_FIRST_DAY, toYM + THE_FIRST_DAY);
	}

	/**
	 * 날짜 text format 형식으로 변환. text format 된 string 의 날짜 형식은 "yyyy-MM-dd" 형태이다.
	 *
	 * @param date
	 *            날짜 date
	 * @return 날짜 text format 된 string
	 */
	public static String formatDate(Date date) {
		return formatDate(date, PATTERN_SDATE);
	}

	/**
	 * 현재 날짜.
	 *
	 * @return 현재 날짜
	 */
	public static Date rightNow() {
		Calendar rightNow = Calendar.getInstance();
		return rightNow.getTime();
	}

	/**
	 * @return 지난주 날짜(yyyyMMdd)
	 */
	public static String getLastWeekDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(cal.getTime());
	}

	public static String getGlobalDate() {
		TimeZone tz;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		tz = TimeZone.getTimeZone("Europe/London");
		df.setTimeZone(tz);
		return df.format(date);
	}

	public static Long getGlobalDateWithLong() throws ParseException {
		TimeZone tz;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		tz = TimeZone.getTimeZone("Europe/London");
		df.setTimeZone(tz);
		String strDate = df.format(date);

		date = parseDate(strDate, "yyyyMMddHHmmssSSS");

		return date.getTime();
	}

	/**
	 * Compute the time difference between the two given dates in milliseconds,
	 * it always gives a positive result.
	 *
	 * @param date1
	 *            the first date.
	 * @param date2
	 *            the second date.
	 * @return the difference between the two given dates in milliseconds
	 * @throws IllegalArgumentException
	 *             if one a the given Date is null.
	 */
	public static long timeDifference(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException("Expecting date parameter not to be null");
		return Math.abs(date1.getTime() - date2.getTime());
	}

	public static Date convertFrom(Long date) {
		Date d = new Date(date);

		return d;
	}

	public static boolean truncatedEqualToday(Date dt) {

		if (dt == null) {
			return false;

		} else {

			if (org.apache.commons.lang3.time.DateUtils.truncatedEquals(dt, new Date(), Calendar.DATE)) {
				return true;
			}
		}

		return false;
	}

	public static Date getDateFromTimeStamp(long timestamp) {
		Date d = new Date(timestamp);

		return d;
	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1
	 *            the oldest date
	 * @param date2
	 *            the newest date
	 * @param timeUnit
	 *            the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * @param strDate1
	 *            yyyyMMdd
	 * @param strDate2
	 *            yyyyMMdd
	 * @return gaps between two days
	 */
	public static long getDateDiff(String strDate1, String strDate2) {
		Date date1 = parseDate(strDate1);
		Date date2 = parseDate(strDate2);
		return getDateDiff(date1, date2, TimeUnit.DAYS);
	}

	public static int getSecondGap(Date d1, Date d2) {
		int ss1 = getSecondFromDate(d1);
		int ss2 = get24HourFromDate(d2);

		int gap = Math.abs(ss1 - ss2);

		return gap;
	}

	public static int getHourGap(long timestamp1, long timestamp2) {
		Date d1 = new Date(timestamp1);
		Date d2 = new Date(timestamp2);

		int hh1 = get24HourFromDate(d1);
		int hh2 = get24HourFromDate(d2);

		int gap = Math.abs(hh1 - hh2);

		return gap;
	}

	public static int getDayOfWeek(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static int get24HourFromDate(Date d) {
		String hh = new SimpleDateFormat("HH").format(d);

		int iHH = Integer.parseInt(hh);

		return iHH;
	}

	public static int getSecondFromDate(Date d) {
		String ss = new SimpleDateFormat("ss").format(d);

		int iss = Integer.parseInt(ss);

		return iss;
	}

	public static boolean isMorning(int hour) {
		if (hour >= 4 && hour < 9) {
			return true;
		}

		return false;
	}

	public static boolean isDay(int hour) {
		if (hour >= 9 && hour < 19) {
			return true;
		}

		return false;
	}

	public static boolean isNight(int hour) {
		if (hour >= 19 && hour < 24) {
			return true;

		} else if (hour >= 0 && hour < 4) {
			return true;
		}

		return false;
	}

	public static Date getPastDate(int pastDays) {
		Date past = addDate(new Date(), -pastDays);

		return past;
	}

	public static Long getTimestamp(Date t) {
		return t.getTime();
	}

	public static List<String> getDateList(String fromDate, String toDate) {
		List<String> dateList = new ArrayList<>();
		long diff = getDateDiff(fromDate, toDate);
		Calendar cal = parseCalendar(fromDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i <= diff; i++) {
			String date = "";
			if (i == 0) {
				date = formatter.format(cal.getTime());
			} else {
				cal.add(Calendar.DATE, 1);
				date = formatter.format(cal.getTime());
			}
			dateList.add(date);
		}

		return dateList;
	}

	public static LocalDate asLocalDate(java.util.Date date) {
		return asLocalDate(date, ZoneId.systemDefault());
	}

	public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
		if (date == null) return null;

		if (date instanceof java.sql.Date)
			return ((java.sql.Date) date).toLocalDate();
		else
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(java.util.Date date) {
		return asLocalDateTime(date, ZoneId.systemDefault());
	}

	public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
		if (date == null) return null;

		if (date instanceof java.sql.Timestamp)
			return ((java.sql.Timestamp) date).toLocalDateTime();
		else
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
	}

	/**
	 * Calls {@link #asUtilDate(Object, ZoneId)} with the system default time
	 * zone.
	 */
	public static java.util.Date asUtilDate(Object date) {
		return asUtilDate(date, ZoneId.systemDefault());
	}

	/**
	 * Creates a {@link java.util.Date} from various date objects. Is null-safe.
	 * Currently supports:
	 * <ul>
	 * <li>{@link java.util.Date}
	 * <li>{@link java.sql.Date}
	 * <li>{@link java.sql.Timestamp}
	 * <li>{@link java.time.LocalDate}
	 * <li>{@link java.time.LocalDateTime}
	 * <li>{@link java.time.ZonedDateTime}
	 * <li>{@link java.time.Instant}
	 * </ul>
	 * 
	 * @param zone
	 *            Time zone, used only if the input object is LocalDate or
	 *            LocalDateTime.
	 * 
	 * @return {@link java.util.Date} (exactly this class, not a subclass, such
	 *         as java.sql.Date)
	 */
	public static java.util.Date asUtilDate(Object date, ZoneId zone) {
		if (date == null) return null;

		if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
			return new java.util.Date(((java.util.Date) date).getTime());
		if (date instanceof java.util.Date)
			return (java.util.Date) date;
		if (date instanceof LocalDate)
			return java.util.Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
		if (date instanceof LocalDateTime)
			return java.util.Date.from(((LocalDateTime) date).atZone(zone).toInstant());
		if (date instanceof ZonedDateTime)
			return java.util.Date.from(((ZonedDateTime) date).toInstant());
		if (date instanceof Instant)
			return java.util.Date.from((Instant) date);

		throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
	}

	/**
	 * Creates an {@link Instant} from {@code java.util.Date} or it's
	 * subclasses. Null-safe.
	 */
	public static Instant asInstant(Date date) {
		if (date == null) return null;
		else return Instant.ofEpochMilli(date.getTime());
	}

	/**
	 * Calls {@link #asZonedDateTime(Date, ZoneId)} with the system default time
	 * zone.
	 */
	public static ZonedDateTime asZonedDateTime(Date date) {
		return asZonedDateTime(date, ZoneId.systemDefault());
	}

	/**
	 * Creates {@link ZonedDateTime} from {@code java.util.Date} or it's
	 * subclasses. Null-safe.
	 */
	public static ZonedDateTime asZonedDateTime(Date date, ZoneId zone) {
		if (date == null) return null;
		else return asInstant(date).atZone(zone);
	}

	public static int between(LocalDate start, LocalDate end) {
		return (int) Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
	}

	public static int between(LocalDateTime start, LocalDate end) {
		return (int) Duration.between(start, end.atStartOfDay()).toDays();
	}

	public static int between(LocalDate start, LocalDateTime end) {
		return (int) Duration.between(start.atStartOfDay(), end).toDays();
	}

	public static int between(LocalDateTime start, LocalDateTime end) {
		return (int) Duration.between(start, end).toDays();
	}

}
