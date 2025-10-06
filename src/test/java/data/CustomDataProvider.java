package data;

import models.User;
import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    public static final String DP_CREDENTIALS = "dpCredentials";
    public static final String DP_ERRORMESSAGE = "dpErrorMessage";

    @DataProvider(name = DP_CREDENTIALS)
    public static Object[][] credentialsDataProvider(){

        final var invalid = DataGiver.getLockedCredentials();
        final var unexistent = DataGiver.getUnexistenCredentials();

        return new Object[][]{

                {invalid.getUsername(), invalid.getPassword(), invalid.getMessage()},
                {unexistent.getUsername(), unexistent.getPassword(), unexistent.getMessage()}
        };
    }

    @DataProvider(name = DP_ERRORMESSAGE)
    public static Object[][] errorMessageDataProvider(){

        final var user = new User();
        final var mapErrorMessage = Parser.obtainMapErrorMessage();

        return new Object[][]{

                {"", user.getLastname(), user.getZipcode(),
                        mapErrorMessage.get("error_name").getMensaje()},
                {user.getFirstname(), "", user.getZipcode(),
                        mapErrorMessage.get("error_lastname").getMensaje()},
                {user.getFirstname(), user.getLastname(), "",
                        mapErrorMessage.get("error_zipcode").getMensaje()}
        };
    }
}
