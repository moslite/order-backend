package com.moslite.orderbackend;

import com.moslite.orderbackend.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class OrderBackendApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(OrderBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {
        s3Service.uploadFile("c:\\users\\Murilo\\Desktop\\upload_test_s3.jpg");
    }
}
