package pages;

import driver.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

}
