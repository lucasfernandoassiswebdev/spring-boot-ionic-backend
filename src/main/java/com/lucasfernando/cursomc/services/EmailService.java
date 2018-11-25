package com.lucasfernando.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucasfernando.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido p);

	void sendEmail(SimpleMailMessage msg);
}
