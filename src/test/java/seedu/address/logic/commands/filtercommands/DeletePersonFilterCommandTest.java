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
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeletePersonFilterCommand.
 */
public class DeletePersonFilterCommandTest {
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
    public void execute_afterAdd_success() {
        Person alice = TypicalPersons.ALICE;
        Person benson = TypicalPersons.BENSON;

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(alice.getName().fullName));
        nameFilters.add(new NameFilter(benson.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        // Add filters as precondition
        model.addPersonFilter(personFilter);
        expectedModel.addPersonFilter(personFilter);

        // PersonFilter with Benson
        nameFilters.remove(new NameFilter(alice.getName().fullName));
        personFilter = new PersonFilter(nameFilters);
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);
        expectedModel.removePersonFilter(personFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);

        // PersonFilter with Alice
        nameFilters.remove(new NameFilter(benson.getName().fullName));
        nameFilters.add(new NameFilter(alice.getName().fullName));
        personFilter = new PersonFilter(nameFilters);
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

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter(alice.getName().fullName));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);

        expectedModel.removePersonFilter(personFilter);
        String expectedMessage = String.format(DeletePersonFilterCommand.MESSAGE_SUCCESS, personFilter);
        assertCommandSuccess(deletePersonFilterCommand, model, expectedMessage, expectedModel);
    }
}
