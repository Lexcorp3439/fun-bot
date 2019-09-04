package com.handtruth.bot.fun.services;

import org.junit.AfterClass;
import org.junit.Test;

import com.handtruth.bot.fun.entities.Chats;
import com.handtruth.bot.fun.services.ChatsService;

import static org.junit.Assert.assertTrue;

public class ChatsServiceTest {
    @Test
    public void saveTest() {
        long id = 0;
        ChatsService.getInstance().save(new Chats(id));
        assertTrue(ChatsService.getInstance().isExist(id).getKey());
    }

    @Test
    public void findAllTest() {
        long id = 0;
        System.out.println(ChatsService.getInstance().all().toString());
    }

    @AfterClass
    public static void deleteAll() {
        ChatsService.getInstance().deleteAll();
    }
}
