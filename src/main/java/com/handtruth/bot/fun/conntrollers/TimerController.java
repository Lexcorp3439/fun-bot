package com.handtruth.bot.fun.conntrollers;

import java.util.Calendar;
import java.util.Timer;

import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.timers.CustomTimerTask;

public class TimerController {
//    private ChatsService service;

    public TimerController(Calendar calendar, String msg) {
//        service = ChatsService.getInstance();
        Timer timer1 = new Timer();
        timer1.schedule(new CustomTimerTask(msg), calendar.getTime(), 86400000);
    }
}
