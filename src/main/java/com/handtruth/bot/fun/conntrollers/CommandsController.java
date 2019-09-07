package com.handtruth.bot.fun.conntrollers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.handtruth.bot.fun.commands.*;
import com.handtruth.bot.fun.inst.FunBot;
import javafx.util.Pair;
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

    public InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    private CommandsController() {
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
        String[] text = msg.getText().split(" ");
        String txt = text[0];

        BotTools.dropKeys(inlineKeyboardMarkup);
        if (!isCommand(txt)) {
            return new Action(Action.Act.No, null);
        }
        Command command = Command.valueOf(txt.replace("/", ""));

        switch (command) {
            case start:
                return new StartCommand().execute(msg);
            case info:
                return new InfoCommand().execute(msg);
            case help:
                return new HelpCommand().execute(msg);
            case update:
                return new UpdateCommand().execute(msg);
            case disband:
                return new DisbandCommand().execute(msg);
            case contact:
                return new ContactCommand().execute(msg);
            case table:
                return new TableCommand().execute(msg);
            case send:
                return new SendCommand().execute(msg);
            case access:
                return new AccessCommand().execute(msg);
            case ahelp:
                return new AhelpCommand().execute(msg);
            default:
                return new Action(Action.Act.Message, "Команды не существует");
        }
    }


    public Action callback(CallbackQuery callbackQuery) {
        BotTools.dropKeys(inlineKeyboardMarkup);
        String back = callbackQuery.getData();
        if (back.equals(BotTools.thumbs_up_sign)) {
            System.out.println("Отлично от " + callbackQuery.getFrom().getFirstName());
        } else {
            System.out.println("Плохо от " + callbackQuery.getFrom().getFirstName());
        }
        return new Action(Action.Act.Message, "Спасибо за отзыв!");
    }
}
