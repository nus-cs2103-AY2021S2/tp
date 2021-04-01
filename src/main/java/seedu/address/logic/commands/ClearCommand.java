package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;


/**
 * Clears the address book if given no arguments or clears all Contacts with a particular tag.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CLEAR_TAG_SUCCESS = "Cleared all contacts with any tag: %1$s";
    private Set<Tag> tagsToClear;

    public ClearCommand(Set<Tag> tagsToClear) {
        this.tagsToClear = tagsToClear;
    }

    public ClearCommand() {
        tagsToClear = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (tagsToClear.isEmpty()) {
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            ObservableList<Contact> contactList = model.getAddressBook().getContactList();
            Iterator<Contact> contactIterator = contactList.iterator();
            List<Contact> contactsToDelete = new ArrayList<>();
            while (contactIterator.hasNext()) {
                Contact contact = contactIterator.next();
                Set<Tag> tags = contact.getTags();
                if (!Collections.disjoint(tags, tagsToClear)) {
                    contactsToDelete.add(contact);
                }
            }
            contactsToDelete.forEach(model::deleteContact);
            return new CommandResult(String.format(MESSAGE_CLEAR_TAG_SUCCESS, tagsToClear.toString()));
        }
    }
}
