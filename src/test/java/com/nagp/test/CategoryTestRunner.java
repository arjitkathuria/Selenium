package com.nagp.test;

import com.nagp.common.Runner.BaseRunner;
import com.nagp.common.pages.CategoryPage;
import com.nagp.common.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTestRunner extends BaseRunner {

    private HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    private CategoryPage categoryPage = PageFactory.initElements(driver, CategoryPage.class);

    @Test(description = "Verify total no of products in category dresses", priority = 1)
    public void verifyTotalNoOfProductInDressCategory() {
        homePage.clickOnDressesLink();
        int expectedProducts = 5;
        Assert.assertEquals(categoryPage.getNoOfArticles(), expectedProducts,
                String.format("No of product not as expected in page %s", categoryPage.getCurrentUrl()));
    }

    @Test(description = "Verify title of category dresses", priority = 2)
    public void verifyTiltleofDressCategory() {
        String expectedTitle = "Dresses - My Store";
        Assert.assertEquals(categoryPage.getTitle(), expectedTitle,
                String.format("Title mismatch in page %s", categoryPage.getCurrentUrl()));
    }

    @Test(description = "Verify all yhe product article present in category dresses", priority = 3)
    public void verifyAllTheProductInDressCategory() {
        Assert.assertTrue(categoryPage.isAllArticlesDisplayed(),
                String.format("All elements not displayed %s", categoryPage.getCurrentUrl()));
    }

}
