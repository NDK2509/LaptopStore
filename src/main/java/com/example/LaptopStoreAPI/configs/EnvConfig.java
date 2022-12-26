package com.example.LaptopStoreAPI.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
public class EnvConfig {
    @Value("${secret-key.accessToken}")
    private String accessTokenSecret;
    @Value("${gmt-offset}")
    private int GMTOffset;
    @Value("${expired-time.access-token}")
    private int accessTokenExpiredTime;
    @Value("${expired-time.refresh-token}")
    private int refreshTokenExpiredTime;
}
