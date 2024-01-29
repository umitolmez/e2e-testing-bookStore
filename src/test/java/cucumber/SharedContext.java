package cucumber;

import POJOs.BooksResponse;
import POJOs.TokenResponse;
import POJOs.User;
import POJOs.UserResponse;
import utilities.DataGenerator;

import java.util.List;

public class SharedContext {

    public DataGenerator dataGenerator = new DataGenerator();
    public User user;
    public UserResponse userResponse;
    public TokenResponse generateAutTokenResponse;
    public BooksResponse booksResponse;
    public List<String> filteredBookIsbns;
    public int numberOfBooks;
}
