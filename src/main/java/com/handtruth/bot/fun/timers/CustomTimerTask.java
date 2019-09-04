package com.handtruth.bot.fun.timers;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.services.ChatsService;

public class CustomTimerTask extends TimerTask {
    private String msg;
    private ChatsService service;

    public CustomTimerTask(String msg) {
        this.msg = msg;
        service = ChatsService.getInstance();
    }

    public void run() {
        List<Chats> chats = service.findFirstAccess();
        for(Chats chat: chats) {
            FunBot.Instance.sendMsg(msg, chat.getId());
        }
    }
}
