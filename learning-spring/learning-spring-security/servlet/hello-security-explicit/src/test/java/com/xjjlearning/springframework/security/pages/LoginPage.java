package com.xjjlearning.springframework.security.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by xjj on 2022/12/6
 */
public class LoginPage {

    private final WebDriver webDriver;

    private final LoginForm loginForm;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.loginForm = PageFactory.initElements(this.webDriver, LoginForm.class);
    }

    public LoginPage assertAt() {
        assertThat(this.webDriver.getTitle()).isEqualTo("Please sign in");
        return this;
    }

    public LoginForm loginForm() {
        return this.loginForm;
    }

    public static class LoginForm {

        private WebDriver webDriver;

        private WebElement username;

        private WebElement password;

        @FindBy(css = "button[type=submit]")
        private WebElement submit;

        public LoginForm(WebDriver webDriver) {
            this.webDriver = webDriver;
        }

        public LoginForm username(String username) {
            this.username.sendKeys(username);
            return this;
        }

        public LoginForm password(String password) {
            this.password.sendKeys(password);
            return this;
        }

        public HomePage submit() {
            this.submit.click();
            return PageFactory.initElements(this.webDriver, HomePage.class);
        }

    }
}
