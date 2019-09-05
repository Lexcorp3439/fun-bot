package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class TableCommand implements AdminCommand {
    @Override
    public Action execute(Message msg) {
        if (!hasAccess(msg)) {
            return new Action(Action.Act.Message, "Метод доступен только для АДМИНИСТРАТОРА");
        }
        List<Chats> chats = service.all();
        StringBuilder builder = new StringBuilder();
        for (Chats chat: chats) {
            builder.append(chat.toString()).append("\n\n");
        }
        return new Action(Action.Act.Message, builder.toString());
    }
}
