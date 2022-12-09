package com.xjjlearning.springframework.security.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * created by xjj on 2022/12/6
 */
public class HomePage {

    private final WebDriver webDriver;

    @FindBy(id = "logout")
    private WebElement logout;

    public static LoginPage to(WebDriver driver, int port) {
        driver.get("http://localhost:" + port + "/");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage assertAt() {
        assertThat(this.webDriver.getTitle()).isEqualTo("Hello Security!");
        return this;
    }

    public LoginPage logout() {
        this.logout.click();
        return PageFactory.initElements(this.webDriver, LogoutConfirmPage.class).logout();
    }

}
