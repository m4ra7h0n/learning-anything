package com.xjjlearning.apache.maven;

public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText);
}
