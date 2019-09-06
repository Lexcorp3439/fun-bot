package com.handtruth.bot.fun.utils;

import java.util.List;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.services.ChatsService;

public class DefaultRunnable implements Runnable {
    private ChatsService service = ChatsService.getInstance();
    private String msg;
    private boolean secondAccess;

    public DefaultRunnable(String msg, boolean secondAccess) {
        this.msg = msg;
        this.secondAccess = secondAccess;
    }

    @Override
    public void run() {
        List<Chats> chats;
        if (secondAccess) {
            chats = service.findSecondAccess();
        } else {
            chats = service.findFirstAccess();
        }
        for (Chats chat : chats) {
            FunBot.Instance.sendMsg(msg, chat.getId());
        }

//        FunBot.Instance.sendMsg(msg, 529797809);
    }
}
