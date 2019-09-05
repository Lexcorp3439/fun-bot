package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.utils.Action;
import javafx.util.Pair;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand implements BaseCommand {
    String name = "/start";

    @Override
    public Action execute(Message msg) {
        Pair<Boolean, Boolean> answ = service.isExist(msg.getChatId());
        if (answ.getValue()) {
            return new Action(Action.Act.Message,
                    "Вы уже зарегистрированы");
        } else {
            if (answ.getKey()) {
                Chats chat = service.find(msg.getChatId());
                chat.setFirstAccess(true);
                service.update(chat);
            } else {
                service.save(new Chats(msg.getChatId(), msg.getFrom().getFirstName()));
            }
            return new Action(Action.Act.Message,
                    "Привет! Меня завут *Fun Bot*! Теперь я буду писать тебе каждый день :)\n" +
                            "Нажми на /help, чтобы посмотреть все мои возможности!");

        }
    }
}
