package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.BALLET_RECITAL;
import static seedu.address.testutil.TypicalAppointments.PLAY_DATE;
import static seedu.address.testutil.TypicalAppointments.PSG_MEETING;
import static seedu.address.testutil.TypicalAppointments.PTM;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BENSON;
import static seedu.address.testutil.TypicalContacts.DANIEL;
import static seedu.address.testutil.TypicalContacts.ELLE;
import static seedu.address.testutil.TypicalContacts.FIONA;
import static seedu.address.testutil.TypicalContacts.GEORGE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.AddressBookSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.predicate.ApptNameContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.AppointmentBookBuilder;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookSettings_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookSettings(null));
    }

    @Test
    public void setAddressBook_validGAddressBook_setsAddressBook() {
        AddressBookSettings addressBookSettings = new AddressBookSettings();

        modelManager.setAddressBookSettings(addressBookSettings);
        assertEquals(addressBookSettings, modelManager.getAddressBookSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInAddressBook_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void sortContactList_sortFilteredListByName_returnsFilteredSortedList() {
        ArrayList<String> toFind = new ArrayList<>();
        toFind.add("Me");

        // build result to compare to
        AddressBook addressBook = new AddressBookBuilder().withContact(BENSON).withContact(DANIEL).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(addressBook, new AppointmentBook(), userPrefs);

        // add contacts to model manager
        modelManager = new ModelManager();

        modelManager.addContact(ALICE);
        modelManager.addContact(DANIEL);
        modelManager.addContact(FIONA);
        modelManager.addContact(BENSON);

        // filter and sort contacts
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(toFind));
        modelManager.sortContactList(OPTION_NAME);

        assertEquals(modelManager.getFilteredContactList(), modelManagerNew.getFilteredContactList());
    }

    @Test
    public void sortContactList_sortFilteredListByNameAndUnfilterList_returnsFullSortedList() {
        ArrayList<String> toFind = new ArrayList<>();
        toFind.add("Me");

        // build result to compare to
        AddressBook addressBook = new AddressBookBuilder().withContact(ALICE).withContact(BENSON)
                .withContact(DANIEL).withContact(FIONA).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(addressBook, new AppointmentBook(), userPrefs);

        // add contacts to model manager
        modelManager = new ModelManager();

        modelManager.addContact(ALICE);
        modelManager.addContact(DANIEL);
        modelManager.addContact(FIONA);
        modelManager.addContact(BENSON);

        // filter and sort contacts
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(toFind));
        modelManager.sortContactList(OPTION_NAME);

        // unfilter list
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        assertEquals(modelManager.getFilteredContactList(), modelManagerNew.getFilteredContactList());
    }

    @Test
    public void orderContacts_addContact_ordersContacts() {
        // build result to compare to
        AddressBook addressBook = new AddressBookBuilder().withContact(ALICE).withContact(BENSON)
                .withContact(ELLE).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(addressBook, new AppointmentBook(), userPrefs);

        // add appointments to model manager
        modelManager = new ModelManager();

        modelManager.sortContactList(OPTION_NAME);

        modelManager.addContact(ELLE);
        modelManager.addContact(ALICE);
        modelManager.addContact(BENSON);

        assertEquals(modelManager.getFilteredContactList(), modelManagerNew.getFilteredContactList());
    }

    @Test
    public void orderContacts_editContact_ordersContacts() {
        // build result to compare to
        AddressBook addressBook = new AddressBookBuilder().withContact(ALICE).withContact(ELLE)
                .withContact(GEORGE).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(addressBook, new AppointmentBook(), userPrefs);

        // add appointments to model manager
        modelManager = new ModelManager();

        modelManager.sortContactList(OPTION_NAME);

        modelManager.addContact(ELLE);
        modelManager.addContact(ALICE);
        modelManager.addContact(BENSON);
        modelManager.setContact(BENSON, GEORGE);

        assertEquals(modelManager.getFilteredContactList(), modelManagerNew.getFilteredContactList());
    }

    @Test
    public void orderAppointments_filterListAndUnfilterList_returnsFullSortedList() {
        ArrayList<String> toFind = new ArrayList<>();
        toFind.add("P");

        // build result to compare to
        AppointmentBook appointmentBook = new AppointmentBookBuilder().withAppointment(PTM)
                .withAppointment(PSG_MEETING).withAppointment(BALLET_RECITAL).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(getTypicalAddressBook(), appointmentBook, userPrefs);

        // add appointments to model manager
        modelManager = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), userPrefs);

        modelManager.addAppointment(PSG_MEETING);
        modelManager.addAppointment(PTM);
        modelManager.addAppointment(BALLET_RECITAL);

        // filter and sort contacts
        modelManager.updateFilteredAppointmentList(new ApptNameContainsKeywordsPredicate(toFind));
        modelManager.orderContacts();

        // unfilter list
        modelManager.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        assertEquals(modelManager.getFilteredAppointmentList(), modelManagerNew.getFilteredAppointmentList());
    }

    @Test
    public void orderAppointments_addAppointment_ordersAppointments() {
        // build result to compare to
        AppointmentBook appointmentBook = new AppointmentBookBuilder().withAppointment(PTM)
                .withAppointment(PSG_MEETING).withAppointment(BALLET_RECITAL).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(getTypicalAddressBook(), appointmentBook, userPrefs);

        // add appointments to model manager
        modelManager = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), userPrefs);

        modelManager.addAppointment(PSG_MEETING);
        modelManager.addAppointment(PTM);
        modelManager.addAppointment(BALLET_RECITAL);

        modelManager.orderAppointments();

        assertEquals(modelManager.getFilteredAppointmentList(), modelManagerNew.getFilteredAppointmentList());
    }

    @Test
    public void orderAppointments_editAppointment_ordersAppointments() {
        // build result to compare to
        AppointmentBook appointmentBook = new AppointmentBookBuilder().withAppointment(PTM)
                .withAppointment(PLAY_DATE).withAppointment(PSG_MEETING).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManagerNew = new ModelManager(getTypicalAddressBook(), appointmentBook, userPrefs);

        // add appointments to model manager
        modelManager = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), userPrefs);

        modelManager.addAppointment(PSG_MEETING);
        modelManager.addAppointment(PTM);
        modelManager.addAppointment(BALLET_RECITAL);
        modelManager.setAppointment(BALLET_RECITAL, PLAY_DATE);

        modelManager.orderAppointments();

        assertEquals(modelManager.getFilteredAppointmentList(), modelManagerNew.getFilteredAppointmentList());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withContact(ALICE).withContact(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, new AppointmentBook(), userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, new AppointmentBook(), userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, new AppointmentBook(), userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, new AppointmentBook(), userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, new AppointmentBook(), differentUserPrefs)));
    }
}
