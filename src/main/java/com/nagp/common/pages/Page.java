package com.nagp.common.pages;

import com.nagp.common.Runner.BaseRunner;
import com.nagp.common.utilities.Utilities;
import org.openqa.selenium.WebDriver;

public class Page extends BaseRunner {
    protected WebDriver driver;
    protected Utilities utilities;

    public Page(WebDriver driver) {
        this.driver = driver;
        utilities = new Utilities(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}
