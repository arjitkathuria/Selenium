package com.nagp.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "email")
    private WebElement emailTextField;

    @FindBy(id = "passwd")
    private WebElement passwordTextField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password){
        utilities.waitForElementExists(emailTextField, 5);
        emailTextField.sendKeys(userName);
        utilities.waitForElementExists(passwordTextField, 5);
        passwordTextField.sendKeys(password);
        utilities.waitForElementExists(submitButton, 3);
        submitButton.click();
    }

}
