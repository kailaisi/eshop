package com.kailaisi.eshop.dao;

/**
 * redis本身有很多功能
 */
public interface RedisDao {
    void set(String key, String value);

    String get(String key);
}
