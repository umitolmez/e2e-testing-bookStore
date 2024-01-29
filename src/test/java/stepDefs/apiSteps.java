package stepDefs;

import POJOs.*;
import bookstore.BookStoreAPI;

import cucumber.SharedContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class apiSteps {

    private static final Logger logger = Logger.getLogger(apiSteps.class.getName());
    private final SharedContext sharedContext;

    public apiSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @Given("Create a random user")
    public void createRandomUser() {
        logger.info("Creating a Random User");

        String randomUserName = sharedContext.dataGenerator.generateUserName();
        String password = "r6i#5pS23|";

        sharedContext.user = new User(randomUserName, password);
        //System.out.println(new Gson().toJson(user));

        InputStream createUserSchema= getClass().getClassLoader().getResourceAsStream("JsonSchemas/CreateUserResponseSchema.json");
        assert createUserSchema != null;

        sharedContext.userResponse =
                given().body(sharedContext.user)
                        .when().post(BookStoreAPI.USER)
                        .then().log().ifError()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("username", equalTo(randomUserName))
                        .body(JsonSchemaValidator.matchesJsonSchema(createUserSchema))
                        .extract().as(UserResponse.class);


    }

    @And("Generate Authentication Token")
    public void generateAuthenticationToken() {
        InputStream tokenSchema=getClass().getClassLoader().getResourceAsStream("JsonSchemas/TokenResponseSchema.json");
        assert tokenSchema != null;

        sharedContext.generateAutTokenResponse=
                given().body(sharedContext.user)
                        .when().post(BookStoreAPI.GENERATE_TOKEN)
                        .then().statusCode(200)
                        .contentType(ContentType.JSON)
                        .body(JsonSchemaValidator.matchesJsonSchema(tokenSchema))
                        .extract().as(TokenResponse.class);

        logger.info("Aut Token generated for Username: " + sharedContext.user.getUserName());
    }

    @When("Get list of books")
    public void getListOfBooks() {
        InputStream booksSchema = getClass().getClassLoader().getResourceAsStream("JsonSchemas/BooksResponseSchema.json");
        assert booksSchema != null;

        sharedContext.booksResponse =
                given().when().get(BookStoreAPI.BOOKS)
                        .then().statusCode(200)
                        .contentType(ContentType.JSON)
                        .body(JsonSchemaValidator.matchesJsonSchema(booksSchema))
                        .extract().as(BooksResponse.class);

        logger.info("All Books Fetched.");
    }

    @And("Filter by publisher: {string}")
    public void filterByPublisher(String publisher) {
        sharedContext.filteredBookIsbns = sharedContext.booksResponse.getBooks().stream()
                .filter(book -> publisher.equals(book.getPublisher()))
                .map(Book::getIsbn)
                .collect(Collectors.toList());
    }

    @And("Post books to the User in context")
    public void postBooksToTheUserInContext() {
        PostBook postBook = new PostBook();
        postBook.setUserId(sharedContext.userResponse.getuserID());

        // Convert the filtered ISBNs to Isbn objects and set them in the postBook
        List<PostBook.Isbn> isbnList = sharedContext.filteredBookIsbns.stream()
                .map(isbn -> {
                    PostBook.Isbn isbnObj = new PostBook.Isbn();
                    isbnObj.setIsbn(isbn);
                    return isbnObj;
                })
                .collect(Collectors.toList());

        postBook.setCollectionOfIsbns(isbnList);

        given()
                .header("Authorization", "Bearer " + sharedContext.generateAutTokenResponse.getToken())
                .body(postBook)
                .when().post(BookStoreAPI.BOOKS)
                .then().statusCode(201)
                .contentType(ContentType.JSON);

        sharedContext.numberOfBooks = postBook.getCollectionOfIsbns().size();

        logger.info("Books Posted.");
    }

    @Then("Test the API & Validate performed actions")
    public void testTheAPIValidatePerformedActions() {
        // Fetch the list of books for the user
        sharedContext.userResponse =
                given()
                        .pathParam("UUID", sharedContext.userResponse.getuserID())
                        .header("Authorization", "Bearer " + sharedContext.generateAutTokenResponse.getToken())
                        .when().get(BookStoreAPI.USER_ID)
                        .then().statusCode(200)
                        .extract().as(UserResponse.class);

        // Extract ISBNs from the response
        List<String> fetchedIsbns = sharedContext.userResponse.getBooks().stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList());

        // Compare the fetched list with the stored list
        Assert.assertEquals("The lists of ISBNs do not match", sharedContext.filteredBookIsbns, fetchedIsbns);
    }

}
