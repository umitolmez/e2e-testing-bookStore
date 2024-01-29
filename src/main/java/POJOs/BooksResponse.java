package POJOs;

import java.util.List;

public class BooksResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BooksResponse{" +
                "books=" + books +
                '}';
    }
}
