package com.handtruth.bot.fun.dao;

import com.handtruth.bot.fun.entities.Admin;

import java.util.List;

public interface AdminDao {
    Admin findById(long id);
    void save(Admin admin);
    void delete(Admin admin);
    void deleteAll();
    void update(Admin admin);
    List<Admin> findAll();
    boolean isAdmin(long id);
}
