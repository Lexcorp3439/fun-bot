package com.handtruth.bot.fun;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.timers.CustomTimerTask;
import com.handtruth.bot.fun.utils.Properties;

public class Main {
    public static void main(String[] args) {
        Properties.setProxy();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(FunBot.Instance);
            System.out.println("Bot start!!!");

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
