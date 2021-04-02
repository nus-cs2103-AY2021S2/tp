package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.model.Model;
import seedu.address.model.TutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.tutor.NameContainsKeywordsPredicate;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.EditTutorDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_GENDER_AMY = "Female";
    public static final String VALID_GENDER_BOB = "Male";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_SUBJECT_NAME = "English";
    public static final String VALID_SUBJECT_LEVEL = "Secondary 4";
    public static final String VALID_SUBJECT_RATE = "60";
    public static final String VALID_SUBJECT_EXPERIENCE = "5";
    public static final String VALID_SUBJECT_QUALIFICATION = "A-Level";

    public static final String VALID_SUBJECT_NAME_SCIENCE = "Science";
    public static final String VALID_GRADED_ITEM_SCIENCE = "Lab 1";
    public static final String VALID_GRADE_SCIENCE = "B";

    public static final String VALID_SCHEDULE_TITLE_ONE = "Maths Homework";
    public static final String VALID_SCHEDULE_TIMEFROM_ONE = "2021-03-24 10:00AM";
    public static final String VALID_SCHEDULE_TIMETO_ONE = "2021-03-24 12:00PM";
    public static final String VALID_SCHEDULE_DESCRIPTION_ONE = "Chapter 5 Page 841";
    public static final String VALID_SCHEDULE_TITLE_TWO = "Science Homework";
    public static final String VALID_SCHEDULE_TIMEFROM_TWO = "2021-03-27 12:00PM";
    public static final String VALID_SCHEDULE_TIMETO_TWO = "2021-03-27 2:00PM";
    public static final String VALID_SCHEDULE_DESCRIPTION_TWO = "Chapter 3 Page 21";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String SUBJECT_NAME_DESC = " " + PREFIX_SUBJECT_NAME + VALID_SUBJECT_NAME;
    public static final String SUBJECT_LEVEL_DESC = " " + PREFIX_EDUCATION_LEVEL + VALID_SUBJECT_LEVEL;
    public static final String SUBJECT_RATE_DESC = " " + PREFIX_RATE + VALID_SUBJECT_RATE;
    public static final String SUBJECT_EXPERIENCE_DESC = " " + PREFIX_YEAR + VALID_SUBJECT_EXPERIENCE;
    public static final String SUBJECT_QUALIFICATION_DESC = " " + PREFIX_QUALIFICATION + VALID_SUBJECT_QUALIFICATION;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTutorDescriptor DESC_AMY;
    public static final EditCommand.EditTutorDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTutorDescriptorBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withSubjectList(
                        VALID_SUBJECT_NAME,
                        VALID_SUBJECT_LEVEL,
                        VALID_SUBJECT_RATE,
                        VALID_SUBJECT_EXPERIENCE,
                        VALID_SUBJECT_QUALIFICATION)
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TutorBook expectedAddressBook = new TutorBook(actualModel.getTutorBook());
        List<Tutor> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTutorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getTutorBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTutorList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorList().size());

        Tutor tutor = model.getFilteredTutorList().get(targetIndex.getZeroBased());
        final String[] splitName = tutor.getName().fullName.split("\\s+");
        model.updateFilteredTutorList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTutorList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final AppointmentDateTime date = appointment.getTimeFrom();

        model.updateFilteredAppointmentList(new DateViewPredicate(date));
        assertEquals(1, model.getFilteredAppointmentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetDateTime} in the
     * {@code model}'s appointment book.
     */
    public static void showAppointmentAtDate(Model model, AppointmentDateTime targetDateTime) {
        assertTrue(model.hasAppointmentDateTime(targetDateTime));

        DateViewPredicate predicate = new DateViewPredicate(targetDateTime);
        model.updateFilteredAppointmentList(predicate);
        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
