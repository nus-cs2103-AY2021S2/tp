package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordPredicate;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ROLE_AMY = "Events Head";
    public static final String VALID_ROLE_BOB = "Member";
    public static final String VALID_TITLE_MARATHON = "MARATHON";
    public static final String VALID_DESCRIPTION_MARATHON = "At east coast park";
    public static final String VALID_TITLE_MEETING = "MEETING";
    public static final String VALID_DESCRIPTION_MEETING = "Board meeting";

    public static final String NEW_NAME_DESC_AMY = " " + PREFIX_NEW_NAME + VALID_NAME_AMY;
    public static final String NEW_NAME_DESC_BOB = " " + PREFIX_NEW_NAME + VALID_NAME_BOB;
    public static final String TITLE_DESC_TASK1 = " " + PREFIX_TITLE + VALID_TITLE_MARATHON;
    public static final String DESCRIPTION_TASK1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MARATHON;
    public static final String NAME_DESC_AMY = VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = VALID_NAME_BOB;

    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;

    public static final String INVALID_NAME_DESC = " " + "James&"; // '&' not allowed in names
    public static final String INVALID_NEW_NAME_DESC = " " + PREFIX_NEW_NAME + "James&"; // '&' not allowed in new names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditMemberCommand.EditMemberDescriptor DESC_AMY;
    public static final EditMemberCommand.EditMemberDescriptor DESC_BOB;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK1;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK2;


    static {
        DESC_AMY = new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        DESC_TASK1 = new EditTaskDescriptorBuilder().withTitle(VALID_DESCRIPTION_MARATHON)
                .withDescription(VALID_DESCRIPTION_MARATHON).build();
        DESC_TASK2 = new EditTaskDescriptorBuilder().withTitle(VALID_DESCRIPTION_MEETING)
                .withDescription(VALID_DESCRIPTION_MEETING).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
        List<Task> expectedFilteredTaskList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
        assertEquals(expectedFilteredTaskList, actualModel.getFilteredTaskList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        model.updateFilteredTaskList(new TitleContainsKeywordPredicate(Arrays.asList(task.getTitle().taskTitle)));

        assertEquals(1, model.getFilteredTaskList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code name} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtName(Model model, Name name) {
        Person person = null;

        for (Person currentPerson : model.getFilteredPersonList()) {
            Name currentName = currentPerson.getName();

            if (currentName.equals(name)) {
                person = currentPerson;
                break;
            }
        }

        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
}
