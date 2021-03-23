package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.storemando.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.storemando.commons.core.index.Index;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;
import seedu.storemando.testutil.EditItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_CHEESE = "Cheese";
    public static final String VALID_NAME_BANANA = "Banana";
    public static final String VALID_QUANTITY_CHEESE = "1";
    public static final String VALID_QUANTITY_BANANA = "2";
    public static final String VALID_EXPIRED_EXPIRYDATE_BANANA = "2017-10-11";
    public static final String VALID_EXPIRYDATE_CHEESE = "2021-10-11";
    public static final String VALID_EXPIRYDATE_BANANA = "2019-08-10";
    public static final String VALID_LOCATION_CHEESE = "Refrigerator";
    public static final String VALID_LOCATION_BANANA = "Kitchen";
    public static final String VALID_TAG_HUSBAND = "Essential";
    public static final String VALID_TAG_FRIEND = "Favourite";

    public static final String NAME_DESC_CHEESE = " " + PREFIX_NAME + VALID_NAME_CHEESE;
    public static final String NAME_DESC_BANANA = " " + PREFIX_NAME + VALID_NAME_BANANA;
    public static final String QUANTITY_DESC_CHEESE = " " + PREFIX_QUANTITY + VALID_QUANTITY_CHEESE;
    public static final String QUANTITY_DESC_BANANA = " " + PREFIX_QUANTITY + VALID_QUANTITY_BANANA;
    public static final String EXPIRYDATE_DESC_CHEESE = " " + PREFIX_EXPIRYDATE + VALID_EXPIRYDATE_CHEESE;
    public static final String EXPIRYDATE_DESC_BANANA = " " + PREFIX_EXPIRYDATE + VALID_EXPIRYDATE_BANANA;
    public static final String LOCATION_DESC_CHEESE = " " + PREFIX_LOCATION + VALID_LOCATION_CHEESE;
    public static final String LOCATION_DESC_BANANA = " " + PREFIX_LOCATION + VALID_LOCATION_BANANA;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "911a"; // 'a' not allowed in quantities
    public static final String INVALID_EXPIRYDATE_DESC = " " + PREFIX_EXPIRYDATE + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for locations
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditItemDescriptor DESC_CHEESE;
    public static final EditCommand.EditItemDescriptor DESC_BANANA;

    static {
        DESC_CHEESE = new EditItemDescriptorBuilder().withName(VALID_NAME_CHEESE)
            .withQuantity(VALID_QUANTITY_CHEESE).withExpiryDate(VALID_EXPIRYDATE_CHEESE)
            .withLocation(VALID_LOCATION_CHEESE)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BANANA = new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA)
            .withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_BANANA)
            .withLocation(VALID_LOCATION_BANANA)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the storemando, filtered item list and selected item in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StoreMando expectedStoreMando = new StoreMando(actualModel.getStoreMando());
        List<Item> expectedFilteredList = new ArrayList<>(actualModel.getFilteredItemList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStoreMando, actualModel.getStoreMando());
        assertEquals(expectedFilteredList, actualModel.getFilteredItemList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s storemando.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList().size());

        Item item = model.getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitName = item.getItemName().fullName.split("\\s+");
        model.updateFilteredItemList(new ItemNameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredItemList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show an empty list based on the given {@code item}
     * which is not in the {@code model}'s StoreMando.
     */
    public static void showEmptyListAfterFind(Model model, Item item) {
        final String[] splitName = item.getItemName().fullName.split("\\s+");
        model.updateFilteredItemList(new ItemNameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(0, model.getFilteredItemList().size());
    }

}
