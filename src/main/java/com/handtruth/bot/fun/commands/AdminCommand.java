package com.handtruth.bot.fun.commands;

import com.handtruth.bot.fun.services.AdminService;
import com.handtruth.bot.fun.utils.Action;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface AdminCommand extends BaseCommand {
    AdminService adminService = AdminService.getInstance();

    default boolean hasAccess(Message msg) {
        return adminService.isAdmin(msg.getChatId());
    }
}
