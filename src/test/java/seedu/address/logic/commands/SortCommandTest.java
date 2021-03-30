package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nameSortedInAscendingOrder() {
        SortCommand sortCommand = new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_ASCENDING);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PersonNameComparator();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS_NAME_ASCENDING, expectedModel);
    }

    @Test
    public void execute_nameSortedInDescendingOrder() {
        SortCommand sortCommand = new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_DESCENDING);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PersonNameComparator();
        comparator = comparator.reversed();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS_NAME_DESCENDING, expectedModel);
    }

    @Test
    public void execute_policySortedInAscendingOrder() {
        SortCommand sortCommand =
                new SortCommand(SortCommand.SORT_BY_INSURANCE_POLICY, SortCommand.DIRECTION_ASCENDING);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PolicyComparator();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model,
                SortCommand.MESSAGE_SUCCESS_INSURANCE_POLICY_ASCENDING, expectedModel);
    }

    @Test
    public void execute_policySortedInDescendingOrder() {
        SortCommand sortCommand =
                new SortCommand(SortCommand.SORT_BY_INSURANCE_POLICY, SortCommand.DIRECTION_DESCENDING);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PolicyComparator();
        comparator = comparator.reversed();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(sortCommand, model,
                SortCommand.MESSAGE_SUCCESS_INSURANCE_POLICY_DESCENDING, expectedModel);
    }
}
