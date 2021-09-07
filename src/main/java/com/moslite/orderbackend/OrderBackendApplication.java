package com.moslite.orderbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class OrderBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {

    }
}
