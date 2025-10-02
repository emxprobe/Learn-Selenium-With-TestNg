package data;

import models.Credentials;

import java.util.Map;

public class DataGiver {

    private static Map<String, Credentials> getMapCredentials() {

        return JsonReader.getCredentialsMap().getMapCredentials();
    }

    public static Credentials getValidCredentials() {

        return getMapCredentials().get("valid");
    }

    public static Credentials getLockedCredentials() {

        return getMapCredentials().get("locked");
    }
}
