package com.moslite.orderbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOGGER.info("Iniciando envio de e-mail...");
        mailSender.send(msg);
        LOGGER.info("E-mail enviado com sucesso!");
        LOGGER.info("Fim do envio de e-mail...");
    }
}
