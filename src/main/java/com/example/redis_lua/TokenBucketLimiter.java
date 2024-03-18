package com.example.redis_lua;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2024/3/4 19:40
 * @Version 1.0
 */
import java.util.Scanner;

public class TokenBucketLimiter {

    private static final int RATE = 10; // 每100ms添加的令牌数
    private static final int MAX_TOKENS = 150; // 令牌桶的最大容量
    private int tokens; // 当前令牌桶中的令牌数
    private int lastTimestamp; // 上一次添加令牌的时间戳

    public TokenBucketLimiter(int tokens) {
        this.tokens = tokens;
        this.lastTimestamp = 0;
    }

    // 添加令牌
    private void addTokens(int timestamp) {
        // 计算时间差
        int delta = timestamp - lastTimestamp;
        // 计算应该添加的令牌数，但最多只能加到最大容量
        int toAdd = (delta / 100) * RATE;
        tokens = Math.min(tokens + toAdd, MAX_TOKENS);
        lastTimestamp = timestamp;
    }

    // 尝试处理请求
    private int tryProcessRequests(int count) {
        // 如果有足够的令牌，则处理所有请求
        if (tokens >= count) {
            tokens -= count;
            return 0;
        } else {
            // 如果令牌不够，则处理部分请求，其余的被限流
            int processed = tokens;
            tokens = 0;
            return count - processed;
        }
    }

    // 主函数
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取行数

        TokenBucketLimiter limiter = new TokenBucketLimiter(100); // 初始令牌数为100
        int limitedCount = 0; // 被限流的请求总数

        for (int i = 0; i < n; i++) {
            int timestamp = scanner.nextInt(); // 读取时刻
            int requestCount = scanner.nextInt(); // 读取请求数量

            // 添加令牌
            limiter.addTokens(timestamp);
            // 处理请求，并计算被限流的数量
            limitedCount += limiter.tryProcessRequests(requestCount);
        }

        System.out.println(limitedCount); // 输出被限流的请求总数
        scanner.close();
    }
}
