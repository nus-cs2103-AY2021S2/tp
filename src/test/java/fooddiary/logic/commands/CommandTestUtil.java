package fooddiary.logic.commands;

import static fooddiary.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static fooddiary.logic.parser.CliSyntax.PREFIX_NAME;
import static fooddiary.logic.parser.CliSyntax.PREFIX_RATING;
import static fooddiary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static fooddiary.logic.parser.CliSyntax.PREFIX_TAG;
import static fooddiary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.NameContainsKeywordsPredicate;
import fooddiary.testutil.EditEntryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_RATING_AMY = "1";
    public static final String VALID_RATING_BOB = "2";
    public static final String VALID_REVIEW_AMY = "amy@example.com";
    public static final String VALID_REVIEW_BOB = "bob@example.com";

    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_TAG_WESTERN = "WESTERN";
    public static final String VALID_TAG_FASTFOOD = "fastfood";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String RATING_DESC_AMY = " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DESC_BOB = " " + PREFIX_RATING + VALID_RATING_BOB;
    public static final String REVIEW_DESC_AMY = " " + PREFIX_REVIEW + VALID_REVIEW_AMY;
    public static final String REVIEW_DESC_BOB = " " + PREFIX_REVIEW + VALID_REVIEW_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FASTFOOD = " " + PREFIX_TAG + VALID_TAG_FASTFOOD;
    public static final String TAG_DESC_WESTERN = " " + PREFIX_TAG + VALID_TAG_WESTERN;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + "911a"; // 'a' not allowed in ratings
    public static final String INVALID_REVIEW_DESC = " " + PREFIX_REVIEW; // empty string not allowed for review
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "western*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEntryDescriptor DESC_AMY;
    public static final EditCommand.EditEntryDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEntryDescriptorBuilder().withName(VALID_NAME_AMY)
                .withRating(VALID_RATING_AMY).withReview(VALID_REVIEW_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FASTFOOD).build();
        DESC_BOB = new EditEntryDescriptorBuilder().withName(VALID_NAME_BOB)
                .withRating(VALID_RATING_BOB).withReview(VALID_REVIEW_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_WESTERN, VALID_TAG_FASTFOOD).build();
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
     * - the food diary, filtered entry list and selected entry in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodDiary expectedFoodDiary = new FoodDiary(actualModel.getFoodDiary());
        List<Entry> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEntryList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodDiary, actualModel.getFoodDiary());
        assertEquals(expectedFilteredList, actualModel.getFilteredEntryList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the entry at the given {@code targetIndex} in the
     * {@code model}'s food diary.
     */
    public static void showEntryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEntryList().size());

        Entry entry = model.getFilteredEntryList().get(targetIndex.getZeroBased());
        final String[] splitName = entry.getName().fullName.split("\\s+");
        model.updateFilteredEntryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEntryList().size());
    }

}
