package com.xjjlearning.apache.maven.accountemail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Component
public class config {
    @Bean
    public JavaMailSender javaMailSender(){
         return new JavaMailSender() {
             @Override
             public MimeMessage createMimeMessage() {
                 return null;
             }

             @Override
             public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
                 return null;
             }

             @Override
             public void send(MimeMessage mimeMessage) throws MailException {

             }

             @Override
             public void send(MimeMessage... mimeMessages) throws MailException {

             }

             @Override
             public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

             }

             @Override
             public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

             }

             @Override
             public void send(SimpleMailMessage simpleMessage) throws MailException {

             }

             @Override
             public void send(SimpleMailMessage... simpleMessages) throws MailException {

             }
         };
   }
}
