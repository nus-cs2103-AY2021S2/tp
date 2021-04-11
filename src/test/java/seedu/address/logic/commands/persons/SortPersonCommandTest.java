package seedu.address.logic.commands.persons;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.WENDY;
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

class SortPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    void executeEmail() throws CommandException {
        SortPersonCommand sortCommand = new SortPersonCommand(PersonSortOption.EMAIL, PersonSortDirection.ASC);
        CommandResult results = sortCommand.execute(model);
        ObservableList<Person> filteredList = model.getFilteredPersonList();
        assertEquals(ALICE,filteredList.get(0));
        assertEquals(new CommandResult("Sorted"), results);
    }
    @Test
    void executeName() throws CommandException {
        SortPersonCommand sortCommand = new SortPersonCommand(PersonSortOption.NAME, PersonSortDirection.DESC);
        CommandResult results = sortCommand.execute(model);
        ObservableList<Person> filteredList = model.getFilteredPersonList();
        assertEquals(WENDY,filteredList.get(0));
        assertEquals(new CommandResult("Sorted"), results);
    }

    @Test
    void executeAddress() throws CommandException {
        SortPersonCommand sortCommand = new SortPersonCommand(PersonSortOption.ADDRESS, PersonSortDirection.ASC);
        CommandResult results = sortCommand.execute(model);
        ObservableList<Person> filteredList = model.getFilteredPersonList();
        assertEquals(DANIEL,filteredList.get(0));
        assertEquals(new CommandResult("Sorted"), results);
    }

    @Test
    void executePhone() throws CommandException {
        SortPersonCommand sortCommand = new SortPersonCommand(PersonSortOption.PHONE, PersonSortDirection.ASC);
        CommandResult results = sortCommand.execute(model);
        ObservableList<Person> filteredList = model.getFilteredPersonList();
        assertEquals(DANIEL,filteredList.get(0));
        assertEquals(new CommandResult("Sorted"), results);
    }

}
