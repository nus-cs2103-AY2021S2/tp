package seedu.weeblingo.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.tag.Tag;

public class LocalDatabasePopulatorTest {

    /** Integration testing */
    @Test
    public void getDatabaseOfWeeblingo() {
        Flashcard[] flashcards = LocalDatabasePopulator
                .getDatabaseOfFlashcards(JsonDatabaseReader.readDatabaseAsJsonArray());
        ReadOnlyFlashcardBook book = LocalDatabasePopulator.getDatabaseOfWeeblingo();
        for (int i = 0; i < flashcards.length; i++) {
            assertEquals(book.getFlashcardList().get(i), flashcards[i]);
        }
    }

    @Test
    public void getDatabaseOfFlashcards() {
        JSONArray jsonArrayReadFromDatabase = JsonDatabaseReaderStub.readDatabaseAsJsonArray();
        Flashcard[] flashcards = LocalDatabasePopulator.getDatabaseOfFlashcards(jsonArrayReadFromDatabase);
        assertEquals(flashcards[0].getQuestion().toString(), "ふ");
        assertEquals(flashcards[0].getAnswer().toString(), "fu");
        assertTrue(flashcards[0].getTags().contains(new TagStub("hiragana")));
        assertTrue(flashcards[0].getTags().contains(new TagStub("gojuon")));

        assertEquals(flashcards[1].getQuestion().toString(), "ひ");
        assertEquals(flashcards[1].getAnswer().toString(), "hi");
        assertTrue(flashcards[1].getTags().isEmpty());
    }

    @Test
    public void getDatabaseOfFlashcards_empty_pass() {
        JSONArray jsonArrayReadFromDatabase = new JSONArray();
        Flashcard[] flashcards = LocalDatabasePopulator.getDatabaseOfFlashcards(jsonArrayReadFromDatabase);
        assertTrue(flashcards.length == 0);
    }

    @Test
    public void getDatabaseOfFlashcards_null_assertionError() {
        assertThrows(AssertionError.class, () -> LocalDatabasePopulator
                .getDatabaseOfFlashcards(null));
    }

    @Test
    public void getTagSet_zeroWord_emptySet() {
        Set<Tag> compare = new HashSet<>();
        assertEquals(compare, LocalDatabasePopulator.getTagSet());
    }

    @Test
    public void getTagSet_oneWord_success() {
        Set<Tag> compare = new HashSet<>();
        compare.add(new TagStub("lol"));
        assertEquals(compare, LocalDatabasePopulator.getTagSet("lol"));
    }

    @Test
    public void getTagSet_manyWordsWithRandomOrdering_success() {
        Set<Tag> compare = new HashSet<>();
        compare.add(new TagStub("a"));
        compare.add(new TagStub("b"));
        compare.add(new TagStub("c"));
        compare.add(new TagStub("d"));
        compare.add(new TagStub("e"));
        compare.add(new TagStub("f"));
        compare.add(new TagStub("g"));
        compare.add(new TagStub("h"));
        compare.add(new TagStub("i"));
        compare.add(new TagStub("j"));
        compare.add(new TagStub("k"));
        compare.add(new TagStub("l"));
        compare.add(new TagStub("m"));
        compare.add(new TagStub("n"));
        compare.add(new TagStub("o"));
        compare.add(new TagStub("p"));
        compare.add(new TagStub("q"));
        compare.add(new TagStub("r"));
        compare.add(new TagStub("s"));
        compare.add(new TagStub("t"));
        compare.add(new TagStub("u"));
        compare.add(new TagStub("v"));
        compare.add(new TagStub("w"));
        compare.add(new TagStub("x"));
        compare.add(new TagStub("y"));
        compare.add(new TagStub("z"));

        assertEquals(compare, LocalDatabasePopulator
                .getTagSet("a", "b", "b", "b", "b", "c", "c",
                "d", "f", "f", "e", "i", "h", "j", "k", "n", "m", "l",
                "o", "q", "q", "p", "r", "s", "t", "x", "y", "z", "u",
                "v", "w", "u", "g"));
    }

    @Test
    public void getTagSet_duplicateWords_singleOneSurvived() {
        Set<Tag> compare = new HashSet<>();
        compare.add(new TagStub("lol"));
        assertEquals(compare, LocalDatabasePopulator.getTagSet("lol", "lol", "lol"));
    }
}

/**
 * Stub class for JsonDatabaseReader
 */
class JsonDatabaseReaderStub extends JsonDatabaseReader {

    /**
     * Stub method for class JsonDatabaseReader
     */
    public static JSONArray readDatabaseAsJsonArray() {
        JSONArray jsonArray = new JSONArray();

        JSONObject jo1 = new JSONObject();
        jo1.put("question", "ふ");
        jo1.put("answer", "fu");
        JSONArray tagArr1 = new JSONArray();
        tagArr1.add("gojuon");
        tagArr1.add("hiragana");
        jo1.put("tagged", tagArr1);

        JSONObject jo2 = new JSONObject();
        jo2.put("question", "ひ");
        jo2.put("answer", "hi");
        jo2.put("tagged", new JSONArray());

        jsonArray.add(jo1);
        jsonArray.add(jo2);
        return jsonArray;
    }
}

/**
 * Stub class for Tag
 */
class TagStub extends Tag {
    public TagStub(String tagName) {
        super(tagName);
    }
}
