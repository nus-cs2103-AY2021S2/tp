package seedu.address.logic.commands.filtercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
import seedu.address.model.filter.PersonFilter;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeletePersonFilterCommand.
 */
public class DeletePersonFilterCommandTest {
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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook());

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook());

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
        Person alice = TypicalPersons.ALICE;
        Person benson = TypicalPersons.BENSON;

        nameFilters.add(new NameFilter(alice.getName().fullName));
        nameFilters.add(new NameFilter(benson.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        // Add filters as precondition
        model.addPersonFilter(personFilter);
        expectedModel.addPersonFilter(personFilter);

        // PersonFilter with Benson
        nameFilters.remove(new NameFilter(alice.getName().fullName));
        personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);
        expectedModel.removePersonFilter(personFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);

        // PersonFilter with Alice
        nameFilters.remove(new NameFilter(benson.getName().fullName));
        nameFilters.add(new NameFilter(alice.getName().fullName));
        personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);
        deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);
        expectedModel.removePersonFilter(personFilter);
        expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        PersonFilter personFilter = new PersonFilter();
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFilters_success() {
        Person alice = TypicalPersons.ALICE;

        nameFilters.add(new NameFilter(alice.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);

        expectedModel.removePersonFilter(personFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }
}
