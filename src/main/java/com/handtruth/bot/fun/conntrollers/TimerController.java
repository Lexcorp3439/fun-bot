package com.handtruth.bot.fun.conntrollers;

import java.util.Calendar;
import java.util.Timer;

import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.timers.CustomTimerTask;

public class TimerController {
//    private ChatsService service;

    public static void setTimer(Calendar calendar, String msg, boolean secondAccess) {
//        service = ChatsService.getInstance();
        Timer timer1 = new Timer();
        timer1.schedule(new CustomTimerTask(msg, secondAccess), calendar.getTime(), 86400000);
    }

    public static void setTimer(Calendar calendar, String msg, boolean secondAccess, Runnable runnable) {
//        service = ChatsService.getInstance();
        Timer timer1 = new Timer();
        timer1.schedule(new CustomTimerTask(msg, secondAccess, runnable), calendar.getTime(), 86400000);
    }
}
