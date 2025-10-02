package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.JsonCredential;
import utilities.Logs;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    private static final String credentialsPath = "src/test/resources/data/credenciales.json";

    public static JsonCredential getCredentialsMap() {

        final var objectMapper = new ObjectMapper();

        try {

                return objectMapper.
                        readValue(new File(credentialsPath), JsonCredential.class);
        } catch (IOException ioException) {

            Logs.error("Error al leer json de credenciales: %s", ioException.getLocalizedMessage());
            throw new RuntimeException(ioException.getLocalizedMessage());
        }
    }
}
