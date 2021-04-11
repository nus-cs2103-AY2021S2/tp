package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDY_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "83501122";
    public static final String VALID_PHONE_BOB = "65420011";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_STUDY_LEVEL_AMY = "Sec 5";
    public static final String VALID_STUDY_LEVEL_BOB = "Primary 2";
    public static final String VALID_GUARDIAN_PHONE_AMY = "90112344";
    public static final String VALID_GUARDIAN_PHONE_BOB = "98102211";
    public static final String VALID_RELATIONSHIP_AMY = "Mother";
    public static final String VALID_RELATIONSHIP_BOB = "Father";
    public static final String VALID_RECURRING_START_DATE_AMY = "2021-04-01";
    public static final String VALID_RECURRING_START_DATE_BOB = "2021-04-02";
    public static final String VALID_RECURRING_END_DATE_AMY = "2021-05-06";
    public static final String VALID_RECURRING_END_DATE_BOB = "2021-05-07";
    public static final String VALID_TIME = "12:00";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String STUDY_LEVEL_DESC_AMY = " " + PREFIX_STUDY_LEVEL + VALID_STUDY_LEVEL_AMY;
    public static final String STUDY_LEVEL_DESC_BOB = " " + PREFIX_STUDY_LEVEL + VALID_STUDY_LEVEL_BOB;
    public static final String GUARDIAN_PHONE_DESC_AMY = " " + PREFIX_GUARDIAN_PHONE + VALID_GUARDIAN_PHONE_AMY;
    public static final String GUARDIAN_PHONE_DESC_BOB = " " + PREFIX_GUARDIAN_PHONE + VALID_GUARDIAN_PHONE_BOB;
    public static final String RELATIONSHIP_DESC_AMY = " " + PREFIX_RELATIONSHIP + VALID_RELATIONSHIP_AMY;
    public static final String RELATIONSHIP_DESC_BOB = " " + PREFIX_RELATIONSHIP + VALID_RELATIONSHIP_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GUARDIAN_PHONE_DESC = " " + PREFIX_GUARDIAN_PHONE
        + "911a"; // 'a' not allowed in phones
    public static final String INVALID_STUDY_LEVEL_DESC = " " + PREFIX_STUDY_LEVEL + "  "; // empty study level
    public static final String INVALID_RELATIONSHIP_DESC = " " + PREFIX_RELATIONSHIP + "  "; // empty relationship


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;

    public static final String VALID_DATE = "2010-01-01";
    public static final String ANOTHER_VALID_DATE = "2011-11-11";
    public static final String VALID_DURATION = "60";
    public static final String VALID_SUBJECT = "Science";
    public static final String VALID_FEE = "30";
    public static final String VALID_REC_INTERVAL = "7";
    public static final String VALID_REC_END = "2010-01-15";

    public static final String INVALID_DURATION = "1000";
    public static final String INVALID_REC_INTERVAL = "8";
    public static final String INVALID_REC_END_BEFORE_START = "2009-01-03";
    public static final String INVALID_REC_END_BEFORE_INTERVAL = "2010-01-03";

    public static final String SESSION_VALID_DATE = " " + PREFIX_DATE + VALID_DATE;
    public static final String SESSION_ANOTHER_VALID_DATE = " " + PREFIX_DATE + ANOTHER_VALID_DATE;
    public static final String SESSION_VALID_TIME = " " + PREFIX_TIME + VALID_TIME;
    public static final String SESSION_VALID_DURATION = " " + PREFIX_DURATION + VALID_DURATION;
    public static final String SESSION_VALID_SUBJECT = " " + PREFIX_SUBJECT + VALID_SUBJECT;
    public static final String SESSION_VALID_FEE = " " + PREFIX_FEE + VALID_FEE;
    public static final String SESSION_INVALID_DURATION = " " + PREFIX_DURATION + INVALID_DURATION;

    public static final String REC_SESSION_VALID_INTERVAL = " " + PREFIX_INTERVAL + VALID_REC_INTERVAL;
    public static final String REC_SESSION_VALID_END = " " + PREFIX_END_DATE + VALID_REC_END;
    public static final String REC_SESSION_INVALID_INTERVAL = " " + PREFIX_INTERVAL + INVALID_REC_INTERVAL;
    public static final String REC_SESSION_INVALID_END_BEFORE_START = " " + PREFIX_END_DATE
            + INVALID_REC_END_BEFORE_START;
    public static final String REC_SESSION_INVALID_END_BEFORE_INTERVAL = " " + PREFIX_END_DATE
            + INVALID_REC_END_BEFORE_INTERVAL;
    public static final String REC_SESSION_INVALID_END_ON_START = " " + PREFIX_END_DATE + VALID_DATE;


    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the sessions of the student at the given {@code targetIndex}
     * in the {@code model}'s address book.
     */
    public static void showSessionsOfStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(student.getListOfSessions().size(),
                model.getFilteredStudentList().get(targetIndex.getZeroBased()).getListOfSessions().size());
    }

}
