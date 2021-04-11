package seedu.weeblingo.testutil;

import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_ANSWER_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_QUESTION_I;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard A_CARD = new FlashcardBuilder()
            .withAnswer("a").withQuestion("あ").withTags("gojuon").build();
    public static final Flashcard I_CARD = new FlashcardBuilder()
            .withAnswer("i").withQuestion("い").withTags("gojuon").build();
    public static final Flashcard U_CARD = new FlashcardBuilder()
            .withQuestion("う").withAnswer("u").withTags("gojuon").build();
    public static final Flashcard E_CARD = new FlashcardBuilder()
            .withQuestion("え").withAnswer("e").withTags("gojuon").build();
    public static final Flashcard O_CARD = new FlashcardBuilder()
            .withQuestion("お").withAnswer("o").withTags("gojuon").build();
    public static final Flashcard KA_CARD = new FlashcardBuilder()
            .withQuestion("か").withAnswer("ka").withTags("gojuon").build();
    public static final Flashcard KI_CARD = new FlashcardBuilder()
            .withQuestion("き").withAnswer("ki").withTags("gojuon").build();

    // Manually added
    public static final Flashcard KU_CARD = new FlashcardBuilder()
            .withQuestion("く").withAnswer("ku").withTags("gojuon").build();
    public static final Flashcard KE_CARD = new FlashcardBuilder()
            .withQuestion("け").withAnswer("ke").withTags("gojuon").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard A = new FlashcardBuilder()
            .withQuestion(VALID_QUESTION_A).withAnswer(VALID_ANSWER_A).withTags(VALID_TAG_EASY).build();
    public static final Flashcard I = new FlashcardBuilder()
            .withQuestion(VALID_QUESTION_I).withAnswer(VALID_ANSWER_I).withTags(VALID_TAG_DIFFICULT, VALID_TAG_EASY)
            .build();

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
        return new ArrayList<>(Arrays.asList(A_CARD, I_CARD, U_CARD, E_CARD, O_CARD, KA_CARD, KI_CARD));
    }
}
