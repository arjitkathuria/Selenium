package com.nagp.common.pages.common;

import com.nagp.common.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends Page {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_query_top")
    private WebElement searchInputTextField;

    @FindBy(name = "submit_search")
    private WebElement searchButton;

    @FindBy(className = "logout")
    private WebElement logoutButton;

    @FindBy(className = "account")
    private WebElement accountName;

    public void searchProductAndSubmit(String productName) {
        utilities.waitForElementExists(searchInputTextField, 5);
        searchInputTextField.sendKeys(productName);
        searchButton.click();
    }

    public void logout(){
        utilities.waitForElementExists(logoutButton, 5);
        logoutButton.click();
    }

    public boolean isAccontNameDisplayed(){
        utilities.waitForElementExists(logoutButton, 5);
        return utilities.isElementExist(accountName);
    }
}
