package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person(s): %1$s";
    public static final String MESSAGE_DELETE_PERSON_APPOINTMENT_FAILURE =
            "Failed to delete the following Person(s) as they are involved in appointments: %1$s";

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
        List<Person> lastShownList = model.getFilteredPersonList();
        final StringBuilder outputString = new StringBuilder();

        List<Person> personToDeleteList = new ArrayList<>();
        List<Person> personCannotBeDeletedList = new ArrayList<>();
        ObservableList<Appointment> appointmentList = model.getFilteredAppointmentList();

        for (Index targetIndex : targetIndexList) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

            if (checkPersonInvolvedInAppointment(personToDelete, appointmentList)) {
                personCannotBeDeletedList.add(personToDelete);
            } else {
                personToDeleteList.add(personToDelete);
            }
        }

        for (Person personToDelete : personToDeleteList) {
            model.deletePerson(personToDelete);
        }

        if (!personToDeleteList.isEmpty()) {
            outputString.append(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                    personListToStringBuilder(personToDeleteList)));
        }

        if (!personCannotBeDeletedList.isEmpty()) {
            if (outputString.length() > 0) {
                outputString.append("\n");
            }
            outputString.append(String.format(MESSAGE_DELETE_PERSON_APPOINTMENT_FAILURE,
                    personListToStringBuilder(personCannotBeDeletedList)));
            throw new CommandException(outputString.toString());
        }

        return new CommandResult(outputString.toString());
    }

    /**
     * Checks if the specified person is involved in an existing appointment
     * @param person person to be checked
     * @return boolean value of whether or not person is involved in an appointment
     */
    private boolean checkPersonInvolvedInAppointment(Person person, ObservableList<Appointment> appointmentList) {
        boolean isInvolved = false;

        for (Appointment appointment : appointmentList) {
            Set<Person> personSet = appointment.getContacts();
            if (personSet.contains(person)) {
                isInvolved = true;
                break;
            }
        }

        return isInvolved;
    }

    /**
     * Appends Persons in a person list to a StringBuilder
     * @param personList list of persons to append
     * @return StringBuilder of all the appended persons in the list
     */
    private StringBuilder personListToStringBuilder(List<Person> personList) {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Person person : personList) {
            if (targetIndexList.size() > 1) {
                resultStringBuilder.append("\n");
            }
            resultStringBuilder.append(person);
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
