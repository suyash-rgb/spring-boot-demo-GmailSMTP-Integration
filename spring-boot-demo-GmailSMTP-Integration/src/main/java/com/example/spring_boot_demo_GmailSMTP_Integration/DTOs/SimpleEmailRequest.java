package com.example.spring_boot_demo_GmailSMTP_Integration.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SimpleEmailRequest {
    private String to;

    private String subject;

    private String body;

}
