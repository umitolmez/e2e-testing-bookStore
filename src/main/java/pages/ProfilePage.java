package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends BasePage{

    @FindBy(id = "delete-record-undefined")
    public List<WebElement> deleteButtons;

    @FindBy(xpath = "//div[@role='rowgroup']/div/div[2]")
    public List<WebElement> titles;
    @FindBy(xpath = "//div[@role='rowgroup']/div/div[3]")
    public List<WebElement>  authors;
    @FindBy(xpath = "//div[@role='rowgroup']/div/div[4]")
    public List<WebElement>  publishers;
}
