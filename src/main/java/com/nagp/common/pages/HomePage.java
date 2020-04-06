package com.nagp.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(css = "a[title='Women']")
    private WebElement womenLink;

    @FindBy(css = "a[title='T-shirts']")
    private WebElement tShirtLink;

    @FindBy(css = ".sf-menu.clearfix>li:nth-of-type(2)>a")
    private WebElement dressesLink;

    @FindBy(className = "login")
    private WebElement signInButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void hoverToWomenLink() {
        utilities.moveToElement(womenLink);
    }

    public void clickOnTshirtLink() {
        utilities.waitForElementExists(tShirtLink, 5);
        tShirtLink.click();
    }

    public void clicklOnSignInButton(){
        utilities.waitForElementExists(signInButton, 5);
        signInButton.click();
    }

    public void clickOnDressesLink(){
        utilities.waitForElementExists(dressesLink, 5);
        dressesLink.click();
    }
}
