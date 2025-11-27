package com.app.kinlock.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendHtml(String to, String subject, String htmlBody) {
        try {
            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            helper.setFrom("youraccount@gmail.com");
            mailSender.send(mime);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send mail", e);
        }
    }

    public void sendText(String to, String subject, String text) {
        sendHtml(to, subject, text.replaceAll("\n", "<br>"));
    }
}
