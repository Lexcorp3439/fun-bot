package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class InfoCommand implements BaseCommand{
    @Override
    public Action execute(Message msg) {
        return new Action(Action.Act.Message,
                "Я предназначен для того, чтобы желать тебе доброе утро каждый день (хоть кто-то это будет делать)!\n\n" +
                        "Но если ты захочешь, чтобы я еще желал спокойной ночи, то используй команду /update и узнай как это сделать.\n\n" +
                        "Текущий дневной распорядок:\n" +
                        "   *8:00* - я желаю вам доброе утро\n" +
                        "   *12:30* - дневной кисик\n" +
                        "   *22:45* - я желаю вам спокойной ночи, если вы стали элитой\n\n" +
                        " В дальнейшем будут появляться новые функции, о которых ты обязательно будешь узнавать!" +
                        " Чтобы увидеть перечень всех команд, введи /help");
    }
}
