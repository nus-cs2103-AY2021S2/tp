package seedu.weeblingo.storage;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import seedu.weeblingo.MainApp;
import seedu.weeblingo.storage.exceptions.DataInitializationException;

/**
 * Flashcards database (Json file) reader.
 */
public class JsonDatabaseReader {

    private static final String DATABASE_LOCATION = "/database/JapaneseDatabase.json";

    /**
     * Reads the local database for flashcards as Json Array format.
     *
     * @return The Json Array representation of the flashcards in the local database.
     */
    public static JSONArray readDatabaseAsJsonArray() {
        JSONParser jsonParser = new JSONParser();
        try (Scanner sc = new Scanner(MainApp.class.getResourceAsStream(DATABASE_LOCATION), "utf8")) {
            StringBuilder sb = new StringBuilder("");
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONArray flashcardsJsonArr = (JSONArray) jsonObject.get("flashcards");
            return flashcardsJsonArr;
        } catch (ParseException e) {
            throw new DataInitializationException();
        }
    }
}
