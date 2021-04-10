package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_PATIENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PersonBuilder;

class ArchiveListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Patient newAlice = new PersonBuilder(ALICE).build();
        Patient newBenson = new PersonBuilder(BENSON).build();
        Patient newFiona = new PersonBuilder(FIONA).build();
        model.addPerson(newAlice);
        model.addPerson(newBenson);
        model.addPerson(newFiona);
        model.archivePerson(newAlice);
        model.archivePerson(newBenson);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PATIENTS);
    }

    @Test
    public void execute_archiveListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ArchiveListCommand(), model, ArchiveListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_archiveListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ArchiveListCommand(), model, ArchiveListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
