package com.handtruth.bot.fun.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.services.ChatsService;

public class KittensRunnable implements Runnable {
    private ChatsService service = ChatsService.getInstance();
    private String msg;
    private boolean secondAccess;

    public KittensRunnable(String msg, boolean secondAccess) {
        this.msg = msg;
        this.secondAccess = secondAccess;
    }

    @Override
    public void run() {
        List<Chats> chats;
        Calendar c = new GregorianCalendar();
        String resources = "L:\\IdeaProjects\\fun-bot\\src\\main\\resources\\kittens\\";
        if (secondAccess) {
            chats = service.findSecondAccess();
        } else {
            chats = service.findFirstAccess();
        }
        String path = resources + c.get(Calendar.DAY_OF_YEAR) % 91 + ".jpg";
        for (Chats chat : chats) {
            FunBot.Instance.sendPhoto(path, msg, chat.getId());
        }
    }
}
