package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
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
 * Contains tests for the {@code PersonListPanel}.
 */
public class ContactListPanelTest extends GuiUnitTest {
    private static final ObservableList<Contact> TYPICAL_CONTACTS =
            FXCollections.observableList(getTypicalPersons());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Contact> selectedPerson = new SimpleObjectProperty<>();
    private PersonListPanelHandle personListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_CONTACTS);

        for (int i = 0; i < TYPICAL_CONTACTS.size(); i++) {
            personListPanelHandle.navigateToCard(TYPICAL_CONTACTS.get(i));
            Contact expectedContact = TYPICAL_CONTACTS.get(i);
            PersonCardHandle actualCard = personListPanelHandle.getPersonCardHandle(i);

            assertCardDisplaysPerson(expectedContact, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    /**
     * Verifies that creating and deleting large number of persons in {@code PersonListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Contact> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of person cards exceeded time limit");
    }

    /**
     * Returns a list of persons containing {@code personCount} persons that is used to populate the
     * {@code PersonListPanel}.
     */
    private ObservableList<Contact> createBackingList(int personCount) {
        ObservableList<Contact> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < personCount; i++) {
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
     * Initializes {@code personListPanelHandle} with a {@code PersonListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code PersonListPanel}.
     */
    private void initUi(ObservableList<Contact> backingList) {
        PersonListPanel personListPanel =
                new PersonListPanel(backingList);
        uiPartExtension.setUiPart(personListPanel);

        personListPanelHandle = new PersonListPanelHandle(getChildNode(personListPanel.getRoot(),
                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
    }
}
