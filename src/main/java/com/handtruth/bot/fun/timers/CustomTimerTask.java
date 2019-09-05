package com.handtruth.bot.fun.timers;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.utils.DefaultRunnable;

public class CustomTimerTask extends TimerTask {
    private String msg;
    private boolean secondAccess;

    private Runnable runnable = null;

    public CustomTimerTask(String msg, boolean secondAccess) {
        setUpFields(msg, secondAccess, new DefaultRunnable(msg, secondAccess));
    }


    public CustomTimerTask(String msg, boolean secondAccess, Runnable runnable) {
        setUpFields(msg, secondAccess, runnable);
    }

    private void setUpFields(String msg, boolean secondAccess, Runnable runnable) {
        this.msg = msg;
        this.secondAccess = secondAccess;
        this.runnable = runnable;
    }


    public void run() {
        runnable.run();
    }
}
