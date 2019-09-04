package com.handtruth.bot.fun.conntrollers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.tools.BotTools;
import com.handtruth.bot.fun.utils.Action;
import com.handtruth.bot.fun.utils.Command;

public class CommandsController {
    private static final CommandsController INSTANCE = new CommandsController();

    private ChatsService service;
    public InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    private CommandsController() {
        service = ChatsService.getInstance();
    }

    public static CommandsController getInstance() {
        return INSTANCE;
    }

    private boolean isCommand(String text) {
        for (Command command : Command.values()) {
            if (command.getName().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public Action execute(Message msg) {
        BotTools.dropKeys(inlineKeyboardMarkup);
        if (!isCommand(msg.getText())) {
            return new Action(Action.Act.No, null);
        }
        Command command = Command.valueOf(msg.getText());
        Calendar c = new GregorianCalendar();
        int day = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        switch (command) {
            case start:
                if (service.isExist(msg.getChatId())) {
                    return new Action(Action.Act.Message,
                            "Вы уже зарегистрированы");
                } else {
                    service.save(new Chats(msg.getChatId()));
                    return new Action(Action.Act.Message,
                            "Привет! Теперь я буду писать тебе каждый день :)");
                }
            case info:
                return new Action(Action.Act.Message,
                        "Я предназначен для того, чтобы желать тебе доброе утро каждый день (хоть кто-то это будет делать)!\n " +
                                "Но если ты захочешь, чтобы я еще желал спокойной ночи, то используй команду /update) и узнай как это сделать." +
                                "Тогда ты попадешь в элиту и будешь получать от меня сообщения чаще)))\n" +
                                "В дальнейшем буду поялвяться новые функции, к оторых ты обязательно будешь узнавать!" +
                                "Чтобы увидеть перечень всех команд, введи /help");
            case help:
                return new Action(Action.Act.Message,
                        "/start - регистрация на получение утренних сообщений\n" +
                                "/info - краткая информация о боте\n" +
                                "/disband - отменить все функции бота\n" +
                                "/update - информация по дополнительным функциям бота\n"
                );
            case update:
                new Action(Action.Act.Message,
                        "Чтобы получить дополнительную функцию, необходимо перевести на карту: \n" +
                                "\n" +
                                "50 рублей и скинуть чек в наш диалог! Если операция прошла успешно, функция будет вам доступна!"
                );
            case disband:
                service.delete(msg.getChatId());
                return new Action(Action.Act.Message,
                        "Очень жаль! Надеюсь ты ко мне вернешься((((");
            default:
                return new Action(Action.Act.Message, "Команды не существует");
        }
    }


    public Action callback(CallbackQuery callbackQuery) {
        BotTools.dropKeys(inlineKeyboardMarkup);
        return new Action(Action.Act.Message, "Получено!");
    }
}
