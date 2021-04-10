package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.NameContainsKeywordsPredicate;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.EditBookingDescriptorBuilder;
import seedu.address.testutil.EditResidenceDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_RESIDENCE1 = "Amber Park";
    public static final String VALID_NAME_RESIDENCE2 = "Duxton";
    public static final String VALID_ADDRESS_RESIDENCE1 = "14 Amber Gardens, 439960";
    public static final String VALID_ADDRESS_RESIDENCE2 = "Block 50, Cantonment Rd";

    public static final String VALID_CLEAN_TAG = "y";
    public static final String VALID_UNCLEAN_TAG = "n";
    public static final String VALID_BOOKED_TAG = "y";
    public static final String VALID_UNBOOKED_TAG = "n";
    public static final String VALID_TAG_RESERVED = "reserved";
    public static final String VALID_TAG_REPAIR = "plumbing";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String VALID_PHONE_BOOKING1 = "11111111";
    public static final String VALID_PHONE_BOOKING2 = "22222222";
    public static final String VALID_NAME_BOOKING1 = "Amy";
    public static final String VALID_NAME_BOOKING2 = "John";
    public static final String VALID_BOOKING_START1 = "03-03-2021";
    public static final String VALID_BOOKING_START2 = "30-03-2021";
    public static final String VALID_BOOKING_END1 = "05-05-2021";
    public static final String VALID_BOOKING_END2 = "30-05-2021";

    public static final LocalDate INVALID_BOOKING_END = LocalDate.parse("01-01-1990", DATE_TIME_FORMATTER);
    public static final LocalDate OVERLAP_BOOKING_DATE = LocalDate.parse("03-03-2020", DATE_TIME_FORMATTER);

    public static final String NAME_DESC_RESIDENCE1 = " " + PREFIX_NAME + VALID_NAME_RESIDENCE1;
    public static final String NAME_DESC_RESIDENCE2 = " " + PREFIX_NAME + VALID_NAME_RESIDENCE2;
    public static final String ADDRESS_DESC_RESIDENCE1 = " " + PREFIX_RESIDENCE_ADDRESS + VALID_ADDRESS_RESIDENCE1;
    public static final String ADDRESS_DESC_RESIDENCE2 = " " + PREFIX_RESIDENCE_ADDRESS + VALID_ADDRESS_RESIDENCE2;
    public static final String TAG_DESC_BOOKED = " " + PREFIX_TAG + VALID_BOOKED_TAG;
    public static final String TAG_DESC_UNBOOKED = " " + PREFIX_TAG + VALID_UNBOOKED_TAG;
    public static final String TAG_DESC_RESERVED = " " + PREFIX_TAG + VALID_TAG_RESERVED;
    public static final String TAG_DESC_REPAIR = " " + PREFIX_TAG + VALID_TAG_REPAIR;
    public static final String CLEAN_STATUS_DESC = " " + PREFIX_CLEAN_STATUS_TAG + VALID_CLEAN_TAG;
    public static final String UNCLEAN_STATUS_DESC = " " + PREFIX_CLEAN_STATUS_TAG + VALID_UNCLEAN_TAG;
    public static final String PHONE_DESC_BOOKING1 = " " + PREFIX_PHONE + VALID_PHONE_BOOKING1;
    public static final String PHONE_DESC_BOOKING2 = " " + PREFIX_PHONE + VALID_PHONE_BOOKING2;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a";
    // empty string not allowed for addresses
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_RESIDENCE_ADDRESS;
    // must be 'y' or 'clean'
    public static final String INVALID_CLEAN_TAG_DESC = " " + PREFIX_CLEAN_STATUS_TAG + "yup";
    // must be 'n' or 'unclean'
    public static final String INVALID_UNCLEAN_TAG_DESC = " " + PREFIX_CLEAN_STATUS_TAG + "CLEAN IT!!";
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "renovate*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditResidenceDescriptor DESC_RESIDENCE1;
    public static final EditCommand.EditResidenceDescriptor DESC_RESIDENCE2;
    public static final EditBookingCommand.EditBookingDescriptor DESC_BOOKING1;
    public static final EditBookingCommand.EditBookingDescriptor DESC_BOOKING2;

    static {
        DESC_RESIDENCE1 = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE1)
                .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_RESERVED).build();
        DESC_RESIDENCE2 = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE2)
                .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_RESERVED, VALID_TAG_REPAIR).build();
        DESC_BOOKING1 = new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING1)
                .withPhone(VALID_PHONE_BOOKING1)
                .withStartDate(LocalDate.parse(VALID_BOOKING_START1, DATE_TIME_FORMATTER))
                .withEndDate(LocalDate.parse(VALID_BOOKING_END1, DATE_TIME_FORMATTER))
                .build();
        DESC_BOOKING2 = new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING2)
                .withPhone(VALID_PHONE_BOOKING1)
                .withStartDate(LocalDate.parse(VALID_BOOKING_START2, DATE_TIME_FORMATTER))
                .withEndDate(LocalDate.parse(VALID_BOOKING_END2, DATE_TIME_FORMATTER))
                .build();
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
     * - the residence tracker, filtered residence list and selected residence in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ResidenceTracker expectedAddressBook = new ResidenceTracker(actualModel.getResidenceTracker());
        List<Residence> expectedFilteredList = new ArrayList<>(actualModel.getFilteredResidenceList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getResidenceTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredResidenceList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the residence at the given {@code targetIndex} in the
     * {@code model}'s Residence Tracker.
     */
    public static void showResidenceAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredResidenceList().size());

        Residence residence = model.getFilteredResidenceList().get(targetIndex.getZeroBased());
        final String[] splitName = residence.getResidenceName().toString().split("\\s+");
        model.updateFilteredResidenceList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredResidenceList().size());
    }

}
