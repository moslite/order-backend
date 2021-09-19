package com.moslite.orderbackend.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath) {
        try {
            LOGGER.info("Iniciando upload");
            File file = new File(localFilePath);
            s3client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            LOGGER.info("Upload finalizado");
        } catch (AmazonServiceException e) {
            LOGGER.info("Erro ao enviar arquivo para Amazon S3.");
            LOGGER.info("Detalhes: " + e.getMessage());
            LOGGER.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOGGER.info("Erro de Client ao enviar arquivo para Amazon S3.");
        }
    }

}
