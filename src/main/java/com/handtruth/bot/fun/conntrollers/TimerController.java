package com.handtruth.bot.fun.conntrollers;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Timer;

import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.timers.CustomTimerTask;
import com.handtruth.bot.fun.utils.KittensRunnable;

import static com.handtruth.bot.fun.tools.BotTools.smile_with_tongue_emoji;

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

    public static void setDefaultTimers() {
        Calendar c = new GregorianCalendar();
        int day = c.get(Calendar.DAY_OF_YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        Calendar c1 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 8, 0, 0);
        if (hour > 8) {
            c1.add(Calendar.DAY_OF_WEEK, 1);
        }

        Calendar c2 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 22, 45, 0);
        if (hour == 22 && minute > 45) {
            c2.add(Calendar.DAY_OF_WEEK, 1);
        }

        Calendar c3 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 12, 30, 0);
        if (hour > 12) {
            c3.add(Calendar.DAY_OF_WEEK, 1);
        }
        String msg = "Дневной кисик";
        String mo = "Доброе утро, Дорогой друг! Надеюсь ты выспался и готов к тяжелому трудовому дню!";
        String night = "Спокойной ночи, Дорогой друг!";

        try {
            Scanner goodmo = new Scanner(new java.io.File("L:\\IdeaProjects\\fun-bot\\src\\main\\resources\\lists\\goodmo.txt"));
            Scanner goodnight = new Scanner(new java.io.File("L:\\IdeaProjects\\fun-bot\\src\\main\\resources\\lists\\goodnight.txt"));
            int i = day % 98;
            int j = day % 67;
            for (int k = 0; k < 98; k++) {
                if (i <= k) {
                    mo = goodmo.nextLine();
                    mo += smile_with_tongue_emoji;
                }
                if (j <= k) {
                    night = goodnight.nextLine();
                    night += smile_with_tongue_emoji;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TimerController.setTimer(c1, mo, false);
        TimerController.setTimer(c2, night, true);
        TimerController.setTimer(c3, msg, false, new KittensRunnable(msg, false));
    }
}
