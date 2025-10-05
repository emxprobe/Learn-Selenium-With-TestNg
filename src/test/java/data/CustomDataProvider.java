package data;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    public static final String DP_CREDENTIALS = "dpCredentials";

    @DataProvider(name = DP_CREDENTIALS)
    public static Object[][] credentialsDataProvider(){

        final var invalid = DataGiver.getLockedCredentials();
        final var unexistent = DataGiver.getUnexistenCredentials();

        return new Object[][]{

                {invalid.getUsername(), invalid.getPassword(), invalid.getMessage()},
                {unexistent.getUsername(), unexistent.getPassword(), unexistent.getMessage()}
        };
    }
}
