package com.group2.watchstorecover.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
    void setTimeToLive(String key, Long time);

    void hashSet(String key, String field, Object value);

    Object hashGet(String key, String field);

    void hasDel(String key);

    List<Object> hashGetByFieldPrefix(String key, String fieldPrefix);

    Set<String> getByFieldPrefix(String key);

    boolean hashExists(String key, String field);

    void delete();

    void delete(String key, String field);

    void delete(String key, List<String> fields);
}
