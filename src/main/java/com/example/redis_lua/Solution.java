package com.example.redis_lua;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2024/3/28 20:37
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            map.put(String.valueOf(i), "1");
        }
        Set<String> set = new HashSet<>();
        set.add("1");
    }

}
