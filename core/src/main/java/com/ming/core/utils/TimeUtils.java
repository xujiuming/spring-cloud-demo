package com.ming.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * 时间工具类
 *
 * @author ming
 * @date 2021-09-10 18:36:42
 */
public class TimeUtils {
    //默认上海东八时区
    private static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

    /**
     * localDateTime 转换毫秒时间戳
     * 使用默认 +8时区
     *
     * @param localDateTime localDateTime
     * @return long 毫秒时间戳
     * @author ming
     * @date 2021-09-13 15:39:07
     */
    public static long toTimeMillis(LocalDateTime localDateTime) {
        return localDateTime.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli();
    }

    /**
     * localDateTime 转换毫秒时间戳
     *
     * @param localDateTime localDateTime
     * @param zoneOffset    时区偏移
     * @return long 毫秒时间戳
     * @author ming
     * @date 2021-09-13 15:39:07
     */
    public static long toTimeMillis(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return localDateTime.toInstant(zoneOffset).toEpochMilli();
    }

    /**
     * localDate 转换 毫秒时间戳
     * 默认 +8时区
     * 默认转换为当天00:00:00时间
     *
     * @param localDate localDate
     * @return long  毫秒时间戳
     * @author ming
     * @date 2021-09-13 15:46:32
     */
    public static long toTimeMillis(LocalDate localDate) {
        return toTimeMillis(LocalDateTime.of(localDate, LocalTime.MIN));
    }


    /**
     * localDate 转换 毫秒时间戳
     * 默认转换为当天00:00:00时间
     *
     * @param localDate  localDate
     * @param zoneOffset 时区偏移
     * @return long  毫秒时间戳
     * @author ming
     * @date 2021-09-13 15:46:32
     */
    public static long toTimeMillis(LocalDate localDate, ZoneOffset zoneOffset) {
        return toTimeMillis(LocalDateTime.of(localDate, LocalTime.MIN), zoneOffset);
    }

    /**
     * localTime 转换毫秒时间戳
     * 默认使用+8时区
     * 默认日期为当天
     *
     * @param localTime localTime
     * @return long
     * @author ming
     * @date 2021-09-13 15:52:28
     */
    public static long toTimeMillis(LocalTime localTime) {
        return toTimeMillis(LocalDateTime.of(LocalDate.now(), localTime));
    }

    /**
     * localTime 转换毫秒时间戳
     * 默认日期为当天
     *
     * @param localTime  localTime
     * @param zoneOffset 时区偏移
     * @return long
     * @author ming
     * @date 2021-09-13 15:52:28
     */
    public static long toTimeMillis(LocalTime localTime, ZoneOffset zoneOffset) {
        return toTimeMillis(LocalDateTime.of(LocalDate.now(), localTime), zoneOffset);
    }


    /**
     * 毫秒时间戳 转换 localDateTime
     * 默认使用+8时区
     *
     * @param timeMillis 毫秒时间戳
     * @return LocalDateTime
     * @author ming
     * @date 2021-09-13 15:40:12
     */
    public static LocalDateTime toLocalDateTime(long timeMillis) {
        return LocalDateTime.ofEpochSecond(timeMillis / 1000, 0, DEFAULT_ZONE_OFFSET);
    }

    /**
     * 毫秒时间戳 转换 localDateTime
     *
     * @param timeMillis 毫秒时间戳
     * @param zoneOffset 时区偏移
     * @return LocalDateTime
     * @author ming
     * @date 2021-09-13 15:40:12
     */
    public static LocalDateTime toLocalDateTime(long timeMillis, ZoneOffset zoneOffset) {
        return LocalDateTime.ofEpochSecond(timeMillis / 1000, 0, zoneOffset);
    }

    /**
     * 转换 localDate
     * 默认+8时区
     *
     * @param timeMillis 毫秒时间戳
     * @return LocalDate
     * @author ming
     * @date 2021-09-13 15:57:16
     */
    public static LocalDate toLocalDate(long timeMillis) {
        return toLocalDateTime(timeMillis).toLocalDate();
    }

    /**
     * 转换 localDate
     *
     * @param timeMillis 毫秒时间戳
     * @param zoneOffset 时区偏移
     * @return LocalDate
     * @author ming
     * @date 2021-09-13 15:57:16
     */
    public static LocalDate toLocalDate(long timeMillis, ZoneOffset zoneOffset) {
        return toLocalDateTime(timeMillis, zoneOffset).toLocalDate();
    }

    /**
     * 转换localTime
     * 默认使用+8时区
     *
     * @param timeMillis 毫秒时间戳
     * @return LocalTime
     * @author ming
     * @date 2021-09-13 15:58:33
     */
    public static LocalTime toLocalTime(long timeMillis) {
        return toLocalDateTime(timeMillis).toLocalTime();
    }

    /**
     * 转换localTime
     *
     * @param timeMillis 毫秒时间戳
     * @param zoneOffset 时区偏移
     * @return LocalTime
     * @author ming
     * @date 2021-09-13 15:58:33
     */
    public static LocalTime toLocalTime(long timeMillis, ZoneOffset zoneOffset) {
        return toLocalDateTime(timeMillis, zoneOffset).toLocalTime();
    }
}
