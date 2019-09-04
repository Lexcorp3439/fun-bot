package com.handtruth.bot.fun.inst;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
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

    public static FunBot Instance = new FunBot();

    public FunBot() {
        super();
        Calendar c = new GregorianCalendar();
        int day = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour > 8) {
            c.add(Calendar.DAY_OF_WEEK, 1);
        } else {
            c.set(Calendar.HOUR_OF_DAY, 8);
        }
        new TimerController(c, "Доброе утро!!!");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                Action action = CommandsController.getInstance().execute(message);
            }
            if (message.hasPhoto()) {

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

    private void sendPhoto(String[] ids, long chat_id) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chat_id);
        System.out.println(Arrays.toString(ids));
        for (int i = 0; i < ids.length - 1; i++) {
            sendPhoto.setPhoto(ids[i]);

            if (i == ids.length - 2) {
                sendPhoto.setCaption(ids[ids.length - 1]);
            }
            try {
                execute(sendPhoto); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public java.io.File downloadImg(String id) {
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
