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

    @PostMapping("/addUser")
    public String createOrder(@RequestBody User user) {
        String key = user.getUsername();
        String value = UUID.randomUUID().toString().replace("-", "");
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 20000, TimeUnit.MILLISECONDS);
        if (flag) {
            log.info("{}锁定成功，开始处理业务", key);
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String lockValue = (String) redisTemplate.opsForValue().get(key);
            if (lockValue != null && lockValue.equals(value)) {
                System.out.println("lockValue==============:" + lockValue);
                List<String> keys = new ArrayList<>();
                keys.add(key);
                log.info("{}解锁成功，结束处理业务", key);
                Long execute = redisTemplate.execute(script, keys, lockValue);
                System.out.println("execute执行结果，1表示执行del，0表示未执行 ====" + execute);
                return "success";
            } else {
                log.info("{}超时", key);
            }
        } else {
            log.info("{}获取锁失败", key);
            return "请稍后再试...";
        }
        return "";
    }
}
