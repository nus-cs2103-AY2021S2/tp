package seedu.address.logic.commands.persons;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;

class UnsortPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    void execute() throws CommandException {
        UnsortPersonCommand sortCommand = new UnsortPersonCommand();
        CommandResult results = sortCommand.execute(model);
        ObservableList<Person> filteredList = model.getFilteredPersonList();
        assertEquals(ALICE,filteredList.get(0));
        assertEquals(new CommandResult("Unsorted the contacts list"), results);
    }
}
