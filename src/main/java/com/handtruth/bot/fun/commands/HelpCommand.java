package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpCommand implements BaseCommand {
    @Override
    public Action execute(Message msg) {
        return new Action(Action.Act.Message,
                "/start - регистрация на получение сообщений\n" +
                        "/info - краткая информация о боте\n" +
                        "/disband - отменить все функции бота\n" +
                        "/update - информация о том, как стать элитой\n" +
                        "/contact - если возникли вопросы, здесь можно получить контакты"
        );
    }
}
