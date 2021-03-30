package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FlashBack;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.FlashcardContainsKeywordsPredicate;
import seedu.address.testutil.EditCardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_EINSTEIN = "Einstein's Equation";
    public static final String VALID_QUESTION_OCTOPUS = "How many hearts does an Octopus have?";
    public static final String VALID_ANSWER_EINSTEIN = "e = mc^2";
    public static final String VALID_ANSWER_OCTOPUS = "3";
    public static final String VALID_CATEGORY_EINSTEIN = "Physics";
    public static final String VALID_CATEGORY_OCTOPUS = "Biology";
    public static final String VALID_PRIORITY_EINSTEIN = "Mid";
    public static final String VALID_PRIORITY_OCTOPUS = "High";
    public static final String VALID_TAG_EQUATION = "Einstein";
    public static final String VALID_TAG_GENERAL = "General";

    public static final String QUESTION_DESC_EINSTEIN = " " + PREFIX_QUESTION + VALID_QUESTION_EINSTEIN;
    public static final String QUESTION_DESC_OCTOPUS = " " + PREFIX_QUESTION + VALID_QUESTION_OCTOPUS;
    public static final String ANSWER_DESC_EINSTEIN = " " + PREFIX_ANSWER + VALID_ANSWER_EINSTEIN;
    public static final String ANSWER_DESC_OCTOPUS = " " + PREFIX_ANSWER + VALID_ANSWER_OCTOPUS;
    public static final String CATEGORY_DESC_EINSTEIN = " " + PREFIX_CATEGORY + VALID_CATEGORY_EINSTEIN;
    public static final String CATEGORY_DESC_OCTOPUS = " " + PREFIX_CATEGORY + VALID_CATEGORY_OCTOPUS;
    public static final String PRIORITY_DESC_EINSTEIN = " " + PREFIX_PRIORITY + VALID_PRIORITY_EINSTEIN;
    public static final String PRIORITY_DESC_OCTOPUS = " " + PREFIX_PRIORITY + VALID_PRIORITY_OCTOPUS;
    public static final String TAG_DESC_GENERAL = " " + PREFIX_TAG + VALID_TAG_GENERAL;
    public static final String TAG_DESC_EQUATION = " " + PREFIX_TAG + VALID_TAG_EQUATION;

    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION + ""; // '&' not allowed in questions
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER + ""; // 'a' not allowed in answers
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + ""; // missing '@' symbol
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY; // empty string not allowed for priorities
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Equation*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCardDescriptor DESC_EINSTEIN;
    public static final EditCommand.EditCardDescriptor DESC_ATP;

    static {
        DESC_EINSTEIN = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_EINSTEIN)
                .withAnswer(VALID_ANSWER_EINSTEIN).withCategory(VALID_CATEGORY_EINSTEIN)
                .withPriority(VALID_PRIORITY_EINSTEIN)
                .withTags(VALID_TAG_GENERAL).build();
        DESC_ATP = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_OCTOPUS)
                .withAnswer(VALID_ANSWER_OCTOPUS).withCategory(VALID_CATEGORY_OCTOPUS)
                .withPriority(VALID_PRIORITY_OCTOPUS)
                .withTags(VALID_TAG_EQUATION, VALID_TAG_GENERAL).build();
    }

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
     * - FlashBack, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FlashBack expectedFlashBack = new FlashBack(actualModel.getFlashBack());
        List<Flashcard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashcardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFlashBack, actualModel.getFlashBack());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashcardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s flashcard list.
     */
    public static void showFlashcardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashcardList().size());

        Flashcard flashcard = model.getFilteredFlashcardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashcard.getQuestion().fullQuestion.split("\\s+");
        model.updateFilteredFlashcardList(new FlashcardContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashcardList().size());
    }

}
