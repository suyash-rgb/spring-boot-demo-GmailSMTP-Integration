package com.example.spring_boot_demo_GmailSMTP_Integration.controller;

import com.example.spring_boot_demo_GmailSMTP_Integration.DTOs.HtmlEmailRequest;
import com.example.spring_boot_demo_GmailSMTP_Integration.DTOs.SimpleEmailRequest;
import com.example.spring_boot_demo_GmailSMTP_Integration.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/simple-text-email")
    public ResponseEntity<String> sendTextEmail(@RequestBody SimpleEmailRequest emailRequest){
        try{
            emailService.sendSimpleTextEmail(
                    emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getBody()
            );

            return ResponseEntity.ok("Text Email sent successfully!");

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: "+ e.getMessage());
        }
    }

    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<String> SendEmailWithAttachments(@RequestBody HtmlEmailRequest emailRequest){
        try{
            Map<String, InputStreamSource> attachments = new HashMap<>();
            attachments.put("sample.pdf", new FileSystemResource("src/main/resources/sample.pdf"));

            emailService.sendHtmlMailWithAttachment(
                    emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getHtmlBody(),
                    attachments
            );

            return ResponseEntity.ok("Email sent succcessfully");

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: "+e.getMessage());
        }

    }

    @PostMapping("/send-html-email-with-inline-image")
    public ResponseEntity<String> sendHtmlEmailWithImage(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String htmlBody,
            @RequestParam ("image") MultipartFile imageFile,
            @RequestParam String contentId
            ) {

        try{
            Resource image = new ByteArrayResource(imageFile.getBytes());
            emailService.sendEmailWithInlineImage(to, subject, htmlBody, image, contentId);
            return ResponseEntity.ok("Email sent succcessfully");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: "+e.getMessage());
        }
    }
}
