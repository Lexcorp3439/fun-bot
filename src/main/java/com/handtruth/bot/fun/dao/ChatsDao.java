package com.handtruth.bot.fun.dao;

import java.util.List;

import com.handtruth.bot.fun.entities.Chats;

public interface ChatsDao {
    Chats findById(long id);
    void save(Chats chat);
    void delete(Chats chat);
    void deleteAll();
    List<Chats> findAll();
    List<Chats> findWhereFirstAccess();
    List<Chats> findWhereSecondAccess();
}
