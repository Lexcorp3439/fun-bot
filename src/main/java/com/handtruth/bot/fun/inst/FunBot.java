package com.handtruth.bot.fun.inst;

import java.io.FileNotFoundException;
import java.util.*;

import com.handtruth.bot.fun.tools.BotTools;

import com.handtruth.bot.fun.utils.KittensRunnable;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import com.handtruth.bot.fun.conntrollers.CommandsController;
import com.handtruth.bot.fun.conntrollers.TimerController;
import com.handtruth.bot.fun.utils.Action;

public class FunBot extends TelegramLongPollingBot {
    private static final String TAG = "FunBot";
    private static String USERNAME = "j_for_fun_bot";
    private static String TOKEN = "829035322:AAGSF5TX_yHtDx4TKxqvNKFVU-31PwoZWns";

    private String smile_emoji = EmojiParser.parseToUnicode("\uD83D\uDE0B");

    public static FunBot Instance = new FunBot();

    public FunBot() {
        super();
        Calendar c = new GregorianCalendar();
        int day = c.get(Calendar.DAY_OF_YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.HOUR_OF_DAY);

        Calendar c1 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 8, 0, 0);
        if (hour > 8) {
            c1.add(Calendar.DAY_OF_WEEK, 1);
        }

        Calendar c2 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 22, 0, 0);
        if (hour == 23 && minute > 0) {
            c2.add(Calendar.DAY_OF_WEEK, 1);
        }

        Calendar c3 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 13, 0, 0);
        if (hour > 13) {
            c3.add(Calendar.DAY_OF_WEEK, 1);
        }
        String msg = "Дневной кисик";
        String mo = "Доброе утро, Дорогой друг! Надеюсь ты выспался и готов к тяжелому трудовому дню!";
        String night = "Спокойной ночи, Дорогой друг!";

        try {
            Scanner goodmo = new Scanner(new java.io.File("L:\\IdeaProjects\\fun-bot\\src\\main\\resources\\lists\\goodmo.txt"));
            Scanner goodnight = new Scanner(new java.io.File("L:\\IdeaProjects\\fun-bot\\src\\main\\resources\\lists\\goodnight.txt"));
            int i = day % 98;
            int j = day % 67;
            for (int k = 0; k < 98; k++) {
                if (i <= k) {
                    mo = goodmo.nextLine();
                    mo += smile_emoji;
                }
                if (j <= k) {
                    night = goodnight.nextLine();
                    night += smile_emoji;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TimerController.setTimer(c1, mo, false);
        TimerController.setTimer(c2, night, true);
        TimerController.setTimer(c3, msg, false, new KittensRunnable(msg, false));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getChatId() != 529797809) {
                forwardMsg(message);
            }
            if (message.hasText()) {
                Action action = CommandsController.getInstance().execute(message);
                if (action.act == Action.Act.Message) {
                    sendMsg(action.messages, message.getChatId());
                }
            }
            if (message.hasPhoto()) {
                String id = Objects.requireNonNull(message.getPhoto().stream().max(Comparator.comparing(PhotoSize::getFileSize))
                        .orElse(null)).getFileId();
                BotTools.downloadImg(id, message.getChatId());
            }
        }
        if (update.hasCallbackQuery()) {

        }


    }


    public void sendMsg(String text, long chatID) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setReplyMarkup(CommandsController.getInstance().inlineKeyboardMarkup);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        System.out.println(chatID);
        System.out.println(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            BotLogger.error("Could not send message", TAG, e);
        }
    }

    public void sendPhoto(String path, String msg, long chat_id) {
        SendPhoto sendPhoto = new SendPhoto();
        java.io.File file = new java.io.File(path);

        sendPhoto.setChatId(chat_id);
        sendPhoto.setPhoto(file);
        sendPhoto.setCaption(msg);

        try {
            execute(sendPhoto); // Call method to send the photo with caption
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void forwardMsg(Message msg) {
        ForwardMessage forwardMessage = new ForwardMessage((long) 529797809, msg.getChatId(), msg.getMessageId());
        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public java.io.File image(String id) {
        try {
            File file = execute(new GetFile().setFileId(id));
            System.out.println(file.getFileUrl(getBotToken()));
            return downloadFile(file);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
