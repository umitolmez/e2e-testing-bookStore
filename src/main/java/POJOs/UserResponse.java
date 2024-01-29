package POJOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    @JsonAlias({"userID", "userId"}) //Handles both "userID" and "userId"
    private String userID;
    private String username;
    private List<Book> books;

    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", books=" + books +
                '}';
    }
}

