package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{

    @FindBy(xpath = "//h5[text()=\"Book Store Application\"]/parent::div")
    public WebElement bookStoreCard;
}
