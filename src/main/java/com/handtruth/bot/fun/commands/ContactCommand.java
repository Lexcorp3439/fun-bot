package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ContactCommand implements BaseCommand {
    @Override
    public Action execute(Message msg) {
        return new Action(Action.Act.Message,
                "По любым вопросам: @handtruth"
        );
    }
}
