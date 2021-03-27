package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.EditPassengerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final DayOfWeek VALID_TRIPDAY_FRIDAY = DayOfWeek.FRIDAY;
    public static final DayOfWeek VALID_TRIPDAY_MONDAY = DayOfWeek.MONDAY;
    public static final LocalTime VALID_TRIPTIME_EVENING = LocalTime.of(18, 00);
    public static final LocalTime VALID_TRIPTIME_MORNING = LocalTime.of(8, 30);
    public static final DayOfWeek VALID_TRIPDAY_BOB = DayOfWeek.THURSDAY;
    public static final LocalTime VALID_TRIPTIME_BOB = LocalTime.of(19, 30);
    public static final double VALID_PRICE_AMY = 1.69;
    public static final double VALID_PRICE_BOB = 6.9;
    public static final String VALID_TRIPDAY_STR_FRIDAY = "FRIDAY";
    public static final String VALID_TRIPDAY_STR_MONDAY = "MONDAY";
    public static final String VALID_TRIPTIME_STR_EVENING = "1800";
    public static final String VALID_TRIPTIME_STR_MORNING = "0830";
    public static final String VALID_TRIPDAY_STR_BOB = "THURSDAY";
    public static final String VALID_TRIPTIME_STR_BOB = "1930";
    public static final String VALID_PRICE_STR_AMY = "1.69";
    public static final String VALID_PRICE_STR_BOB = "6.9";

    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_COMMUTER_1 = "1";
    public static final String VALID_COMMUTER_2 = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TRIPDAY_DESC_FRIDAY = " " + PREFIX_TRIPDAY + VALID_TRIPDAY_STR_FRIDAY;
    public static final String TRIPDAY_DESC_MONDAY = " " + PREFIX_TRIPDAY + VALID_TRIPDAY_STR_MONDAY;
    public static final String TRIPTIME_DESC_EVENING = " " + PREFIX_TRIPTIME + VALID_TRIPTIME_STR_EVENING;
    public static final String TRIPTIME_DESC_MORNING = " " + PREFIX_TRIPTIME + VALID_TRIPTIME_STR_MORNING;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TRIPDAY_DESC_BOB = " " + PREFIX_TRIPDAY + VALID_TRIPDAY_STR_BOB;
    public static final String TRIPTIME_DESC_BOB = " " + PREFIX_TRIPTIME + VALID_TRIPTIME_STR_BOB;
    public static final String PRICE_DESC_AMY = " " + PREFIX_PRICE + VALID_PRICE_STR_AMY;
    public static final String PRICE_DESC_BOB = " " + PREFIX_PRICE + VALID_PRICE_STR_BOB;
    public static final String COMMUTER_DESC_1 = " " + PREFIX_COMMUTER + VALID_COMMUTER_1;
    public static final String COMMUTER_DESC_2 = " " + PREFIX_COMMUTER + VALID_COMMUTER_2;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TRIPDAY = " " + PREFIX_TRIPDAY + "FUNDAY"; // not among the valid week days
    public static final String INVALID_TRIPTIME = " " + PREFIX_TRIPTIME + "21032103"; // not in the 24 hour time format
    public static final String INVALID_PRICE = " " + PREFIX_PRICE + "1/12"; // not a decimal number
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_COMMUTER = " " + PREFIX_COMMUTER + "a"; // 'a' not allowed in commuter

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPassengerDescriptor DESC_AMY;
    public static final EditCommand.EditPassengerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPassengerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPassengerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
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
     * - the address book, filtered passenger list and selected passenger in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Passenger> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPassengerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPassengerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the passenger at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPassengerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPassengerList().size());

        Passenger passenger = model.getFilteredPassengerList().get(targetIndex.getZeroBased());
        final String[] splitName = passenger.getName().fullName.split("\\s+");
        model.updateFilteredPassengerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPassengerList().size());
    }

}
