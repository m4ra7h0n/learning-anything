package com.xjjlearning.maven.accountemail;

public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText);
}
