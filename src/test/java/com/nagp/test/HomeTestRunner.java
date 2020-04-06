package com.nagp.test;

import com.nagp.common.Runner.BaseRunner;
import com.nagp.common.pages.CategoryPage;
import com.nagp.common.pages.common.Header;
import com.nagp.common.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTestRunner extends BaseRunner {

    private HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    private CategoryPage categoryPage = PageFactory.initElements(driver, CategoryPage.class);
    private Header header = PageFactory.initElements(driver, Header.class);

    @Test(description = "Verify T-shirt category product and it's heading", priority = 1)
    public void verifyTshirtProductAndHeading() throws Throwable {
        homePage.hoverToWomenLink();
        homePage.clickOnTshirtLink();
        Assert.assertTrue(categoryPage.isHeadingDisplayed(),
                String.format("Heading isn't visible on page %s", categoryPage.getCurrentUrl()));
        String expectedHeading = "T-SHIRTS";
        Assert.assertEquals(categoryPage.getCategoryHeading(), expectedHeading,
                String.format("Heading not as expected on page %s", categoryPage.getCurrentUrl()));
    }

    @Test(description = "Verify search product price", priority = 2)
    public void verifySearchProductAndPrice() {
        String searchProduct = "Faded Short Sleeve T-shirts";
        header.searchProductAndSubmit(searchProduct);
        String expectedPrice = "$20";
        Assert.assertEquals(categoryPage.getProductPrice(), expectedPrice,
                String.format("Price not similar on page %s", categoryPage.getCurrentUrl()));
    }

    @Test(description = "Verify search product heading", priority = 3)
    public void verifySearchProductHeading() {
        String searchProduct = "Faded Short Sleeve T-shirts";
        header.searchProductAndSubmit(searchProduct);
        String expectedPrice = "$20";
        Assert.assertEquals(categoryPage.getProductPrice(), expectedPrice,
                String.format("Price not similar on page %s", categoryPage.getCurrentUrl()));
    }
}
