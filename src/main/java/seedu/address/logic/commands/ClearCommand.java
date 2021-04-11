package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.checkContactInvolvedInAppointment;
import static seedu.address.logic.commands.CommandUtil.contactListToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;


/**
 * Clears the address book if given no arguments or clears all Contacts with a particular tag.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CLEAR_TAG_SUCCESS = "Cleared all contacts with any tag: \n%1$s";
    public static final String MESSAGE_FAILURE_CONTACTS_IN_APPOINTMENTS = "Failed to clear as the following "
                                                                + "Contact(s) are involved in appointments";
    private Set<Tag> tagsToClear;

    public ClearCommand(Set<Tag> tagsToClear) {
        this.tagsToClear = tagsToClear;
    }

    public ClearCommand() {
        tagsToClear = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Contact> contactList = model.getAddressBook().getContactList();
        Iterator<Contact> contactIterator = contactList.iterator();
        ObservableList<Appointment> appointmentList = model.getAppointmentBook().getAppointmentList();

        if (tagsToClear.isEmpty()) {
            checkSafeToClear(contactList, appointmentList);
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            List<Contact> contactsToDelete = new ArrayList<>();
            while (contactIterator.hasNext()) {
                Contact contact = contactIterator.next();
                Set<Tag> tags = contact.getTags();
                if (!Collections.disjoint(tags, tagsToClear)) {
                    contactsToDelete.add(contact);
                }
            }

            checkSafeToClear(contactsToDelete, appointmentList);
            contactsToDelete.forEach(model::deleteContact);
            return new CommandResult(String.format(MESSAGE_CLEAR_TAG_SUCCESS, tagsToClear.toString()));
        }
    }

    private void checkSafeToClear(List<Contact> contactsToDelete,
                             ObservableList<Appointment> appointmentList) throws CommandException {
        assert !contactsToDelete.isEmpty();
        ArrayList<Contact> contactsInvolvedInAppts = new ArrayList<>();
        for (Contact contact : contactsToDelete) {
            if (checkContactInvolvedInAppointment(contact, appointmentList)) {
                contactsInvolvedInAppts.add(contact);
            }
        }
        if (!contactsInvolvedInAppts.isEmpty()) {
            StringBuilder exceptionMessage = new StringBuilder(MESSAGE_FAILURE_CONTACTS_IN_APPOINTMENTS);
            exceptionMessage.append(contactListToStringBuilder(contactsInvolvedInAppts));
            throw new CommandException(exceptionMessage.toString());
        }
    }
}
