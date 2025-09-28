# 📬 Gmail Integration in Spring Boot — Send Emails in 3 Simple Steps

This project demonstrates how to integrate Gmail SMTP into a Spring Boot application to send plain text emails, HTML emails, and emails with attachments (PDFs, images) using MIME and Base64 encoding.

## 🚀 Features

- ✅ Send plain text emails via Gmail
- ✅ Send rich HTML emails with embedded content
- ✅ Attach PDFs and images using MIME protocol
- ✅ Demonstrates Base64 encoding for reliable email transmission
- ✅ REST API endpoints for dynamic email sending

---

## 🧩 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Boot Starter Mail
- Jakarta Mail (JavaMailSender)
- Gmail SMTP
- MIME + Base64 encoding

---

## 🛠️ Setup Instructions

### Step 1: Generate a Gmail App Password

1. Log in to your Google Account.
2. Enable 2-Step Verification.
3. Go to **App Passwords**.
4. Select "Mail" and "Other (Custom name)" → enter "Spring Boot App".
5. Copy the 16-character password (shown only once).

### Step 2: Set Up Your Spring Boot Project

1. Go to [start.spring.io](https://start.spring.io)
2. Add dependencies: **Spring Web**, **Spring Boot Starter Mail**
3. Generate and open the project in your IDE

### Step 3: Configure and Implement Email Logic

1. Add Gmail SMTP settings to `application.properties`:
   ```properties
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your.email@gmail.com
   spring.mail.password=your_app_password
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```

2. - Create a service class to send emails using JavaMailSender
     
3. - Build a REST controller to trigger email sending via JSON requests

