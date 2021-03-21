package com.codeup.adlister.dao;

import com.codeup.adlister.models.Image;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Images imagesDao;
    static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Images getImagesDao() {
        if (imagesDao == null) {
            imagesDao = new MySQLImagesDao(config);
        }
        return imagesDao;
    }
}
