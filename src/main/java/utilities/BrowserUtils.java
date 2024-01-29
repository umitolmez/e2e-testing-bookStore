package utilities;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

//Actions that apply to every browser, for example scroll down, hover over, click with js etc.
public class BrowserUtils {

    /**
     * Scrolls down until the element is in view using JS
     *
     * @param element WebElement
     */
    public static void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                "arguments[0].scrollIntoView(true);", element);
    }
}
