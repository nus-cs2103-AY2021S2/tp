package seedu.weeblingo.storage;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Flashcards database (Json file) reader.
 */
public class JsonDatabaseReader {

    private static final String DATABASE_LOCATION = "database/flashcards.json";

    /**
     * Reads the local database for flashcards as Json Array format.
     *
     * @return The Json Array representation of the flashcards in the local database.
     */
    public static JSONArray readDatabaseAsJsonArray() {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new FileReader(DATABASE_LOCATION)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray flashcardsJsonArr = (JSONArray) jsonObject.get("flashcards");
            return flashcardsJsonArr;
        } catch (IOException | ParseException e) {
            throw new RuntimeException();
        }
    }
}
