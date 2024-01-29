package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends BasePage{

    @FindBy(xpath = "//span[text()='Login']/parent::li")
    public WebElement login;
}
