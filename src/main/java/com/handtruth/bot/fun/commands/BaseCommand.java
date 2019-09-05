package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.services.ChatsService;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface BaseCommand {
    ChatsService service = ChatsService.getInstance();

    Action execute(Message msg);
}
