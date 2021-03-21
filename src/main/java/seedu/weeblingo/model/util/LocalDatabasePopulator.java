package seedu.weeblingo.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;

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
        Flashcard[] flashcards = new Flashcard[46];
        flashcards[0] = new Flashcard(new Question("あ"), new Answer("a"), new HashSet<>());
        flashcards[1] = new Flashcard(new Question("い"), new Answer("i"), new HashSet<>());
        flashcards[2] = new Flashcard(new Question("う"), new Answer("u"), new HashSet<>());
        flashcards[3] = new Flashcard(new Question("え"), new Answer("e"), new HashSet<>());
        flashcards[4] = new Flashcard(new Question("お"), new Answer("o"), new HashSet<>());
        flashcards[5] = new Flashcard(new Question("か"), new Answer("ka"), new HashSet<>());
        flashcards[6] = new Flashcard(new Question("き"), new Answer("ki"), new HashSet<>());
        flashcards[7] = new Flashcard(new Question("く"), new Answer("ku"), new HashSet<>());
        flashcards[8] = new Flashcard(new Question("け"), new Answer("ke"), new HashSet<>());
        flashcards[9] = new Flashcard(new Question("こ"), new Answer("ko"), new HashSet<>());
        flashcards[10] = new Flashcard(new Question("さ"), new Answer("sa"), new HashSet<>());
        flashcards[11] = new Flashcard(new Question("し"), new Answer("shi"), new HashSet<>());
        flashcards[12] = new Flashcard(new Question("す"), new Answer("su"), new HashSet<>());
        flashcards[13] = new Flashcard(new Question("せ"), new Answer("se"), new HashSet<>());
        flashcards[14] = new Flashcard(new Question("そ"), new Answer("so"), new HashSet<>());
        flashcards[15] = new Flashcard(new Question("た"), new Answer("ta"), new HashSet<>());
        flashcards[16] = new Flashcard(new Question("ち"), new Answer("chi"), new HashSet<>());
        flashcards[17] = new Flashcard(new Question("つ"), new Answer("tsu"), new HashSet<>());
        flashcards[18] = new Flashcard(new Question("て"), new Answer("te"), new HashSet<>());
        flashcards[19] = new Flashcard(new Question("と"), new Answer("to"), new HashSet<>());
        flashcards[20] = new Flashcard(new Question("な"), new Answer("na"), new HashSet<>());
        flashcards[21] = new Flashcard(new Question("に"), new Answer("ni"), new HashSet<>());
        flashcards[22] = new Flashcard(new Question("ぬ"), new Answer("nu"), new HashSet<>());
        flashcards[23] = new Flashcard(new Question("ね"), new Answer("ne"), new HashSet<>());
        flashcards[24] = new Flashcard(new Question("の"), new Answer("no"), new HashSet<>());
        flashcards[25] = new Flashcard(new Question("は"), new Answer("ha"), new HashSet<>());
        flashcards[26] = new Flashcard(new Question("ひ"), new Answer("hi"), new HashSet<>());
        flashcards[27] = new Flashcard(new Question("ふ"), new Answer("fu"), new HashSet<>());
        flashcards[28] = new Flashcard(new Question("へ"), new Answer("he"), new HashSet<>());
        flashcards[29] = new Flashcard(new Question("ほ"), new Answer("ho"), new HashSet<>());
        flashcards[30] = new Flashcard(new Question("ま"), new Answer("ma"), new HashSet<>());
        flashcards[31] = new Flashcard(new Question("み"), new Answer("mi"), new HashSet<>());
        flashcards[32] = new Flashcard(new Question("む"), new Answer("mu"), new HashSet<>());
        flashcards[33] = new Flashcard(new Question("め"), new Answer("me"), new HashSet<>());
        flashcards[34] = new Flashcard(new Question("も"), new Answer("mo"), new HashSet<>());
        flashcards[35] = new Flashcard(new Question("や"), new Answer("ya"), new HashSet<>());
        flashcards[36] = new Flashcard(new Question("ゆ"), new Answer("yu"), new HashSet<>());
        flashcards[37] = new Flashcard(new Question("よ"), new Answer("yo"), new HashSet<>());
        flashcards[38] = new Flashcard(new Question("ら"), new Answer("ra"), new HashSet<>());
        flashcards[39] = new Flashcard(new Question("り"), new Answer("ri"), new HashSet<>());
        flashcards[40] = new Flashcard(new Question("る"), new Answer("ru"), new HashSet<>());
        flashcards[41] = new Flashcard(new Question("れ"), new Answer("re"), new HashSet<>());
        flashcards[42] = new Flashcard(new Question("ろ"), new Answer("ro"), new HashSet<>());
        flashcards[43] = new Flashcard(new Question("わ"), new Answer("wa"), new HashSet<>());
        flashcards[44] = new Flashcard(new Question("を"), new Answer("wo"), new HashSet<>());
        flashcards[45] = new Flashcard(new Question("ん"), new Answer("n"), new HashSet<>());
        return flashcards;
    }

    public static ReadOnlyFlashcardBook getDatabaseOfWeeblingo() {
        FlashcardBook sampleAb = new FlashcardBook();
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
