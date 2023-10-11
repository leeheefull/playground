package com.leeheefull.domain.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = [
        "com.leeheefull.domain",
    ],
)
@EntityScan(
    basePackages = [
        "com.leeheefull.domain",
    ],
)
class JpaConfig