package seedu.weeblingo.storage;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.tag.Tag;

/**
 * Reads from local database, populates the flashcards read into user's data storage.
 */
public class LocalDatabasePopulator {

    /**
     * Gets the database of flashcards, as an array of flashcards.
     *
     * @return An array of flashcards extracted from database.
     */
    public static Flashcard[] getDatabaseOfFlashcards() {
        JSONArray readDatabaseAsJsonArray = JsonDatabaseReader.readDatabaseAsJsonArray();
        Flashcard[] flashcards = new Flashcard[readDatabaseAsJsonArray.size()];
        for (int i = 0; i < readDatabaseAsJsonArray.size(); i++) {
            JSONObject tempJsonCard = (JSONObject) readDatabaseAsJsonArray.get(i);
            Question question = new Question((String) tempJsonCard.get("question"));
            Answer answer = new Answer((String) tempJsonCard.get("answer"));
            Set<Tag> tags = getTagSet((JSONArray) tempJsonCard.get("tagged"));
            Flashcard tempCard = new Flashcard(question, answer, tags);
            flashcards[i] = tempCard;
        }
        return flashcards;
    }

    /**
     * Gets the specified number of flashcards, as an array of flashcards.
     *
     * @return An array of flashcards of specified size extracted from database.
     */
    public static Flashcard[] getDatabaseOfFlashcards(int numberOfQuestions) {
        JSONArray readDatabaseAsJsonArray = JsonDatabaseReader.readDatabaseAsJsonArray();
        Flashcard[] flashcards = new Flashcard[readDatabaseAsJsonArray.size()];
        for (int i = 0; i < readDatabaseAsJsonArray.size(); i++) {
            JSONObject tempJsonCard = (JSONObject) readDatabaseAsJsonArray.get(i);
            Question question = new Question((String) tempJsonCard.get("question"));
            Answer answer = new Answer((String) tempJsonCard.get("answer"));
            Set<Tag> tags = getTagSet((JSONArray) tempJsonCard.get("tagged"));
            Flashcard tempCard = new Flashcard(question, answer, tags);
            flashcards[i] = tempCard;
        }
        return Arrays.copyOfRange(flashcards, 0, numberOfQuestions);
    }

    public static ReadOnlyFlashcardBook getDatabaseOfWeeblingo() {
        FlashcardBook sampleFb = new FlashcardBook();
        for (Flashcard sampleFlashcard : getDatabaseOfFlashcards()) {
            sampleFb.addFlashcard(sampleFlashcard);
        }
        return sampleFb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the tags contained in the json flashcard object.
     */
    public static Set<Tag> getTagSet(JSONArray jsonTags) {
        String[] strings = new String[jsonTags.size()];
        for (int i = 0; i < jsonTags.size(); i++) {
            strings[i] = (String) jsonTags.get(i);
        }
        return getTagSet(strings);
    }
}
