package org.example.Mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Mail implements Runnable {
    private String msg;
    private String to;
    private String subject;

    // private File file;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFile(File file) {
        //  this.file = file;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void outMail() throws MessagingException, IOException {
        String from = "loshaniramsha01@gmail.com"; //sender's email
        String host = "localhost";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("loshaniramsha01@gmail.com", "wpyu boea cyoq blah");
            }
        });


        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(this.subject);
        mimeMessage.setText(this.msg);
        Transport.send(mimeMessage);

        System.out.println("sent");
    }


    @Override
    public void run() {
        if (msg != null) {
            try {
                outMail();
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("not sent. empty msg!");
        }
    }
    }

