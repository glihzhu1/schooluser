package com.all.management.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * = UserApplication
 *
 * TODO Auto-generated class documentation
 *
 */
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.all.management.user.repository")
//@EntityScan(basePackages = "com.all.management.user.model")
public class UserApplication {

    /**
     * TODO Auto-generated method documentation
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}