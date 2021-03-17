package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.NameContainsKeywordsPredicate;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.EditResidenceDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_RESIDENCE1 = "Amber Park";
    public static final String VALID_NAME_RESIDENCE2 = "Duxton";
    public static final String VALID_ADDRESS_RESIDENCE1 = "14 Amber Gardens, 439960";
    public static final String VALID_ADDRESS_RESIDENCE2 = "Block 50, Cantonment Rd";

    public static final String VALID_BOOKING_DETAILS = "4 Adults";
    public static final String VALID_BOOKING_DETAILS_RESIDENCE1 = "4 Adults";
    public static final String VALID_BOOKING_DETAILS_RESIDENCE2 = "2 Teenagers";
    public static final String VALID_CLEAN_TAG = "y";
    public static final String VALID_UNCLEAN_TAG = "n";
    public static final String VALID_BOOKED_TAG = "y";
    public static final String VALID_UNBOOKED_TAG = "n";
    public static final String VALID_TAG_RESERVED = "reserved";
    public static final String VALID_TAG_REPAIR = "plumbing";

    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String BOOKING_DETAILS_DESC_RESIDENCE1 = " " + PREFIX_BOOKING_DETAILS + VALID_BOOKING_DETAILS;
    public static final String BOOKING_DETAILS_DESC_RESIDENCE2 = " " + PREFIX_BOOKING_DETAILS + VALID_BOOKING_DETAILS;
    public static final String NAME_DESC_RESIDENCE1 = " " + PREFIX_RESIDENCE_NAME + VALID_NAME_RESIDENCE1;
    public static final String NAME_DESC_RESIDENCE2 = " " + PREFIX_RESIDENCE_NAME + VALID_NAME_RESIDENCE2;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_RESIDENCE1 = " " + PREFIX_RESIDENCE_ADDRESS + VALID_ADDRESS_RESIDENCE1;
    public static final String ADDRESS_DESC_RESIDENCE2 = " " + PREFIX_RESIDENCE_ADDRESS + VALID_ADDRESS_RESIDENCE2;
    public static final String TAG_DESC_BOOKED = " " + PREFIX_TAG + VALID_BOOKED_TAG;
    public static final String TAG_DESC_UNBOOKED = " " + PREFIX_TAG + VALID_UNBOOKED_TAG;
    public static final String TAG_DESC_RESERVED = " " + PREFIX_TAG + VALID_TAG_RESERVED;
    public static final String TAG_DESC_REPAIR = " " + PREFIX_TAG + VALID_TAG_REPAIR;
    public static final String CLEAN_STATUS_DESC = " " + PREFIX_CLEAN_STATUS_TAG + VALID_CLEAN_TAG;
    public static final String UNCLEAN_STATUS_DESC = " " + PREFIX_CLEAN_STATUS_TAG + VALID_UNCLEAN_TAG;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_RESIDENCE_NAME + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a";
    // missing '@' symbol
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo";
    // empty string not allowed for addresses
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_RESIDENCE_ADDRESS;
    // empty string not allowed for booking details
    public static final String INVALID_BOOKING_DETAILS_DESC = " " + PREFIX_BOOKING_DETAILS;
    // must be 'y' or 'clean'
    public static final String INVALID_CLEAN_TAG_DESC = " " + PREFIX_CLEAN_STATUS_TAG + "yup";
    // must be 'n' or 'unclean'
    public static final String INVALID_UNCLEAN_TAG_DESC = " " + PREFIX_CLEAN_STATUS_TAG + "CLEAN IT!!";
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditResidenceDescriptor DESC_RESIDENCE1;
    public static final EditCommand.EditResidenceDescriptor DESC_RESIDENCE2;

    static {
        DESC_RESIDENCE1 = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE1).withBookingDetails(VALID_BOOKING_DETAILS_RESIDENCE1)
                .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_RESERVED).build();
        DESC_RESIDENCE2 = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE2).withBookingDetails(VALID_BOOKING_DETAILS_RESIDENCE2)
                .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_RESERVED, VALID_TAG_REPAIR).build();
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
        final String[] splitName = residence.getResidenceName().fullName.split("\\s+");
        model.updateFilteredResidenceList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredResidenceList().size());
    }

}
