package stepDefs;

import cucumber.SharedContext;
import driver.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BooksPage;
import pages.LandingPage;
import pages.LoginPage;
import pages.ProfilePage;
import utilities.BrowserUtils;

import java.util.logging.Logger;

//If the number of test steps increases, the steps can be separated by pages. For example: loginPageSteps
public class uiSteps {
    private static final Logger logger = Logger.getLogger(uiSteps.class.getName());
    private final SharedContext sharedContext;

    LandingPage landingPage = new LandingPage();
    BooksPage booksPage = new BooksPage();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();

    public uiSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @Given("Navigate to {string}")
    public void navigateTo(String url) {
        logger.info("Navigating to: " + url);
        DriverFactory.getDriver().get(url);
    }

    @When("Access the Bookstore application")
    public void accessTheBookstoreApplication() {
        BrowserUtils.scrollIntoView(landingPage.bookStoreCard);

        landingPage.bookStoreCard.click();
    }

    @And("Login with user in context")
    public void loginWithUserInContext() {
        logger.info("Logging with Username: "+sharedContext.user.getUserName()+"and Password: "+sharedContext.user.getPassword());

        booksPage.login.click();

        loginPage.usernameInput.sendKeys(sharedContext.user.getUserName());
        loginPage.passwordInput.sendKeys(sharedContext.user.getPassword());

        loginPage.loginBtn.click();
    }

    @Then("Verify number of books added to user")
    public void verifyNumberOfBooksAddedToUser() {
        Assert.assertEquals(sharedContext.numberOfBooks, profilePage.deleteButtons.size());
    }

    @And("Verify book details")
    public void verifyBookDetails() {
        for (int i = 0; i < sharedContext.numberOfBooks; i++) {
            // Retrieve details from the UI
            String title = profilePage.titles.get(i).getText();
            String author = profilePage.authors.get(i).getText();
            String publisher = profilePage.publishers.get(i).getText();

            //Expeceted values(API)
            String expectedTitle = sharedContext.userResponse.getBooks().get(i).getTitle();
            String expectedAuthor = sharedContext.userResponse.getBooks().get(i).getAuthor();
            String expectedPublisher = sharedContext.userResponse.getBooks().get(i).getPublisher();

            //Assertions
            Assert.assertEquals("Title does not match", expectedTitle, title);
            Assert.assertEquals("Author does not match", expectedAuthor, author);
            Assert.assertEquals("Publisher does not match", expectedPublisher, publisher);
        }
    }
}
