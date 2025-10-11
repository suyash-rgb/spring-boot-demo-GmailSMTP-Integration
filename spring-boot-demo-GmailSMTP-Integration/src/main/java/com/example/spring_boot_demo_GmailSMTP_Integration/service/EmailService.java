package com.example.spring_boot_demo_GmailSMTP_Integration.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleTextEmail(String to,
                                    String subject,
                                    String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, false);

        javaMailSender.send(message);
    }

    public void sendHtmlMailWithAttachment (String to,
                                            String subject,
                                            String htmlBody,
                                            Map<String, InputStreamSource> attachments)throws MessagingException{

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(htmlBody, true);

        //attach files
        for(Map.Entry<String, InputStreamSource> entry: attachments.entrySet()){
            String fileName = entry.getKey();
            InputStreamSource source = entry.getValue();
            mimeMessageHelper.addAttachment(fileName, source);
        }

        javaMailSender.send(message);
    }

    public void sendEmailWithInlineImage(String to,
                                         String subject,
                                         String htmlBody,
                                         Resource imageResource,
                                         String contentId ) throws MessagingException, IOException {
       MimeMessage mimeMessage = javaMailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
       helper.setFrom(fromEmail);
       helper.setTo(to);
       helper.setSubject(subject);
       helper.setText(htmlBody, true);

       //method for adding image as inline-image
        helper.addInline(contentId, imageResource);

        javaMailSender.send(mimeMessage);

    }



}
