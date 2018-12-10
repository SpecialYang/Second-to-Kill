package com.specialyang.secondstokill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Fan Yang in 2018/12/5 1:43 PM.
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    private String host;

    private int port;

    private int timeout;

    private String password;

    private int poolMaxTotal;

    private int poolMaxIdle;

    private int poolMaxWait;

}
