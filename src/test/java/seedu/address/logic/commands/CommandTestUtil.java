package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_WEEKLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.UiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.address.testutil.UpdateContactDescriptorBuilder;
import seedu.address.testutil.UpdateGroupmateDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_SYLPH = "Sylphiette Greyrat";
    public static final String VALID_NAME_ROXY = "Roxy Migurdia";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_ROLE_LEADER = "leader";
    public static final String VALID_ROLE_MAGICIAN = "magician";
    public static final String VALID_INDEX_ONE = "1";
    public static final String VALID_INDEX_TWO = "2";
    public static final String VALID_DESCRIPTION = " " + PREFIX_DESCRIPTION + "CS2106 Tutorial";
    public static final String VALID_DEADLINE_DATE = " " + PREFIX_DEADLINE_DATE + "01-01-2020";
    public static final String VALID_EVENT_DATE = " " + PREFIX_EVENT_DATE + "01-01-2020";
    public static final String VALID_EVENT_TIME = " " + PREFIX_EVENT_TIME + "1730";
    public static final String VALID_EVENT_WEEKLY = " " + PREFIX_EVENT_WEEKLY + "N";
    public static final String VALID_PROJECT_NAME = " " + PREFIX_NAME + "CS2103T team project";
    public static final String INVALID_DESCRIPTION = " " + PREFIX_DESCRIPTION + "";
    public static final String INVALID_DEADLINE_DATE = " " + PREFIX_DEADLINE_DATE + "01/01-2020";
    public static final String INVALID_EVENT_DATE = " " + PREFIX_EVENT_DATE + "01-01/2020";
    public static final String INVALID_EVENT_TIME = " " + PREFIX_EVENT_TIME + "17-30";
    public static final String INVALID_EVENT_WEEKLY = " " + PREFIX_EVENT_WEEKLY + "Maybe";
    public static final String INVALID_PROJECT_NAME = " " + PREFIX_NAME + "   ";


    public static final String NAME_DESC_SYLPH = " " + PREFIX_NAME + VALID_NAME_SYLPH;
    public static final String ROLE_DESC_LEADER = " " + PREFIX_ROLE + VALID_ROLE_LEADER;
    public static final String NAME_DESC_ROXY = " " + PREFIX_NAME + VALID_NAME_ROXY;
    public static final String ROLE_DESC_MAGICIAN = " " + PREFIX_ROLE + VALID_ROLE_MAGICIAN;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INDEX_STANDALONE_ONE = " " + VALID_INDEX_ONE;
    public static final String INDEX_STANDALONE_TWO = " " + VALID_INDEX_TWO;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags


    public static final String INVALID_INDEX_STANDALONE = " " + "0";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final UpdateContactCommand.UpdateContactDescriptor DESC_AMY;
    public static final UpdateContactCommand.UpdateContactDescriptor DESC_BOB;
    public static final UpdateDeadlineCommand.UpdateDeadlineDescriptor DESC_DEADLINE_1;
    public static final UpdateDeadlineCommand.UpdateDeadlineDescriptor DESC_DEADLINE_2;
    public static final UpdateEventCommand.UpdateEventDescriptor DESC_EVENT_1;
    public static final UpdateEventCommand.UpdateEventDescriptor DESC_EVENT_2;

    public static final UpdateGroupmateCommand.UpdateGroupmateDescriptor DESC_SYLPH;
    public static final UpdateGroupmateCommand.UpdateGroupmateDescriptor DESC_ROXY;

    static {
        DESC_AMY = new UpdateContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new UpdateContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_DEADLINE_1 = new UpdateDeadlineCommand.UpdateDeadlineDescriptor();
        DESC_DEADLINE_1.setDescription("deadline");
        DESC_DEADLINE_1.setDate(LocalDate.of(2020, 1, 1));

        DESC_DEADLINE_2 = new UpdateDeadlineCommand.UpdateDeadlineDescriptor();
        DESC_DEADLINE_2.setDescription("close deadline");
        DESC_DEADLINE_2.setDate(LocalDate.of(2020, 2, 1));

        DESC_EVENT_1 = new UpdateEventCommand.UpdateEventDescriptor();
        DESC_EVENT_1.setDescription("Tutorial");
        DESC_EVENT_1.setDate(LocalDate.of(2020, 1, 1));
        DESC_EVENT_1.setTime(LocalTime.of(12, 30));
        DESC_EVENT_1.setIsWeekly(false);

        DESC_EVENT_2 = new UpdateEventCommand.UpdateEventDescriptor();
        DESC_EVENT_2.setDescription("Tutorial 2");
        DESC_EVENT_2.setDate(LocalDate.of(2020, 2, 1));
        DESC_EVENT_2.setTime(LocalTime.of(14, 30));
        DESC_EVENT_2.setIsWeekly(true);

        DESC_SYLPH = new UpdateGroupmateDescriptorBuilder().withName(VALID_NAME_SYLPH)
                .withRoles(VALID_ROLE_LEADER).build();
        DESC_ROXY = new UpdateGroupmateDescriptorBuilder().withName(VALID_NAME_ROXY)
                .withRoles(VALID_ROLE_MAGICIAN).build();
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and a {@code uiCommand}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            UiCommand uiCommand, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, uiCommand);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the CoLAB folder, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ColabFolder expectedColabFolder = new ColabFolder(actualModel.getColabFolder());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedColabFolder, actualModel.getColabFolder());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s contact list.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s projects folder.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getProjectName().projectName.split("\\s+");
        model.updateFilteredProjectList(new ProjectNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProjectList().size());
    }
}
