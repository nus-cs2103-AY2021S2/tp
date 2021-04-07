package seedu.address.logic.commands;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;



public class CommandUtil {
    /**
     * Checks if the specified contact is involved in an existing appointment
     * @param contact contact to be checked
     * @return boolean value of whether or not contact is involved in an appointment
     */
    public static boolean checkContactInvolvedInAppointment(Contact contact,
                                                             ObservableList<Appointment> appointmentList) {
        boolean isInvolved = false;

        for (Appointment appointment : appointmentList) {
            Set<Contact> contactSet = appointment.getContacts();
            if (contactSet.contains(contact)) {
                isInvolved = true;
                break;
            }
        }

        return isInvolved;
    }

    /**
     * Appends Contacts in a contact list to a StringBuilder
     * @param contactList list of contacts to append
     * @return StringBuilder of all the appended contacts in the list
     */
    public static StringBuilder contactListToStringBuilder(List<Contact> contactList) {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Contact contact : contactList) {
            resultStringBuilder.append("\n");
            resultStringBuilder.append(contact);
        }
        return resultStringBuilder;
    }
}
