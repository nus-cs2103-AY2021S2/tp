package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.checkContactInvolvedInAppointment;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;

/**
 * Deletes a contact identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted Contacts(s): \n%1$s";
    public static final String MESSAGE_DELETE_CONTACT_APPOINTMENT_FAILURE =
            "Failed to delete the following Contact(s) as they are involved in appointments: \n%1$s";

    private final List<Index> targetIndexList;

    /**
     * Takes a list of {@code Index} and constructs a DeleteCommand
     * @param targetIndexList a list of {@code Index}
     */
    public DeleteCommand(List<Index> targetIndexList) {
        this.targetIndexList = targetIndexList;
        assert targetIndexList.size() > 0 : "No indexes for DeleteCommand";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();
        final StringBuilder outputString = new StringBuilder();

        List<Contact> contactToDeleteList = new ArrayList<>();
        List<Contact> contactCannotBeDeletedList = new ArrayList<>();
        ObservableList<Appointment> appointmentList = model.getFilteredAppointmentList();

        for (Index targetIndex : targetIndexList) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
            Contact contactToDelete = lastShownList.get(targetIndex.getZeroBased());

            if (checkContactInvolvedInAppointment(contactToDelete, appointmentList)) {
                contactCannotBeDeletedList.add(contactToDelete);
            } else {
                contactToDeleteList.add(contactToDelete);
            }
        }

        for (Contact contactToDelete : contactToDeleteList) {
            model.deleteContact(contactToDelete);
        }

        if (!contactToDeleteList.isEmpty()) {
            outputString.append(String.format(MESSAGE_DELETE_CONTACT_SUCCESS,
                    contactListToStringBuilder(contactToDeleteList)));
        }

        if (!contactCannotBeDeletedList.isEmpty()) {
            if (outputString.length() > 0) {
                outputString.append("\n");
            }
            outputString.append(String.format(MESSAGE_DELETE_CONTACT_APPOINTMENT_FAILURE,
                    contactListToStringBuilder(contactCannotBeDeletedList)));
            throw new CommandException(outputString.toString());
        }

        return new CommandResult(outputString.toString());
    }

    /**
     * Appends Contacts in a contact list to a StringBuilder
     * @param contactList list of contacts to append
     * @return StringBuilder of all the appended contacts in the list
     */
    private StringBuilder contactListToStringBuilder(List<Contact> contactList) {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Contact contact : contactList) {
            if (targetIndexList.size() > 1) {
                resultStringBuilder.append("\n");
            }
            resultStringBuilder.append(contact);
        }
        return resultStringBuilder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexList.equals(((DeleteCommand) other).targetIndexList)); // state check
    }
}
