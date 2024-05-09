package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDataConfig {
    Faker faker = new Faker(new Locale("en-US"));

    public static FakerDataConfig getFakerDataConfig() {
        return new FakerDataConfig();
    }

    public String getFirstname() {
        return faker.address().firstName();
    }

    public String getLastname() {
        return faker.address().lastName();
    }
}
