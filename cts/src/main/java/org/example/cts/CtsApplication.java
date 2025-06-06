package org.example.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CtsApplication {

    public static void main(String[] args) {
        System.out.println("中文测试");
        SpringApplication.run(CtsApplication.class, args);

    }

}
