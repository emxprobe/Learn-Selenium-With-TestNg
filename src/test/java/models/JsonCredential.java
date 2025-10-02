package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class JsonCredential {

    @JsonProperty("credentials")
    private Map<String, Credentials> mapCredentials;

    public Map<String, Credentials> getMapCredentials() {
        return mapCredentials;
    }
}
