package com.handtruth.bot.fun.inst;

import java.io.FileNotFoundException;
import java.util.*;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.services.ChatsService;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import com.handtruth.bot.fun.conntrollers.CommandsController;
import com.handtruth.bot.fun.conntrollers.TimerController;
import com.handtruth.bot.fun.utils.Action;

public class FunBot extends TelegramLongPollingBot {
    private static final String TAG = "FunBot";
    private static String USERNAME = "j_for_fun_bot";
    private static String TOKEN = "829035322:AAGSF5TX_yHtDx4TKxqvNKFVU-31PwoZWns";
    private static long myID = 529797809;

    private String smile_emoji = EmojiParser.parseToUnicode("\uD83D\uDE0B");

    public static FunBot Instance = new FunBot();

    public FunBot() {
        super();

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getChatId() != myID) {
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
            Action action = CommandsController.getInstance().callback(update.getCallbackQuery());
            if (action.act == Action.Act.Message) {
                sendMsg(action.messages, update.getCallbackQuery().getMessage().getChatId());
            }
        }


    }


    public void sendMsg(String text, long chatID) {
        sendMsg(text, chatID, CommandsController.getInstance().inlineKeyboardMarkup);
    }

    public void sendMsg(String text, long chatID, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(chatID);
        sendMessage.setChatId(myID);
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
        sendPhoto(path, msg, chat_id, CommandsController.getInstance().inlineKeyboardMarkup);
    }

    public void sendPhoto(String path, String msg, long chat_id, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendPhoto sendPhoto = new SendPhoto();
        java.io.File file = new java.io.File(path);

        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
//        sendPhoto.setChatId(chat_id);
        sendPhoto.setChatId(myID);
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

    private void whatsNew() {
        String news = "Всем привет! Я обновился!\n\n" +
                "Теперь вы можете оценивать дневных кисиков: плохие фотки я буду удолять, а хорошие оставлять!\n" +
                "Также теперь иногда я буду присылать вам нежданный мемес, который в также можете оценивать!\n" +
                "Я научился по-разному желать вам доброе утро и спокойной ночи, жду поздравлений)))\n" +
                "Да здравствует Fun Bot v1.1";
        for (Chats chat : ChatsService.getInstance().all()) {
            sendMsg(news, chat.getId());
        }

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
