package com.handtruth.bot.fun.services;

import com.handtruth.bot.fun.dao.AdminDao;
import com.handtruth.bot.fun.dao.AdminDaoImpl;

public class AdminService {
    private static final AdminService INSTANCE = new AdminService();
    private AdminDao adminDao = new AdminDaoImpl();

    private AdminService() {
    }

    public static AdminService getInstance() {
        return INSTANCE;
    }

    public boolean isAdmin(long id) {
        return adminDao.isAdmin(id);
    }

}
