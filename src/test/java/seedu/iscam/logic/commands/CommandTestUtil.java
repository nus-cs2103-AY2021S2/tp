package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_IMAGE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.iscam.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.commons.NameContainsKeywordsPredicate;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.MeetingContainsKeywordsPredicate;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.testutil.EditClientDescriptorBuilder;
import seedu.iscam.testutil.EditMeetingDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Valid values for Client Amy and Client Bob.
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91234567";
    public static final String VALID_PHONE_BOB = "62222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_LOCATION_AMY = "Block 312, Amy Street 1";
    public static final String VALID_LOCATION_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_PLAN_AMY = "Plan A";
    public static final String VALID_PLAN_BOB = "Plan B";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_IMAGE = "default.png";

    // String description of valid values for Client Amy and Client Bob.
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String PLAN_DESC_AMY = " " + PREFIX_PLAN + VALID_PLAN_AMY;
    public static final String PLAN_DESC_BOB = " " + PREFIX_PLAN + VALID_PLAN_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String IMAGE_DESC = " " + PREFIX_IMAGE + VALID_IMAGE;

    // Invalid values for Client
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for locations
    public static final String INVALID_PLAN_DESC = " " + PREFIX_PLAN + "Plan $"; // '$" no allowed in insurance plans
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_IMAGE_DESC = " " + PREFIX_IMAGE + "morgan.jp"; // File extension not allowed


    // Valid values for Meeting Cleo and Meeting Dan
    public static final String VALID_CLIENT_NAME_CLEO = "Cleo Patra";
    public static final String VALID_CLIENT_NAME_DAN = "Dan Ikris";
    public static final String VALID_DATETIME_CLEO = "28-10-2099 10:00";
    public static final String VALID_DATETIME_DAN = "29-02-2096 13:30";
    public static final String VALID_LOCATION_CLEO = "Starbucks, Tampines Hub";
    public static final String VALID_LOCATION_DAN = "Hon Sui Sen Memorial Library";
    public static final String VALID_DESCRIPTION_CLEO = "Discuss the upgrading of Insurance Plan";
    public static final String VALID_DESCRIPTION_DAN = "Discuss Insurance claim procedure";
    public static final String VALID_TAG_PREMIUM = "premium";
    public static final String VALID_TAG_URGENT = "urgent";
    public static final String VALID_STATUS_CLEO = "complete";
    public static final String VALID_STATUS_DAN = "incomplete";

    // String description of valid values for Meeting Cleo and Dan
    public static final String CLIENT_NAME_DESC_CLEO = " " + PREFIX_CLIENT + VALID_CLIENT_NAME_CLEO;
    public static final String CLIENT_NAME_DESC_DAN = " " + PREFIX_CLIENT + VALID_CLIENT_NAME_DAN;
    public static final String DATETIME_DESC_CLEO = " " + PREFIX_ON + VALID_DATETIME_CLEO;
    public static final String DATETIME_DESC_DAN = " " + PREFIX_ON + VALID_DATETIME_DAN;
    public static final String LOCATION_DESC_CLEO = " " + PREFIX_LOCATION + VALID_LOCATION_CLEO;
    public static final String LOCATION_DESC_DAN = " " + PREFIX_LOCATION + VALID_LOCATION_DAN;
    public static final String DESCRIPTION_DESC_CLEO = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CLEO;
    public static final String DESCRIPTION_DESC_DAN = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_DAN;
    public static final String TAG_DESC_PREMIUM = " " + PREFIX_TAG + VALID_TAG_PREMIUM;
    public static final String TAG_DESC_URGENT = " " + PREFIX_TAG + VALID_TAG_URGENT;
    public static final String STATUS_DESC_CLEO = " " + PREFIX_STATUS + VALID_STATUS_CLEO;
    public static final String STATUS_DESC_DAN = " " + PREFIX_STATUS + VALID_STATUS_DAN;

    // Invalid values for Meeting
    public static final String INVALID_DATETIME_DESC = " " + PREFIX_ON + "282099 10:00";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "Describe @$(*!)@#$";
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "done";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditClientDescriptor DESC_AMY;
    public static final EditCommand.EditClientDescriptor DESC_BOB;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_CLEO;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_DAN;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withLocation(VALID_LOCATION_AMY)
                .withPlan(VALID_PLAN_AMY).withImage(VALID_IMAGE).withTags(VALID_TAG_FRIEND).build();

        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withLocation(VALID_LOCATION_BOB)
                .withPlan(VALID_PLAN_BOB).withImage(VALID_IMAGE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_CLEO = new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_CLEO)
                .withDateTime(VALID_DATETIME_CLEO).withLocation(VALID_LOCATION_CLEO)
                .withDescription(VALID_DESCRIPTION_CLEO).withTags(VALID_TAG_PREMIUM)
                .withStatus(VALID_STATUS_CLEO).build();

        DESC_DAN = new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_DAN)
                .withDateTime(VALID_DATETIME_DAN).withLocation(VALID_LOCATION_DAN)
                .withDescription(VALID_DESCRIPTION_DAN).withTags(VALID_TAG_URGENT)
                .withStatus(VALID_STATUS_DAN).build();
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
     * - the iScam book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertClientCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // We are unable to defensively copy the model for comparison later,
        // so we can only do so by copying its components.
        ClientBook expectedClientBook = new ClientBook(actualModel.getClientBook());

        List<Client> expectedClientFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedClientBook, actualModel.getClientBook());
        assertEquals(expectedClientFilteredList, actualModel.getFilteredClientList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the filtered meeting list and selected meeting in {@code actualModel} remain unchanged
     */
    public static void assertMeetingCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // We are unable to defensively copy the model for comparison later,
        // so we can only do so by copying its components.
        MeetingBook expectedMeetingBook = new MeetingBook(actualModel.getMeetingBook());

        List<Meeting> expectedMeetingFilteredList = new ArrayList<>(actualModel.getFilteredMeetingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMeetingBook, actualModel.getMeetingBook());
        assertEquals(expectedMeetingFilteredList, actualModel.getFilteredMeetingList());
    }



    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s iScam book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s iScam book.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final String[] time = meeting.getDateTime().toString().split("\\s+");
        model.updateFilteredMeetingList(new MeetingContainsKeywordsPredicate(Arrays.asList(time[0])));

        assertEquals(1, model.getFilteredMeetingList().size());
    }

}
