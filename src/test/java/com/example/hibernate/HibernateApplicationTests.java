package com.example.hibernate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HibernateApplication.class)
class HibernateApplicationTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void contextLoads() {
        logger.info("Test is running");
    }

}
