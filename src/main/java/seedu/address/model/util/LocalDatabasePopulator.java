package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Question;
import seedu.address.model.person.Question;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonDatabaseReader;

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

//        Flashcard[] flashcards = new Flashcard[46];
//        Flashcard flashcard = new Flashcard(new Question("あ"), new Answer("a"), new HashSet<>());
//        Flashcard flashcard2 = new Flashcard(new Question("あ"), new Answer("b"), new HashSet<>());
//        flashcards[0] = flashcard;
//        flashcards[1] = flashcard2;
        return flashcards;
    }

    public static ReadOnlyAddressBook getDatabaseOfWeeblingo() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getDatabaseOfFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
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
