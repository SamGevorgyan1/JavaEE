package com.util;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSenderYandex {
    private static final String username = "bookingsystembook@yandex.ru";
    private static final String password = "vsnbobxaeykkotvq";

    public static void sendEmail(String to, String subject, String text) {
        String host = "smtp.yandex.ru";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            EmailUtil.sendEmail(session, to, subject, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class EmailUtil {
    static final String fromEmail = "bookingsystembook@yandex.ru"; //requires valid gmail id

    public static void sendEmail(Session session, String toEmail, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
