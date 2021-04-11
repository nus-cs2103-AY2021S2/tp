package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalContacts.getTypicalContacts;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysContact;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ContactCardHandle;
import guitests.guihandles.ContactListPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;

/**
 * @@author {se-edu}-reused
 * Reused with modification from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Contains tests for the {@code ContactListPanel}.
 */
public class ContactListPanelTest extends GuiUnitTest {
    private static final ObservableList<Contact> TYPICAL_CONTACTS =
            FXCollections.observableList(getTypicalContacts());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();
    private ContactListPanelHandle contactListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_CONTACTS);

        for (int i = 0; i < TYPICAL_CONTACTS.size(); i++) {
            contactListPanelHandle.navigateToCard(TYPICAL_CONTACTS.get(i));
            Contact expectedContact = TYPICAL_CONTACTS.get(i);
            ContactCardHandle actualCard = contactListPanelHandle.getContactCardHandle(i);

            assertCardDisplaysContact(expectedContact, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    /**
     * Verifies that creating and deleting large number of contacts in {@code ContactListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Contact> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of contact cards exceeded time limit");
    }

    /**
     * Returns a list of contacts containing {@code contactCount} contacts that is used to populate the
     * {@code ContactListPanel}.
     */
    private ObservableList<Contact> createBackingList(int contactCount) {
        ObservableList<Contact> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < contactCount; i++) {
            Name name = new Name(i + "a");
            Phone phone = new Phone("000");
            Email email = new Email("a@aa");
            Address address = new Address("a");
            Contact contact = new Contact(name, phone, email, address, Collections.emptySet());
            backingList.add(contact);
        }
        return backingList;
    }

    /**
     * Initializes {@code contactListPanelHandle} with a {@code ContactListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code ContactListPanel}.
     */
    private void initUi(ObservableList<Contact> backingList) {
        ContactListPanel contactListPanel =
                new ContactListPanel(backingList);
        uiPartExtension.setUiPart(contactListPanel);

        contactListPanelHandle = new ContactListPanelHandle(contactListPanel.getRoot());
    }
}
