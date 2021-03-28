package seedu.student.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.logic.commands.statscommands.StatsCommandResidence.MESSAGE_STATS_FAILURE;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.StudentBook;
import seedu.student.model.UserPrefs;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.testutil.StudentBuilder;

public class StatsCommandResidenceTest {
    private static final String VALID_VACCINATED_STATUS_BOB = "vaccinated";
    private static final Student DOES_NOT_LIVE_ON_CAMPUS_BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withFaculty(VALID_FACULTY_BOB).withVacStatus(VALID_VACCINATED_STATUS_BOB)
            .withMedDetails(VALID_DETAILS_BOB).withMatric(VALID_MATRIC_BOB)
            .withSchoolRes("DOES_NOT_LIVE_ON_CAMPUS").build();
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandResidence firstCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        StatsCommandResidence secondCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertTrue(firstCommand.equals(secondCommand));

        StatsCommandResidence thirdCommand = new StatsCommandResidence(new SchoolResidence("CAPT"));
        StatsCommandResidence fourthCommand = new StatsCommandResidence(new SchoolResidence("KRH"));
        assertFalse(thirdCommand.equals(fourthCommand));
    }

    // test no avaliable data
    @Test
    public void execute_typicalStudents_noAvailableData() {
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("UTR"));
        assertCommandFailure(statsCommandResidence, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test success
    @Test
    public void execute_typicalStudents_success() {
        String expectedOutput = "Percentage RVRC vaccinated: 33.33%";
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertCommandSuccess(statsCommandResidence, expectedModel, expectedOutput, expectedModel);
    }

    // test not on campus no data
    @Test
    public void execute_typicalStudentsNotOnCampus_noAvailableData() {
        SchoolResidence notOnCampus = new SchoolResidence("DOES_NOT_LIVE_ON_CAMPUS");
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(notOnCampus);
        assertCommandFailure(statsCommandResidence, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test not on campus success
    @Test
    public void execute_typicalStudentsNotOnCampus_success() {

        StudentBook ab = getTypicalStudentBook();
        ab.addStudent(DOES_NOT_LIVE_ON_CAMPUS_BOB);

        Model newModel = new ModelManager(ab, new UserPrefs());
        String expectedOutput = "Percentage DOES NOT LIVE ON CAMPUS vaccinated: 100.00%";
        SchoolResidence notOnCampus = new SchoolResidence("DOES_NOT_LIVE_ON_CAMPUS");
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(notOnCampus);
        assertCommandSuccess(statsCommandResidence, newModel, expectedOutput, newModel);
    }


}
