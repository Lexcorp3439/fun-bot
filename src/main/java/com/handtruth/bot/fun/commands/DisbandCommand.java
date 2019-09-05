package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DisbandCommand implements BaseCommand {
    @Override
    public Action execute(Message msg) {
        Chats chat = service.find(msg.getChatId());
        chat.setFirstAccess(false);
        service.update(chat);
        return new Action(Action.Act.Message,
                "Очень жаль! Надеюсь ты ко мне вернешься((((");
    }
}
