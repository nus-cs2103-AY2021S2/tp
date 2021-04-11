package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class FavouriteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());

    @Test
    public void execute_validIndexFavourite_success() {
        Contact contactToFavourite = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_CONTACT, true);

        ContactBuilder contactInList = new ContactBuilder(contactToFavourite);
        Contact favouritedContact = contactInList.withFavourite("true").build();

        String expectedMessage = String.format(FavouriteCommand.MESSAGE_FAVOURITE_CONTACT_SUCCESS, contactToFavourite);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(), new UserPrefs());
        expectedModel.setContact(contactToFavourite, favouritedContact);

        assertCommandSuccess(favouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfavourite_success() {
        Contact contactToUnfavourite = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_CONTACT, false);

        ContactBuilder contactInList = new ContactBuilder(contactToUnfavourite);
        Contact unfavouritedContact = contactInList.withFavourite("false").build();

        String expectedMessage =
                String.format(FavouriteCommand.MESSAGE_UNFAVOURITE_CONTACT_SUCCESS, contactToUnfavourite);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(), new UserPrefs());
        expectedModel.setContact(contactToUnfavourite, unfavouritedContact);

        assertCommandSuccess(favouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFavourite_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(INDEX_SECOND_CONTACT.getZeroBased() < model.getAddressBook().getContactList().size());

        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_SECOND_CONTACT, true);

        assertCommandFailure(favouriteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }
}
