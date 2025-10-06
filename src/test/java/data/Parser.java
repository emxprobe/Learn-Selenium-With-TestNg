package data;

import models.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, ErrorMessage> obtainMapErrorMessage(){

        final var map = new HashMap<String, ErrorMessage>();

        final var errorMessageList = ExcelReader.readListErrorMessagesExcel();

        for (var errorMessage : errorMessageList){

            map.put(errorMessage.getNombre(), errorMessage);
        }

        return map;
    }
}
