package com.moslite.orderbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOGGER.info("Iniciando simulação de envio de e-mail...");
        LOGGER.info(msg.toString());
        LOGGER.info("E-mail enviado com sucesso!");
        LOGGER.info("Fim da simulação de envio de e-mail...");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOGGER.info("Iniciando simulação de envio de e-mail HTML...");
        LOGGER.info(msg.toString());
        LOGGER.info("E-mail HTML enviado com sucesso!");
        LOGGER.info("Fim da simulação de envio de e-mail HTML...");
    }
}
