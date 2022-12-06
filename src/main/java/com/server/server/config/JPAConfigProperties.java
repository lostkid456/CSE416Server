package com.server.server.config;

import org.hibernate.Hibernate;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.jpa")
public class JPAConfigProperties {
    private boolean showsql;
    private Hibernate ddlauto;
    private Hibernate namingstrategy;
    private String databaseplatform;
}
