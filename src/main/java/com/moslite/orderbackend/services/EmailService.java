package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPassword(Cliente cliente, String newPass);
}
