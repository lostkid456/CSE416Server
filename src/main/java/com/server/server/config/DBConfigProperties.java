package com.server.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource")
public class DBConfigProperties {
    private String driverclassname;
    private String url;
    private String username;
    private String password;
}
