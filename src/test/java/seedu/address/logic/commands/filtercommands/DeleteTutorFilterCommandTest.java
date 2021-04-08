package seedu.address.logic.commands.filtercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BudgetBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.TutorFilter;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.TypicalTutors;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeletePersonFilterCommand.
 */
public class DeleteTutorFilterCommandTest {
    private Model model;
    private Model expectedModel;

    private Set<Predicate<Name>> nameFilters;
    private Set<Predicate<Gender>> genderFilters;
    private Set<Predicate<Phone>> phoneFilters;
    private Set<Predicate<Email>> emailFilters;
    private Set<Predicate<Address>> addressFilters;

    private Set<Predicate<SubjectName>> subjectNameFilters;
    private Set<Predicate<SubjectLevel>> subjectLevelFilters;
    private Set<Predicate<SubjectRate>> subjectRateFilters;
    private Set<Predicate<SubjectExperience>> subjectExperienceFilters;
    private Set<Predicate<SubjectQualification>> subjectQualificationFilters;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());

        expectedModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook(),
                getTypicalScheduleTracker(), getTypicalReminderTracker());

        this.nameFilters = new LinkedHashSet<>();
        this.genderFilters = new LinkedHashSet<>();
        this.phoneFilters = new LinkedHashSet<>();
        this.emailFilters = new LinkedHashSet<>();
        this.addressFilters = new LinkedHashSet<>();

        this.subjectNameFilters = new LinkedHashSet<>();
        this.subjectLevelFilters = new LinkedHashSet<>();
        this.subjectRateFilters = new LinkedHashSet<>();
        this.subjectExperienceFilters = new LinkedHashSet<>();
        this.subjectQualificationFilters = new LinkedHashSet<>();
    }

    @Test
    public void execute_afterAdd_success() {
        Tutor alice = TypicalTutors.ALICE;
        Tutor benson = TypicalTutors.BENSON;

        nameFilters.add(new NameFilter(alice.getName().fullName));
        nameFilters.add(new NameFilter(benson.getName().fullName));
        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        // Add filters as precondition
        model.addTutorFilter(tutorFilter);
        expectedModel.addTutorFilter(tutorFilter);

        // PersonFilter with Benson
        nameFilters.remove(new NameFilter(alice.getName().fullName));
        tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);
        expectedModel.removeTutorFilter(tutorFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);

        // PersonFilter with Alice
        nameFilters.remove(new NameFilter(benson.getName().fullName));
        nameFilters.add(new NameFilter(alice.getName().fullName));
        tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);
        deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);
        expectedModel.removeTutorFilter(tutorFilter);
        expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        TutorFilter tutorFilter = new TutorFilter();
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, tutorFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFiltersExist_failure() {
        Tutor alice = TypicalTutors.ALICE;

        nameFilters.add(new NameFilter(alice.getName().fullName));
        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);
        assertCommandFailure(deletePersonFilterCommand, model, DeletePersonFilterCommand.MESSAGE_NOT_FOUND);
    }
}
