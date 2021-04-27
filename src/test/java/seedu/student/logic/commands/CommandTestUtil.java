package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.student.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;
import static seedu.student.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.student.commons.core.index.Index;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.StudentBook;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;
import seedu.student.model.student.exceptions.MatriculationNumberDoesNotExistException;
import seedu.student.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MATRIC_AMY = "A0199264N";
    public static final String VALID_MATRIC_BOB = "A0245431K";
    public static final String VALID_FACULTY_AMY = "MUSIC";
    public static final String VALID_FACULTY_BOB = "FASS";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_STATUS_AMY = "vaccinated";
    public static final String VALID_STATUS_BOB = "unvaccinated";
    public static final String VALID_DETAILS_AMY = "none";
    public static final String VALID_DETAILS_BOB = "peanut allergy";
    public static final String VALID_RESIDENCE_AMY = "RC4";
    public static final String VALID_RESIDENCE_BOB = "CAPT";

    public static final String VALID_DATE_AMY_APPOINTMENT = "2021-01-01";
    public static final String VALID_DATE_BOB_APPOINTMENT = "2021-01-02";
    public static final String VALID_START_TIME_AMY_APPOINTMENT = "16:00";
    public static final String VALID_START_TIME_BOB_APPOINTMENT = "17:00";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MATRIC_DESC_AMY = " " + PREFIX_MATRICULATION_NUMBER + VALID_MATRIC_AMY;
    public static final String MATRIC_DESC_BOB = " " + PREFIX_MATRICULATION_NUMBER + VALID_MATRIC_BOB;
    public static final String FACULTY_DESC_AMY = " " + PREFIX_FACULTY + VALID_FACULTY_AMY;
    public static final String FACULTY_DESC_BOB = " " + PREFIX_FACULTY + VALID_FACULTY_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String STATUS_DESC_AMY = " " + PREFIX_VACCINATION_STATUS + VALID_STATUS_AMY;
    public static final String STATUS_DESC_BOB = " " + PREFIX_VACCINATION_STATUS + VALID_STATUS_BOB;
    public static final String DETAILS_DESC_AMY = " " + PREFIX_MEDICAL_DETAILS + VALID_DETAILS_AMY;
    public static final String DETAILS_DESC_BOB = " " + PREFIX_MEDICAL_DETAILS + VALID_DETAILS_BOB;
    public static final String RESIDENCE_DESC_AMY = " " + PREFIX_SCHOOL_RESIDENCE + VALID_RESIDENCE_AMY;
    public static final String RESIDENCE_DESC_BOB = " " + PREFIX_SCHOOL_RESIDENCE + VALID_RESIDENCE_BOB;

    public static final String DATE_DESC_AMY_APPOINTMENT = " " + PREFIX_DATE + VALID_DATE_AMY_APPOINTMENT;
    public static final String DATE_DESC_BOB_APPOINTMENT = " " + PREFIX_DATE + VALID_DATE_BOB_APPOINTMENT;
    public static final String START_TIME_DESC_AMY_APPOINTMENT = " " + PREFIX_START_TIME
            + VALID_START_TIME_AMY_APPOINTMENT;
    public static final String START_TIME_DESC_BOB_APPOINTMENT = " " + PREFIX_START_TIME
            + VALID_START_TIME_BOB_APPOINTMENT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MATRIC_DESC = " " + PREFIX_MATRICULATION_NUMBER
            + "A01Z34567F"; // 'Z' not allowed in matriculation number
    public static final String INVALID_FACULTY_DESC = " " + PREFIX_FACULTY + "SOC";
    public static final String INVALID_STATUS_DESC = " " + PREFIX_VACCINATION_STATUS + "v@ccin@ted";
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_DETAILS_DESC = " " + PREFIX_MEDICAL_DETAILS;
    public static final String INVALID_RESIDENCE_DESC = " " + PREFIX_SCHOOL_RESIDENCE + "rc5";

    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "next week";
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START_TIME + "twelve o'clock";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).withMatric(VALID_MATRIC_AMY)
                .withFaculty(VALID_FACULTY_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withVacStatus(VALID_STATUS_AMY).withMedDetails(VALID_DETAILS_AMY)
                .withSchoolRes(VALID_RESIDENCE_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).withMatric(VALID_MATRIC_BOB)
                .withFaculty(VALID_FACULTY_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withVacStatus(VALID_STATUS_BOB).withMedDetails(VALID_DETAILS_BOB)
                .withSchoolRes(VALID_RESIDENCE_BOB).build();
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
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | ParseException | MatriculationNumberDoesNotExistException ce) {
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
     * - the student book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getStudentBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getStudentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final MatriculationNumber matriculationNumber = student.getMatriculationNumber();
        model.updateFilteredStudentList(new StudentContainsMatriculationNumberPredicate(matriculationNumber));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student with the given {@code matriculationNumber} in the
     * {@code model}'s student book.
     */
    public static void showStudentWithMatricNum(Model model, MatriculationNumber matriculationNumber) {
        assertTrue(MatriculationNumber.isValidMatric(matriculationNumber.toString()));

        List<Student> studentListTest = model.getFilteredStudentList();
        Student student = null;
        for (Student p : studentListTest) {
            if (p.getMatriculationNumber().equals(matriculationNumber)) {
                student = p;
            }
        }

        assertTrue(student != null);
        final MatriculationNumber studentMatriculationNumber = student.getMatriculationNumber();
        model.updateFilteredStudentList(new StudentContainsMatriculationNumberPredicate((studentMatriculationNumber)));

        assertEquals(1, model.getFilteredStudentList().size());
    }
}
