package com.example.redis_lua.controller;

import com.example.redis_lua.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Description TODO
 * @Auther chengjiahui
 * @Date 2023/3/3 19:55
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private DefaultRedisScript<Long> script;

    @PostConstruct
    public void init() {
        script = new DefaultRedisScript<Long>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("niceyoo.lua")));
    }

//    @PostMapping("/addUser")
//    public String createOrder(@RequestBody User user) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 80,
//                300, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000));
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        String key = user.getUsername();
//        String value = UUID.randomUUID().toString().replace("-", "");
//        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 2000, TimeUnit.MILLISECONDS);
//        if (flag) {
//            log.info("{}锁定成功，开始处理业务", key);
//            try {
//                Thread.sleep(1000 * 1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            String lockValue = (String) redisTemplate.opsForValue().get(key);
//            if (lockValue != null && lockValue.equals(value)) {
//                System.out.println("lockValue==============:" + lockValue);
//                List<String> keys = new ArrayList<>();
//                keys.add(key);
//                log.info("{}解锁成功，结束处理业务", key);
//                Long execute = redisTemplate.execute(script, keys, lockValue);
//                System.out.println("execute执行结果，1表示执行del，0表示未执行 ====" + execute);
//                return "success";
//            } else {
//                log.info("{}超时", key);
//            }
//        } else {
//            log.info("{}获取锁失败", key);
//            return "请稍后再试...";
//        }
//        return "";
//    }

    @PostMapping("/addUser")
    public String createOrder(@RequestBody User user) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);


        String key = user.getUsername();
//        String value = UUID.randomUUID().toString().replace("-", "");
//        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 2000, TimeUnit.MILLISECONDS);
        RLock lock = redisson.getLock(key);
        try {
            // 尝试获取锁，等待最多 10 秒，锁在 60 秒后自动释放
            if (lock.tryLock(1, 60, TimeUnit.SECONDS)) {
                try {
                    // 业务逻辑
                    System.out.println("Lock acquired and operations are safe to perform");
                    Thread.sleep(10000);
                } finally {
                    // 确保锁被释放
                    lock.unlock();
                    System.out.println("Lock released");
                    return "加锁成功";
                }
            } else {
                System.out.println("Unable to acquire lock");
                return "加锁失败";
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to acquire lock: " + e.getMessage());
        } finally {
            // 关闭 Redisson 客户端连接
            redisson.shutdown();
        }
        return "";
    }
}
