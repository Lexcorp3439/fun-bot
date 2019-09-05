package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AccessCommand implements AdminCommand {
    @Override
    public Action execute(Message msg) {
        if (!hasAccess(msg)) {
            return new Action(Action.Act.Message, "Метод доступен только для АДМИНИСТРАТОРА");
        }

        String[] text = msg.getText().split(" ");
        boolean set = Boolean.parseBoolean(text[1]);
        long id = Long.parseLong(text[2]);

        Chats chat = service.find(id);
        if (chat != null) {
            chat.setSecondAccess(set);
            if (set) {
                FunBot.Instance.sendMsg("Добро пожаловать в ЭЛИТУ! Теперь я буду желать тебе спокойной ночи каждый вечер в *23:00*)))", chat.getId());
            } else {
                FunBot.Instance.sendMsg("Извините, но вы лешились статуса Элиты, мне очень жаль(((", chat.getId());
            }
        }
        return new Action(Action.Act.Message, "Изменено");
    }
}
