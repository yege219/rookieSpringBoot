package com.rookie.demo;

import org.joda.time.DateTime;

/**
 * Created by rookie on 2018/1/17.
 */
public class JodaTimeDemo {

    public static void main(String[] args) {

        //
        //1.获取当前日期时间对象
        DateTime dateTime = new DateTime();
        // 按照指定格式输出
        System.out.println(dateTime.toString("E yyyy/MM/dd HH:mm:ss.SSS"));

        // 当前时间减去一个月
        System.out.println(dateTime.minusMonths(1));

        // 当前日期的上个月的最后一天
        System.out.println(dateTime.minusMonths(1).dayOfMonth().withMaximumValue());

        // 获取每天的零点
        DateTime dt=new DateTime().withMillisOfDay(0);
        System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss"));

        // 每天的6:30
        DateTime dt1=new DateTime().withHourOfDay(6).withMinuteOfHour(30).withSecondOfMinute(0);
        System.out.println(dt1.toString("yyyy-MM-dd HH:mm:ss"));

        // 获取当月的7号的6:30
        DateTime dt2=new DateTime().withDayOfMonth(7).withHourOfDay(6).withMinuteOfHour(30).withSecondOfMinute(0);
        System.out.println(dt2.toString("yyyy-MM-dd HH:mm:ss"));

    }
}
