// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 19:04:02
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MyTimestramp.java

package com.job.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MyTimestramp
{
    public static Timestamp setTime(Timestamp time)
    {
        SimpleDateFormat dateformatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(dateformatAll.format(time));
    }

    public static Timestamp changeToTimestramp(String time)
    {
        time = (new StringBuilder(String.valueOf(time))).append(":00").toString();
        return Timestamp.valueOf(time);
    }

    public static String changeToShowString(Timestamp time)
    {
        DateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm分");
        return sdf.format(time).toString();
    }
}
