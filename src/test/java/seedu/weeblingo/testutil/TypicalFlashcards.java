package seedu.weeblingo.testutil;

import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_B;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_B;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard ALICE = new FlashcardBuilder()
            .withAnswer("123, Jurong West Ave 6, #08-111").withQuestion("alice@example.com")
            .withTags("friends").build();
    public static final Flashcard BENSON = new FlashcardBuilder()
            .withAnswer("311, Clementi Ave 2, #02-25")
            .withQuestion("johnd@example.com")
            .withTags("owesMoney", "friends").build();
    public static final Flashcard CARL = new FlashcardBuilder()
            .withQuestion("heinz@example.com").withAnswer("wall street").build();
    public static final Flashcard DANIEL = new FlashcardBuilder()
            .withQuestion("cornelia@example.com").withAnswer("10th street").withTags("friends").build();
    public static final Flashcard ELLE = new FlashcardBuilder()
            .withQuestion("werner@example.com").withAnswer("michegan ave").build();
    public static final Flashcard FIONA = new FlashcardBuilder()
            .withQuestion("lydia@example.com").withAnswer("little tokyo").build();
    public static final Flashcard GEORGE = new FlashcardBuilder()
            .withQuestion("anna@example.com").withAnswer("4th street").build();

    // Manually added
    public static final Flashcard HOON = new FlashcardBuilder()
            .withQuestion("stefan@example.com").withAnswer("little india").build();
    public static final Flashcard IDA = new FlashcardBuilder()
            .withQuestion("hans@example.com").withAnswer("chicago ave").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder()
            .withQuestion(VALID_QUESTION_A).withAnswer(VALID_ANSWER_A).withTags(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new FlashcardBuilder()
            .withQuestion(VALID_QUESTION_B).withAnswer(VALID_ANSWER_B).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code FlashcardBook} with all the typical flashcards.
     */
    public static FlashcardBook getTypicalFlashcardBook() {
        FlashcardBook ab = new FlashcardBook();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
