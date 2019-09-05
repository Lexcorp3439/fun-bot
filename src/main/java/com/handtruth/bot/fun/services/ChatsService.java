package com.handtruth.bot.fun.services;

import java.util.List;

import com.handtruth.bot.fun.dao.ChatsDao;
import com.handtruth.bot.fun.dao.ChatsDaoImpl;
import com.handtruth.bot.fun.entities.Chats;
import javafx.util.Pair;

public class ChatsService {
    private static final ChatsService INSTANCE = new ChatsService();
    private ChatsDao chatsDao = new ChatsDaoImpl();

    private ChatsService() {
    }

    public static ChatsService getInstance() {
        return INSTANCE;
    }

    public void save(Chats chats) {
        chatsDao.save(chats);
    }

    public void update(Chats chats) {
        chatsDao.update(chats);
    }

    public void delete(long id, String name) {
        delete(new Chats(id, name));
    }

    public void delete(Chats chats) {
        chatsDao.delete(chats);
    }

    public List<Chats> all() {
        return chatsDao.findAll();
    }

    public void deleteAll() {
        chatsDao.deleteAll();
    }

    public boolean hasChat(long id) {
        return chatsDao.findById(id) != null;
    }

    public Chats find(long id) {
        return chatsDao.findById(id);
    }

    public Pair<Boolean, Boolean>  isExist(long id) {
        Chats chat = find(id);
        boolean exist = chat != null;
        boolean activated = chat != null && chat.isFirstAccess();
        return new Pair<>(exist, activated);
    }

    public List<Chats> findFirstAccess() {
        return chatsDao.findWhereFirstAccess();
    }

    public List<Chats> findSecondAccess() {
        return chatsDao.findWhereSecondAccess();
    }
}
