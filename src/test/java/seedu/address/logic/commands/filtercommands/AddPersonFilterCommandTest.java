package seedu.address.logic.commands.filtercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddPersonFilterCommand.
 */
public class AddPersonFilterCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook());

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalAppointmentBook(), new BudgetBook(), getTypicalGradeBook());
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        Person alice = TypicalPersons.ALICE;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(alice.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(personFilter);

        expectedModel.addPersonFilter(personFilter);
        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, personFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Person alice = TypicalPersons.ALICE;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(alice.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(personFilter);

        expectedModel.addPersonFilter(personFilter);
        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, personFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        PersonFilter personFilter = new PersonFilter();

        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(personFilter);

        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, personFilter);

        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonFilter_success() {
        Person alice = TypicalPersons.ALICE;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(alice.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(personFilter);

        expectedModel.addPersonFilter(personFilter);
        String expectedMessage = String.format(AddPersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(addPersonFilterCommand, model, expectedMessage, expectedModel);

        expectedMessage = AddPersonFilterCommand.MESSAGE_DUPLICATE;
        assertCommandFailure(addPersonFilterCommand, model, expectedMessage);
    }
}
