package seedu.weeblingo.storage;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.Question;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.model.tag.Tag;

/**
 * Reads from local database, populates the flashcards read into user's data storage.
 */
public class LocalDatabasePopulator {

    /**
     * Gets the database of flashcards, as an array of flashcards.
     *
     * @param jsonArrayReadFromDatabase Data read from database, assumed to be in JsonArray format. Must not be null.
     * @return An array of flashcards extracted from database.
     */
    public static Flashcard[] getDatabaseOfFlashcards(JSONArray jsonArrayReadFromDatabase) {
        assert jsonArrayReadFromDatabase != null;
        Flashcard[] flashcards = new Flashcard[jsonArrayReadFromDatabase.size()];
        for (int i = 0; i < jsonArrayReadFromDatabase.size(); i++) {
            JSONObject tempJsonCard = (JSONObject) jsonArrayReadFromDatabase.get(i);
            Question question = new Question((String) tempJsonCard.get("question"));
            Answer answer = new Answer((String) tempJsonCard.get("answer"));
            Set<Tag> tags = getTagSet((JSONArray) tempJsonCard.get("tagged"));
            Flashcard tempCard = new Flashcard(question, answer, tags);
            flashcards[i] = tempCard;
        }
        return flashcards;
    }

    public static ReadOnlyFlashcardBook getDatabaseOfWeeblingo() {
        FlashcardBook sampleFb = new FlashcardBook();
        for (Flashcard sampleFlashcard : getDatabaseOfFlashcards(JsonDatabaseReader.readDatabaseAsJsonArray())) {
            sampleFb.addFlashcard(sampleFlashcard);
        }

        try {
            sampleFb.addScore(Score.of(100, 100));
            TimeUnit.SECONDS.sleep(1);
            sampleFb.addScore(Score.of(100, 80));
            TimeUnit.SECONDS.sleep(1);
            sampleFb.addScore(Score.of(46, 45));
            TimeUnit.SECONDS.sleep(1);
            sampleFb.addScore(Score.of(10, 5));
            TimeUnit.SECONDS.sleep(1);
            sampleFb.addScore(Score.of(20, 14));
            TimeUnit.SECONDS.sleep(1);
            sampleFb.addScore(Score.of(23, 2));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return sampleFb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @param strings The strings to be converted to a set of Tags. Will not be null.
     */
    public static Set<Tag> getTagSet(String... strings) {
        assert strings != null;
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the tags contained in the json flashcard object.
     */
    private static Set<Tag> getTagSet(JSONArray jsonTags) {
        String[] strings = new String[jsonTags.size()];
        for (int i = 0; i < jsonTags.size(); i++) {
            strings[i] = (String) jsonTags.get(i);
        }
        return getTagSet(strings);
    }
}
