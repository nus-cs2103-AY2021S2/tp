package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysContact;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ContactCardHandle;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

/**
 * @@author {se-edu}-reused
 * Reused from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Contains tests for the {@code ContactCard}.
 */
public class ContactCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Contact contactWithNoTags = new ContactBuilder().withTags(new String[0]).build();
        ContactCard contactCard = new ContactCard(contactWithNoTags, 1);
        uiPartExtension.setUiPart(contactCard);
        assertCardDisplay(contactCard, contactWithNoTags, 1);

        // with tags
        Contact contactWithTags = new ContactBuilder().build();
        contactCard = new ContactCard(contactWithTags, 2);
        uiPartExtension.setUiPart(contactCard);
        assertCardDisplay(contactCard, contactWithTags, 2);
    }

    @Test
    public void equals() {
        Contact contact = new ContactBuilder().build();
        ContactCard contactCard = new ContactCard(contact, 0);

        // same contact, same index -> returns true
        ContactCard copy = new ContactCard(contact, 0);
        assertTrue(contactCard.equals(copy));

        // same object -> returns true
        assertTrue(contactCard.equals(contactCard));

        // null -> returns false
        assertFalse(contactCard.equals(null));

        // different types -> returns false
        assertFalse(contactCard.equals(0));

        // different contact, same index -> returns false
        Contact differentContact = new ContactBuilder().withName("differentName").build();
        assertFalse(contactCard.equals(new ContactCard(differentContact, 0)));

        // same contact, different index -> returns false
        assertFalse(contactCard.equals(new ContactCard(contact, 1)));
    }

    /**
     * Asserts that {@code contactCard} displays the details of {@code expectedContact} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ContactCard contactCard, Contact expectedContact, int expectedId) {
        guiRobot.pauseForHuman();

        ContactCardHandle contactCardHandle = new ContactCardHandle(contactCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", contactCardHandle.getId());

        // verify contact details are displayed correctly
        assertCardDisplaysContact(expectedContact, contactCardHandle);
    }
}
