package com.xjj.learning.maven.accountemail;

public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText);
}
