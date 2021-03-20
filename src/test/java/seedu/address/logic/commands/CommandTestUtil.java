package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.StudentBook;
import seedu.address.model.Model;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MatriculationNumberContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_STATUS_BOB = "not vaccinated";
    public static final String VALID_DETAILS_AMY = "none";
    public static final String VALID_DETAILS_BOB = "peanut allergy";
    public static final String VALID_RESIDENCE_AMY = "RC4";
    public static final String VALID_RESIDENCE_BOB = "CAPT";

    public static final String VALID_DATE_AMY_APPOINTMENT = "2021-01-01";
    public static final String VALID_DATE_BOB_APPOINTMENT = "2021-01-02";
    public static final String VALID_START_TIME_AMY_APPOINTMENT = "16:00";
    public static final String VALID_START_TIME_BOB_APPOINTMENT = "17:00";
    public static final String VALID_END_TIME_AMY_APPOINTMENT = "16:30";
    public static final String VALID_END_TIME_BOB_APPOINTMENT = "17:30";

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
    public static final String END_TIME_DESC_AMY_APPOINTMENT = " " + PREFIX_END_TIME
            + VALID_END_TIME_AMY_APPOINTMENT;
    public static final String END_TIME_DESC_BOB_APPOINTMENT = " " + PREFIX_END_TIME
            + VALID_END_TIME_BOB_APPOINTMENT;

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
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END_TIME + "30min later";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withMatric(VALID_MATRIC_AMY)
                .withFaculty(VALID_FACULTY_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withVacStatus(VALID_STATUS_AMY).withMedDetails(VALID_DETAILS_AMY)
                .withSchoolRes(VALID_RESIDENCE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withMatric(VALID_MATRIC_BOB)
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
        StudentBook expectedAddressBook = new StudentBook(actualModel.getAddressBook());
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
        final String matriculationNumber = person.getMatriculationNumber().toString();
        model.updateFilteredPersonList(new MatriculationNumberContainsKeywordsPredicate(matriculationNumber));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person with the given {@code matriculationNumber} in the
     * {@code model}'s address book.
     */
    public static void showPersonWithMatricNum(Model model, MatriculationNumber matriculationNumber) {
        assertTrue(MatriculationNumber.isValidMatric(matriculationNumber.toString()));

        List<Person> personListTest = model.getFilteredPersonList();
        Person person = null;
        for (Person p : personListTest) {
            if (p.getMatriculationNumber().equals(matriculationNumber)) {
                person = p;
            }
        }

        assertTrue(person != null);
        final String[] splitName = person.getMatriculationNumber().toString().split("\\s+");
        model.updateFilteredPersonList(new MatriculationNumberContainsKeywordsPredicate((splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
