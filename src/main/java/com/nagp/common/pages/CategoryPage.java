package com.nagp.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends Page {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "cat-name")
    private WebElement categoryHeading;

    @FindBy(css = ".price.product-price")
    private WebElement productPrice;

    @FindBy(css = ".left-block>.product-image-container")
    private List<WebElement> articles;

    public boolean isHeadingDisplayed() {
        utilities.waitForElementExists(categoryHeading, 5);
        return utilities.isElementExist(categoryHeading);
    }

    public String getCategoryHeading(){
        utilities.waitForElementExists(categoryHeading, 5);
        return categoryHeading.getText().trim();
    }

    public String getProductPrice(){
        utilities.waitForElementExists(productPrice, 5);
        return productPrice.getText().trim();
    }

    public int getNoOfArticles(){
        utilities.waitForElementsExists(articles, 5);
        return articles.size();
    }

    public boolean isAllArticlesDisplayed(){
        utilities.waitForElementsExists(articles, 5);
        return utilities.isAllElementsDisplayed(articles);
    }
}
