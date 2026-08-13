package com.fitnessapp.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void enviarCorreoVerificacion(String emailDestino) {

        SimpleMailMessage mensaje = new SimpleMailMessage();

        //1. Remitente
        mensaje.setFrom("pizarroguamankevinpaul@gmail.com");

        // 2. Destinatario: El correo del usuario que se acaba de registrar
        mensaje.setTo(emailDestino);

        // 3. Asunto
        mensaje.setSubject("¡Bienvenido! Verifica tu cuenta de seguridad");

        // 4. El contenido del mensaje y el enlace mágico
        String enlaceVerificacion = "http://localhost:8080/api/auth/verificar?email=" + emailDestino;

        mensaje.setText("Hola,\n\n"
                + "Tu cuenta ha sido creada exitosamente. "
                + "Por medidas de ciberseguridad, necesitamos que verifiques tu identidad antes de darte acceso al Dashboard.\n\n"
                + "Haz clic en el siguiente enlace para activar tu cuenta:\n"
                + enlaceVerificacion + "\n\n"
                + "Si no solicitaste este registro, ignora este mensaje.\n\n"
                + "Un saludo del equipo de Seguridad.");

        // 5. La orden de disparo (aquí es donde Java se conecta a Google y lo envía)
        mailSender.send(mensaje);
    }
}
