package models;

import com.poiji.annotation.ExcelSheet;
import net.datafaker.Faker;

@ExcelSheet("mensajes")
public class User {

    private final String firstname;
    private final String lastname;
    private final String zipcode;

    public User() {

        final var faker = new Faker();

        this.firstname = faker.name().firstName();
        this.lastname = faker.name().lastName();
        this.zipcode = faker.address().zipCode();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getZipcode() {
        return zipcode;
    }
}
