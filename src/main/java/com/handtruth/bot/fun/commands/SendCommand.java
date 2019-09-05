package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.inst.FunBot;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SendCommand implements AdminCommand {
    @Override
    public Action execute(Message msg) {
        if (!hasAccess(msg)) {
            return new Action(Action.Act.Message, "Метод доступен только для АДМИНИСТРАТОРА");
        }

        String[] text = msg.getText().split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < text.length - 1; i++) {
            builder.append(text[i]).append(" ");
        }
        String m = builder.toString();
        FunBot.Instance.sendMsg(m, Long.parseLong(text[text.length - 1]));
        return new Action(Action.Act.No, "");
    }
}
