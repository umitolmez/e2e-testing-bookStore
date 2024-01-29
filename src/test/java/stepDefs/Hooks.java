package stepDefs;

import bookstore.BookStoreAPI;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.util.logging.Logger;

//Test Base for Before & After and common Steps
public class Hooks {
    private static final Logger logger = Logger.getLogger(Hooks.class.getName());

    @Before
    public void setUp(){
        //UI
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //API
        RestAssured.baseURI = BookStoreAPI.BASE_URL;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        DriverFactory.closeDriver();

        logger.info("Test completed");
    }

}
