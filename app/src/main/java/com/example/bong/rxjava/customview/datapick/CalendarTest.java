package com.example.bong.rxjava.customview.datapick;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hs on 2017/8/17.
 */

public class CalendarTest {
    public static void main(String[] args) {
//        Test1();
//        Test2();
//        Test3();//计算某一月份的最大天数
//        Test4();//Calender和Date的相互转化
//        Test5();
//        Test6();
//        Test7();
//        Test8();
        Test9();
    }

    /**
     * roll的用法
     * ##可见，roll()方法在本月内循环，一般使用add()方法；
     */
    private static void Test9() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.roll(Calendar.DATE,4);
        Date time = calendar.getTime();
        System.out.println(simpleDateFormat.format(time));

        calendar.roll(Calendar.DATE, -4);
        Date time1 = calendar.getTime();
        System.out.println(simpleDateFormat.format(time1));
    }

    /**
     * add用法
     */
    private static void Test8() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        Date time = calendar.getTime();
        System.out.println(simpleDateFormat.format(time));

        calendar.add(Calendar.DATE, 4);
        Date time1 = calendar.getTime();
        System.out.println(simpleDateFormat.format(time1));
    }

    /**
     * 计算一年中的第几星期是几号
     */
    private static void Test7() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.WEEK_OF_YEAR, 33);
        calendar.set(calendar.DAY_OF_WEEK, 5);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

    /**
     * 计算某一天是一年中的第几星期
     */
    private static void Test6() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("week:" + week);
    }

    /**
     * 格式化输出日期时间
     */
    private static void Test5() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = simpleDateFormat.format(date);
        System.out.println("格式化后的日期:" + result);
    }

    /**
     * Calender和Date的相互转化
     */
    private static void Test4() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        Date date1 = new Date();
        calendar.setTime(date1);

    }

    /**
     * 计算某一月份的最大天数
     */
    private static void Test3() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 7);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println("当前月的最大天数为:" + day);
    }

    private static void Test2() {
        Calendar calendar = Calendar.getInstance();
        long year2017 = calendar.getTimeInMillis();
        calendar.set(1995, 0, 23);
        long year1995 = calendar.getTimeInMillis();
        long days = (year2017 - year1995) / (1000 * 60 * 60 * 24);
        System.out.println("今天和1995年1月23日相隔了" + days);
    }

    private static void Test1() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//年
        int month = calendar.get(Calendar.MONTH) + 1;//月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//日
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;//星期 其实表示一周中的第几天
        System.out.println("现在时间是：" + year + "年" + month + "月" + day + "日，星期" + week);
    }
}
