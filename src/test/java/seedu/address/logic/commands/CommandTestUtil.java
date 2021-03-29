package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditPersonCommand.EditPersonPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditPersonPersonDescriptorBuilder;
import seedu.address.testutil.EditSessionDescriptorBuilder;


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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PERSON_TYPE_STUDENT = "student";
    public static final String VALID_PERSON_TYPE_TUTOR = "tutor";
    public static final String VALID_PERSON_ID_AMY = "s/1";
    public static final String VALID_PERSON_ID_BOB = "t/1";
    public static final String VALID_SESSION_ID_FIRST_SESSION = "c/1";
    public static final String VALID_SESSION_ID_SECOND_SESSION = "c/2";
    public static final String VALID_DAY_FIRST_SESSION = "MONDAY";
    public static final String VALID_DAY_SECOND_SESSION = "TUESDAY";
    public static final String VALID_SUBJECT_FIRST_SESSION = "SCIENCE";
    public static final String VALID_SUBJECT_SECOND_SESSION = "MATH";
    public static final String VALID_TIMESLOT_FIRST_SESSION = "12:00 to 13:00";
    public static final String VALID_TIMESLOT_SECOND_SESSION = "09:00 to 10:00";
    public static final String VALID_TAG_SESSION = "session";


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
    public static final String PERSON_TYPE_DESC_STUDENT = " " + PREFIX_PERSON_TYPE + VALID_PERSON_TYPE_STUDENT;
    public static final String PERSON_TYPE_DESC_TUTOR = " " + PREFIX_PERSON_TYPE + VALID_PERSON_TYPE_TUTOR;
    public static final String PERSON_TYPE_DESC_AMY = " " + PREFIX_PERSON_TYPE + VALID_PERSON_TYPE_STUDENT;
    public static final String PERSON_TYPE_DESC_BOB = " " + PREFIX_PERSON_TYPE + VALID_PERSON_TYPE_TUTOR;
    public static final String PERSON_ID_DESC_AMY = VALID_PERSON_ID_AMY;
    public static final String PERSON_ID_DESC_BOB = VALID_PERSON_ID_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_PERSON_TYPE_DESC = " " + PREFIX_PERSON_TYPE + "invalid";
    public static final String INVALID_PERSON_ID_DESC = "wrongid";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final EditSessionCommand.EditSessionDescriptor DESC_FIRST_SESSION;
    public static final EditSessionCommand.EditSessionDescriptor DESC_SECOND_SESSION;
    public static final EditPersonPersonDescriptor DESC_AMY;
    public static final EditPersonPersonDescriptor DESC_BOB;
    public static final EditPersonDescriptor DESC_AMY_EDIT;
    public static final EditPersonDescriptor DESC_BOB_EDIT;

    static {
        DESC_AMY = new EditPersonPersonDescriptorBuilder().withPersonType(VALID_PERSON_TYPE_STUDENT)
                .withPersonId(VALID_PERSON_ID_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonPersonDescriptorBuilder().withPersonType(VALID_PERSON_TYPE_TUTOR)
                .withPersonId(VALID_PERSON_ID_BOB)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_AMY_EDIT = new EditPersonDescriptorBuilder().withPersonType(VALID_PERSON_TYPE_STUDENT)
                .withPersonId(VALID_PERSON_ID_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB_EDIT = new EditPersonDescriptorBuilder().withPersonType(VALID_PERSON_TYPE_TUTOR)
                .withPersonId(VALID_PERSON_ID_BOB)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_FIRST_SESSION = new EditSessionDescriptorBuilder().withSessionId(VALID_SESSION_ID_FIRST_SESSION)
                .withDay(VALID_DAY_FIRST_SESSION)
                .withSubject(VALID_SUBJECT_FIRST_SESSION)
                .withTimeslot(VALID_TIMESLOT_FIRST_SESSION)
                .withTags(VALID_TAG_SESSION).build();
        DESC_SECOND_SESSION = new EditSessionDescriptorBuilder().withSessionId(VALID_SESSION_ID_SECOND_SESSION)
                .withDay(VALID_DAY_SECOND_SESSION)
                .withSubject(VALID_SUBJECT_SECOND_SESSION)
                .withTimeslot(VALID_TIMESLOT_SECOND_SESSION)
                .withTags(VALID_TAG_SESSION).build();
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

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
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

}
