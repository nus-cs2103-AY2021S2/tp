package seedu.address.logic.commands.medical;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PersonBuilder;

public class ListAppointmentsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Patient newAlice = new PersonBuilder(ALICE).build();
        Patient newBenson = new PersonBuilder(BENSON).build();
        Patient newCarl = new PersonBuilder(CARL).build();
        Patient newDaniel = new PersonBuilder(DANIEL).build();
        Patient newElle = new PersonBuilder(ELLE).build();
        Patient newFiona = new PersonBuilder(FIONA).build();
        Patient newGeorge = new PersonBuilder(GEORGE).build();
        Patient newHoon = new PersonBuilder(HOON).build();
        Patient newIda = new PersonBuilder(IDA).build();
        model.addPerson(newAlice);
        model.addPerson(newBenson);
        model.addPerson(newCarl);
        model.addPerson(newDaniel);
        model.addPerson(newElle);
        model.addPerson(newFiona);
        model.addPerson(newGeorge);
        model.addPerson(newHoon);
        model.addPerson(newIda);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_PATIENTS_WITH_APPT);
        assertCommandSuccess(new ListAppointmentsCommand(), model,
                                ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_PATIENTS_WITH_APPT);
        assertCommandSuccess(new ListAppointmentsCommand(), model,
                                ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void equals() {
        ListAppointmentsCommand listAppointmentsFirstCommand = new ListAppointmentsCommand();
        ListAppointmentsCommand listAppointmentsSecondCommand = new ListAppointmentsCommand();

        // same object -> returns true
        assertTrue(listAppointmentsFirstCommand.equals(listAppointmentsFirstCommand));

        // different types -> returns false
        assertFalse(listAppointmentsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listAppointmentsFirstCommand.equals(null));
    }
}
