package com.handtruth.bot.fun.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.handtruth.bot.fun.inst.FunBot;

public class BotTools {

    public static void setKeys(InlineKeyboardMarkup inlineKeyboardMarkup, List<String> list) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        for (String button : list) {
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(button).setCallbackData(button));
        }
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
    }

    public static void dropKeys(InlineKeyboardMarkup inlineKeyboardMarkup) {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
    }

    public void downloadImg(String imgId, long chatId) {
        String resource = "";
        String filePath = resource.concat("/" + imgId +"FROM" + chatId + ".jpg");
        File file = FunBot.Instance.downloadImg(imgId);

        File f = new java.io.File(filePath);
        if (file.renameTo(f)) {
            System.out.println("Файл успешно перемещён!");
        } else {
            if (file.delete()) {
                System.out.println("Файл был успешно удален");
            } else {
                System.out.println("Файл НЕ УДАЛЕН");
            }
        }
    }

}
