package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.gradecommands.EditGradeCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.model.GradeBook;
import seedu.address.model.Model;
import seedu.address.model.TutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.grade.Grade;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateViewPredicate;
import seedu.address.model.tutor.NameContainsKeywordsPredicate;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.EditGradeDescriptorBuilder;
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

    public static final String VALID_SUBJECT_NAME_MATHS = "Mathematics";
    public static final String VALID_GRADED_ITEM_MATHS = "Midterm Exam";
    public static final String VALID_GRADE_MATHS = "A1";
    public static final String VALID_SUBJECT_NAME_SCIENCE = "Science";
    public static final String VALID_GRADED_ITEM_SCIENCE = "Lab 1";
    public static final String VALID_GRADE_SCIENCE = "B3";
    public static final String VALID_SUBJECT_NAME_PHYSICS = "Physics";
    public static final String VALID_GRADED_ITEM_PHYSICS = "Quiz";
    public static final String VALID_GRADE_PHYSICS = "D7";

    public static final String VALID_SCHEDULE_TITLE_ONE = "Maths Homework";
    public static final String VALID_SCHEDULE_DATE_ONE = "2021-05-24";
    public static final String VALID_SCHEDULE_TIME_FROM_ONE = "10:00AM";
    public static final String VALID_SCHEDULE_TIME_TO_ONE = "12:00PM";
    public static final String VALID_SCHEDULE_DATE_TIME_FROM_ONE = VALID_SCHEDULE_DATE_ONE + " "
            + VALID_SCHEDULE_TIME_FROM_ONE;
    public static final String VALID_SCHEDULE_DATE_TIME_TO_ONE = VALID_SCHEDULE_DATE_ONE + " "
            + VALID_SCHEDULE_TIME_TO_ONE;
    public static final String VALID_SCHEDULE_DESCRIPTION_ONE = "Chapter 5 Page 841";

    public static final String VALID_SCHEDULE_TITLE_TWO = "Science Homework";
    public static final String VALID_SCHEDULE_DESCRIPTION_TWO = "Chapter 3 Page 21";
    public static final String VALID_SCHEDULE_DATE_TWO = "2021-05-27";
    public static final String VALID_SCHEDULE_TIME_FROM_TWO = "12:00PM";
    public static final String VALID_SCHEDULE_TIME_TO_TWO = "2:00PM";
    public static final String VALID_SCHEDULE_DATE_TIME_FROM_TWO = VALID_SCHEDULE_DATE_TWO + " "
            + VALID_SCHEDULE_TIME_FROM_TWO;
    public static final String VALID_SCHEDULE_DATE_TIME_TO_TWO = VALID_SCHEDULE_DATE_TWO + " "
            + VALID_SCHEDULE_TIME_TO_TWO;

    public static final String TITLE_DESC_ONE = " " + PREFIX_TITLE + VALID_SCHEDULE_TITLE_ONE;
    public static final String TITLE_DESC_TWO = " " + PREFIX_TITLE + VALID_SCHEDULE_TITLE_TWO;
    public static final String DATE_DESC_ONE = " " + PREFIX_DATE + VALID_SCHEDULE_DATE_ONE;
    public static final String DATE_DESC_TWO = " " + PREFIX_DATE + VALID_SCHEDULE_DATE_TWO;
    public static final String TIME_FROM_DESC_ONE = " " + PREFIX_TIME_FROM + VALID_SCHEDULE_TIME_FROM_ONE;
    public static final String TIME_FROM_DESC_TWO = " " + PREFIX_TIME_FROM + VALID_SCHEDULE_TIME_FROM_TWO;
    public static final String TIME_TO_DESC_ONE = " " + PREFIX_TIME_TO + VALID_SCHEDULE_TIME_TO_ONE;
    public static final String TIME_TO_DESC_TWO = " " + PREFIX_TIME_TO + VALID_SCHEDULE_TIME_TO_TWO;
    public static final String DESC_DESC_ONE = " " + PREFIX_DESCRIPTION + VALID_SCHEDULE_DESCRIPTION_ONE;
    public static final String DESC_DESC_TWO = " " + PREFIX_DESCRIPTION + VALID_SCHEDULE_DESCRIPTION_TWO;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Homework&"; // '&' not allowed in title
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "20/5/2021"; // date must be in YYYY-MM-DD format
    public static final String INVALID_TIME_FROM_DESC = " " + PREFIX_TIME_FROM + "13:00 PM"; // invalid time
    public static final String INVALID_TIME_TO_DESC = " " + PREFIX_TIME_TO + "21:00"; // 24 hours timing not accepted
    public static final String INVALID_DESC_DESC = " " + PREFIX_DESCRIPTION; // empty string not allowed for description

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

    public static final String SUBJECT_DESC_MATHS = " " + PREFIX_SUBJECT_NAME + VALID_SUBJECT_NAME_MATHS;
    public static final String SUBJECT_DESC_SCIENCE = " " + PREFIX_SUBJECT_NAME + VALID_SUBJECT_NAME_SCIENCE;
    public static final String GRADED_ITEM_DESC_MATHS = " " + PREFIX_GRADED_ITEM + VALID_GRADED_ITEM_MATHS;
    public static final String GRADED_ITEM_DESC_SCIENCE = " " + PREFIX_GRADED_ITEM + VALID_GRADED_ITEM_SCIENCE;
    public static final String GRADE_DESC_MATHS = " " + PREFIX_GRADE + VALID_GRADE_MATHS;
    public static final String GRADE_DESC_SCIENCE = " " + PREFIX_GRADE + VALID_GRADE_SCIENCE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_SUBJECT_DESC = " "
            + PREFIX_SUBJECT_NAME + "Science&"; // '&' not allowed in subject names
    public static final String INVALID_GRADED_ITEM_DESC = " "
            + PREFIX_GRADED_ITEM + "Midterm&"; // '&' not allowed in graded items
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + "A-"; // '-' not allowed in grade

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTutorDescriptor DESC_AMY;
    public static final EditCommand.EditTutorDescriptor DESC_BOB;

    public static final EditGradeCommand.EditGradeDescriptor DESC_MATHS;
    public static final EditGradeCommand.EditGradeDescriptor DESC_SCIENCE;

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

        // EditGradeDescriptor
        DESC_MATHS = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_MATHS)
                .withGradedItem(VALID_GRADED_ITEM_MATHS).withGrade(VALID_GRADE_MATHS).build();
        DESC_SCIENCE = new EditGradeDescriptorBuilder()
                .withSubject(VALID_SUBJECT_NAME_SCIENCE)
                .withGradedItem(VALID_GRADED_ITEM_SCIENCE).withGrade(VALID_GRADE_SCIENCE).build();
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
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the grade book, filtered grade list and selected grade in {@code actualModel} remain unchanged
     */
    public static void assertGradeCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        GradeBook expectedGradeBook = new GradeBook(actualModel.getGradeBook());
        List<Grade> expectedFilteredList = new ArrayList<>(actualModel.getFilteredGradeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedGradeBook, actualModel.getGradeBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredGradeList());
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
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showScheduleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredScheduleList().size());

        Schedule schedule = model.getFilteredScheduleList().get(targetIndex.getZeroBased());
        final AppointmentDateTime date = schedule.getTimeFrom();

        model.updateFilteredScheduleList(new ScheduleDateViewPredicate(date));
        assertEquals(1, model.getFilteredScheduleList().size());
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

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetDateTime} in the
     * {@code model}'s schedule tracker.
     */
    public static void showScheduleAtDate(Model model, AppointmentDateTime targetDateTime) {
        ScheduleDateViewPredicate predicate = new ScheduleDateViewPredicate(targetDateTime);
        model.updateFilteredScheduleList(predicate);
        assertEquals(1, model.getFilteredScheduleList().size());
    }
}
