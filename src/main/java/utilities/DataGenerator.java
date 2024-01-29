package utilities;

import com.github.javafaker.Faker;

public class DataGenerator {
    private final Faker faker;

    public DataGenerator() {
        this.faker = new Faker();
    }

    public String generateUserName() {
        return faker.name().username();
    }
}
