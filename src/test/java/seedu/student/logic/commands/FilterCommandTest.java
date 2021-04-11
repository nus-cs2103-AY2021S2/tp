package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.commons.core.Messages.MESSAGE_NO_STUDENTS_ARE_LISTED;
import static seedu.student.commons.core.Messages.MESSAGE_STUDENTS_ARE_LISTED;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalStudents.ALICE;
import static seedu.student.testutil.TypicalStudents.BENSON;
import static seedu.student.testutil.TypicalStudents.DANIEL;
import static seedu.student.testutil.TypicalStudents.ELLE;
import static seedu.student.testutil.TypicalStudents.FIONA;
import static seedu.student.testutil.TypicalStudents.GEORGE;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.student.FacultyContainsKeywords;
import seedu.student.model.student.SchoolResidenceContainsKeywords;
import seedu.student.model.student.VaccinationStatusContainsKeywords;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {

    private static String vaccinatedString = "vaccinated";
    private static String facultyString = "COM";
    private static String schoolResidenceString = "RVRC";

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {
        VaccinationStatusContainsKeywords vaccinationPredicate =
                new VaccinationStatusContainsKeywords(vaccinatedString);
        FacultyContainsKeywords facultyPredicate =
                new FacultyContainsKeywords(facultyString);
        SchoolResidenceContainsKeywords schoolResidencePredicate =
                new SchoolResidenceContainsKeywords(schoolResidenceString);

        FilterCommand findByVaccinationStatus = new FilterCommand(vaccinationPredicate, vaccinatedString);
        FilterCommand findByFaculty = new FilterCommand(facultyPredicate, facultyString);
        FilterCommand findBySchoolResidence = new FilterCommand(schoolResidencePredicate, schoolResidenceString);

        // same object -> returns true
        assertTrue(findByVaccinationStatus.equals(findByVaccinationStatus));

        // same values -> returns true
        FilterCommand findByVaccinationStatusCopy = new FilterCommand(vaccinationPredicate, vaccinatedString);
        assertTrue(findByVaccinationStatus.equals(findByVaccinationStatusCopy));

        // different types -> returns false
        assertFalse(findByVaccinationStatus.equals(1));

        // null -> returns false
        assertFalse(findByVaccinationStatus.equals(null));

        // different student -> returns false
        assertFalse(findByVaccinationStatus.equals(findByFaculty));

        // same object -> returns true
        assertTrue(findByFaculty.equals(findByFaculty));

        // same values -> returns true
        FilterCommand findByFacultyCopy = new FilterCommand(facultyPredicate, facultyString);
        assertTrue(findByFaculty.equals(findByFacultyCopy));

        // different types -> returns false
        assertFalse(findByFaculty.equals(1));

        // null -> returns false
        assertFalse(findByFaculty.equals(null));

        // different student -> returns false
        assertFalse(findByFaculty.equals(findBySchoolResidence));

        // same object -> returns true
        assertTrue(findBySchoolResidence.equals(findBySchoolResidence));

        // same values -> returns true
        FilterCommand findBySchoolResidenceCopy = new FilterCommand(schoolResidencePredicate, schoolResidenceString);
        assertTrue(findBySchoolResidence.equals(findBySchoolResidenceCopy));

        // different types -> returns false
        assertFalse(findBySchoolResidence.equals(1));

        // null -> returns false
        assertFalse(findBySchoolResidence.equals(null));

        // different student -> returns false
        assertFalse(findBySchoolResidence.equals(findByFaculty));

    }

    @Test
    public void execute_zeroKeywords_noStudentFoundFilterByVaccinationStatus() {

        String expectedMessage = String.format(MESSAGE_NO_STUDENTS_ARE_LISTED, 0);

        //Filter by Vaccination status

        VaccinationStatusContainsKeywords vaccinationPredicate = new VaccinationStatusContainsKeywords("");
        FilterCommand filterStatusCommand = new FilterCommand(vaccinationPredicate, vaccinatedString);
        expectedModel.updateFilteredStudentList(vaccinationPredicate);
        assertCommandSuccess(filterStatusCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

        //Filter by Faculty

        FacultyContainsKeywords facultyPredicate = new FacultyContainsKeywords("FASS");
        FilterCommand filterFacultyCommand = new FilterCommand(facultyPredicate, facultyString);
        expectedModel.updateFilteredStudentList(facultyPredicate);
        assertCommandSuccess(filterFacultyCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

        //Filter by School Residence

        SchoolResidenceContainsKeywords schoolResidencePredicate = new
                SchoolResidenceContainsKeywords("DOES_NOT_LIVE_ON_CAMPUS");
        FilterCommand filterResidenceCommand = new FilterCommand(schoolResidencePredicate, schoolResidenceString);
        expectedModel.updateFilteredStudentList(schoolResidencePredicate);
        assertCommandSuccess(filterResidenceCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

    }

    @Test
    public void execute_oneKeyword_multipleStudentFound() {

        String expectedMessage = String.format(MESSAGE_STUDENTS_ARE_LISTED, 0);

        //Filter by Vaccination status

        VaccinationStatusContainsKeywords vaccinationPredicate = new VaccinationStatusContainsKeywords("unvaccinated");
        FilterCommand filterStatusCommand = new FilterCommand(vaccinationPredicate, vaccinatedString);
        expectedModel.updateFilteredStudentList(vaccinationPredicate);
        assertCommandSuccess(filterStatusCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, GEORGE), model.getFilteredStudentList());

        //Filter by Faculty

        FacultyContainsKeywords facultyPredicate = new FacultyContainsKeywords("BIZ");
        FilterCommand filterFacultyCommand = new FilterCommand(facultyPredicate, facultyString);
        expectedModel.updateFilteredStudentList(facultyPredicate);
        assertCommandSuccess(filterFacultyCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());

        //Filter by School Residence

        SchoolResidenceContainsKeywords schoolResidencePredicate = new SchoolResidenceContainsKeywords("RVRC");
        FilterCommand command = new FilterCommand(schoolResidencePredicate, schoolResidenceString);
        expectedModel.updateFilteredStudentList(schoolResidencePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredStudentList());
    }
}
