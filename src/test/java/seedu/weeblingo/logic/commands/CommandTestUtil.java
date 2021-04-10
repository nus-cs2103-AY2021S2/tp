package seedu.weeblingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_START_NUMBER;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.weeblingo.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.weeblingo.model.tag.Tag;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_A = "あ";
    public static final String VALID_QUESTION_I = "い";
    public static final String VALID_ANSWER_A = "a";
    public static final String VALID_ANSWER_I = "i";
    public static final String VALID_ANSWER_CAPS = "I";
    public static final String INVALID_ANSWER_EMPTY = "";
    public static final String INVALID_ANSWER_SYMBOL = "@#!!!";
    public static final String VALID_TAG_DIFFICULT = "difficult";
    public static final String VALID_TAG_EASY = "easy";
    public static final String VALID_START_INTEGER_MIN = "1";
    public static final String VALID_START_INTEGER_MIDDLE = "10";
    public static final String VALID_START_INTEGER_MAX = String.valueOf(Integer.MAX_VALUE);
    public static final String INVALID_START_INTEGER_MAX = "0";
    public static final String INVALID_START_INTEGER_MIDDLE = "-10";
    public static final String INVALID_START_INTEGER_MIN = String.valueOf(Integer.MIN_VALUE);
    public static final String VALID_TAG_HIRAGANA = "hiragana";
    public static final String VALID_TAG_GOJUON = "gojuon";
    public static final String INVALID_START_TAG = "!@#$%";
    public static final String VALID_NONEXISTENT_TAG = "thisIsANonExistentTag";
    public static final String VALID_TAG_INPUT = "1 t/easy";
    public static final String VALID_TAG_INPUT_MULTIPLE = "1 t/difficult t/easy";
    public static final int VALID_START_INTEGER_GENERIC = 0;
    public static final Set<Tag> VALID_TAGS_SET_GENERIC = new HashSet<>();
    public static final Set<Tag> VALID_TAGS_SET_HIRAGANA = new HashSet<>(List.of(new Tag(VALID_TAG_HIRAGANA)));
    public static final Set<Tag> VALID_TAGS_SET_GOJUON = new HashSet<>(List.of(new Tag(VALID_TAG_GOJUON)));
    public static final Set<Tag> VALID_TAGS_SET_COMBINATION = new HashSet<>(
            List.of(new Tag(VALID_TAG_GOJUON), new Tag(VALID_TAG_HIRAGANA)));
    public static final Set<Tag> VALID_TAGS_SET_EASY = new HashSet<>(List.of(new Tag(VALID_TAG_EASY)));
    public static final Set<Tag> VALID_TAGS_SET_DIFFICULT = new HashSet<>(List.of(new Tag(VALID_TAG_DIFFICULT)));
    public static final Set<Tag> VALID_TAGS_SET_EASY_AND_DIFFICULT = new HashSet<>(
            List.of(new Tag(VALID_TAG_DIFFICULT), new Tag(VALID_TAG_EASY)));
    public static final Set<Tag> VALID_NONEXISTENT_TAGS_SET = new HashSet<>(List.of(new Tag(VALID_NONEXISTENT_TAG)));

    public static final String VALID_START_INTEGER_MIN_DESC = " " + PREFIX_START_NUMBER + VALID_START_INTEGER_MIN;
    public static final String VALID_START_INTEGER_MIDDLE_DESC = " " + PREFIX_START_NUMBER + VALID_START_INTEGER_MIDDLE;
    public static final String VALID_START_INTEGER_MAX_DESC = " " + PREFIX_START_NUMBER + VALID_START_INTEGER_MAX;
    public static final String INVALID_START_INTEGER_MAX_DESC = " " + PREFIX_START_NUMBER + INVALID_START_INTEGER_MAX;
    public static final String INVALID_START_INTEGER_MIDDLE_DESC = " " + PREFIX_START_NUMBER
            + INVALID_START_INTEGER_MIDDLE;
    public static final String INVALID_START_INTEGER_MIN_DESC = " " + PREFIX_START_NUMBER + INVALID_START_INTEGER_MIN;
    public static final String VALID_TAG_HIRAGANA_DESC = " " + PREFIX_TAG + VALID_TAG_HIRAGANA;
    public static final String VALID_TAG_GOJUON_DESC = " " + PREFIX_TAG + VALID_TAG_GOJUON;
    public static final String VALID_TAG_COMBINATION_DESC = " " + VALID_TAG_GOJUON_DESC + " "
            + VALID_TAG_HIRAGANA_DESC;
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + INVALID_START_TAG;
    public static final String VALID_NONEXISTENT_TAGS_SET_DESC = " " + PREFIX_TAG + VALID_NONEXISTENT_TAG;

    public static final String QUESTION_DESC_A = " " + PREFIX_QUESTION + VALID_QUESTION_A;
    public static final String QUESTION_DESC_B = " " + PREFIX_QUESTION + VALID_QUESTION_I;
    public static final String ANSWER_DESC_A = " " + PREFIX_ANSWER + VALID_ANSWER_A;
    public static final String ANSWER_DESC_B = " " + PREFIX_ANSWER + VALID_ANSWER_I;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_EASY;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_DIFFICULT;

    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER; // empty string not allowed for answeres

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the answer book, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FlashcardBook expectedFlashcardBook = new FlashcardBook(actualModel.getFlashcardBook());
        List<Flashcard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashcardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFlashcardBook, actualModel.getFlashcardBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashcardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s flashcard book.
     */
    public static void showFlashcardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashcardList().size());

        Flashcard flashcard = model.getFilteredFlashcardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashcard.getQuestion().toString().split("\\s+");
        model.updateFilteredFlashcardList(new QuestionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashcardList().size());
    }

}
