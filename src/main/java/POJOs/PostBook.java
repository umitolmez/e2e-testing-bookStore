package POJOs;

import java.util.List;

public class PostBook {
    private String userId;
    private List<Isbn> collectionOfIsbns;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Isbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    @Override
    public String toString() {
        return "PostBook{" +
                "userId='" + userId + '\'' +
                ", collectionOfIsbns=" + collectionOfIsbns +
                '}';
    }

    public void setCollectionOfIsbns(List<Isbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

    //Inner class for items in collectionOfIsbns
    public static class Isbn {
        private String isbn;

        // Getter and Setter
        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        @Override
        public String toString() {
            return "Isbn{" +
                    "isbn='" + isbn + '\'' +
                    '}';
        }
    }
}
