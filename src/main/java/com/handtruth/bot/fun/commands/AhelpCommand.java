package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AhelpCommand implements AdminCommand {
    @Override
    public Action execute(Message msg) {
        if (!hasAccess(msg)) {
            return new Action(Action.Act.Message, "Метод доступен только для АДМИНИСТРАТОРА");
        }
        return new Action(Action.Act.Message,
                "/access + <bool> + id -> изменить доступ\n" +
                        "/table -> таблица участников\n" +
                        "/send + <msg> + id -> отправить сообщение участнику\n" +
                        "");
    }
}
