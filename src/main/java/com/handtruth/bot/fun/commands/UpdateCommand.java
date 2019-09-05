package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UpdateCommand implements BaseCommand {
    @Override
    public Action execute(Message msg) {
        return new Action(Action.Act.Message,
                "Чтобы получить дополнительную функцию, необходимо перевести на карту -> " +
                        " *4276 2200 1342 5759* <- " +
                        "*29.99* рублей и скинуть чек в наш диалог!\n\n Если операция прошла успешно, функция будет вам доступна!"
        );
    }
}
