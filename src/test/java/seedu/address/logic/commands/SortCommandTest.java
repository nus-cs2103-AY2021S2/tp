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
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PersonNameComparator();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(new SortCommand("/a"), model,
                SortCommand.MESSAGE_SUCCESS_ASCENDING, expectedModel);
    }

    @Test
    public void execute_nameSortedInDescendingOrder() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Comparator<Person> comparator = new SortCommand.PersonNameComparator();
        comparator.reversed();
        expectedModel.updateSortedPersonList(comparator);

        assertCommandSuccess(new SortCommand("/d"), model,
                SortCommand.MESSAGE_SUCCESS_DESCENDING, expectedModel);
    }
}
