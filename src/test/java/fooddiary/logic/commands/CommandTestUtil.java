package fooddiary.logic.commands;

import static fooddiary.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static fooddiary.logic.parser.CliSyntax.PREFIX_NAME;
import static fooddiary.logic.parser.CliSyntax.PREFIX_PRICE;
import static fooddiary.logic.parser.CliSyntax.PREFIX_RATING;
import static fooddiary.logic.parser.CliSyntax.PREFIX_REVIEW;
import static fooddiary.logic.parser.CliSyntax.PREFIX_TAG_CATEGORY;
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
import fooddiary.testutil.AddOnEntryDescriptorBuilder;
import fooddiary.testutil.EditEntryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_A = "Amy Bee";
    public static final String VALID_NAME_B = "Bob Choo";
    public static final String VALID_RATING_A = "1";
    public static final String VALID_RATING_B = "2";
    public static final String VALID_PRICE_A = "11";
    public static final String VALID_PRICE_B = "17";
    public static final String VALID_REVIEW_A = "amy@example.com";
    public static final String VALID_REVIEW_B = "bob@example.com";

    public static final String VALID_ADDRESS_A = "123, Jurong West Ave 6, #08-111";
    public static final String VALID_ADDRESS_B = "Block 123, Bobby Street 3";

    public static final String VALID_TAG_CATEGORY_WESTERN = "WESTERN";
    public static final String VALID_TAG_CATEGORY_FASTFOOD = "fastfood";
    public static final String VALID_TAG_SCHOOL_SOC = "SOC";

    public static final String NAME_DESC_A = " " + PREFIX_NAME + VALID_NAME_A;
    public static final String NAME_DESC_B = " " + PREFIX_NAME + VALID_NAME_B;
    public static final String RATING_DESC_A = " " + PREFIX_RATING + VALID_RATING_A;
    public static final String RATING_DESC_B = " " + PREFIX_RATING + VALID_RATING_B;
    public static final String PRICE_DESC_A = " " + PREFIX_PRICE + VALID_PRICE_A;
    public static final String PRICE_DESC_B = " " + PREFIX_PRICE + VALID_PRICE_B;
    public static final String REVIEW_DESC_A = " " + PREFIX_REVIEW + VALID_REVIEW_A;
    public static final String REVIEW_DESC_B = " " + PREFIX_REVIEW + VALID_REVIEW_B;
    public static final String ADDRESS_DESC_A = " " + PREFIX_ADDRESS + VALID_ADDRESS_A;
    public static final String ADDRESS_DESC_B = " " + PREFIX_ADDRESS + VALID_ADDRESS_B;
    public static final String TAG_DESC_FASTFOOD = " " + PREFIX_TAG_CATEGORY + VALID_TAG_CATEGORY_FASTFOOD;
    public static final String TAG_DESC_WESTERN = " " + PREFIX_TAG_CATEGORY + VALID_TAG_CATEGORY_WESTERN;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + "911a"; // 'a' not allowed in ratings
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + "18a"; // 'a' not allowed in ratings
    public static final String INVALID_REVIEW_DESC = " " + PREFIX_REVIEW; // empty string not allowed for review
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    // '*' not allowed in tags
    public static final String INVALID_TAG_CATEGORY_DESC = " " + PREFIX_TAG_CATEGORY + "western*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEntryDescriptor DESC_A;
    public static final EditCommand.EditEntryDescriptor DESC_B;
    public static final AddOnCommand.AddOnToEntryDescriptor DESC_C;
    public static final AddOnCommand.AddOnToEntryDescriptor DESC_D;

    static {
        DESC_A = new EditEntryDescriptorBuilder().withName(VALID_NAME_A).withRating(VALID_RATING_A)
                .withPrice(VALID_PRICE_A).withReviews(VALID_REVIEW_A).withAddress(VALID_ADDRESS_A)
                .withTagCategories(VALID_TAG_CATEGORY_FASTFOOD).build();
        DESC_B = new EditEntryDescriptorBuilder().withName(VALID_NAME_B).withRating(VALID_RATING_B)
                .withPrice(VALID_PRICE_B).withReviews(VALID_REVIEW_B).withAddress(VALID_ADDRESS_B)
                .withTagCategories(VALID_TAG_CATEGORY_WESTERN, VALID_TAG_CATEGORY_FASTFOOD).build();
    }

    static {
        DESC_C = new AddOnEntryDescriptorBuilder()
                .withPrice(VALID_PRICE_A).withReviews(VALID_REVIEW_A).build();
        DESC_D = new AddOnEntryDescriptorBuilder()
                .withPrice(VALID_PRICE_B).withReviews(VALID_REVIEW_B).build();
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
            assertEquals(expectedCommandResult.getFeedbackToUser(), result.getFeedbackToUser());
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
        model.updateFilteredEntryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[1])));

        assertEquals(1, model.getFilteredEntryList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the entries at the given {@code targetIndexFirst} and
     * {@code targetIndexSecond} in the {@code model}'s food diary.
     */
    public static void showEntriesAtIndexes(Model model, Index targetIndexFirst, Index targetIndexSecond) {
        assertTrue(targetIndexFirst.getZeroBased() < model.getFilteredEntryList().size());
        assertTrue(targetIndexSecond.getZeroBased() < model.getFilteredEntryList().size());

        Entry entryFirst = model.getFilteredEntryList().get(targetIndexFirst.getZeroBased());
        Entry entrySecond = model.getFilteredEntryList().get(targetIndexSecond.getZeroBased());
        final String[] splitNameFirst = entryFirst.getName().fullName.split("\\s+");
        final String[] splitNameSecond = entrySecond.getName().fullName.split("\\s+");
        model.updateFilteredEntryList(
                new NameContainsKeywordsPredicate(Arrays.asList(splitNameFirst[1], splitNameSecond[1])));

        assertEquals(2, model.getFilteredEntryList().size());
    }

}
