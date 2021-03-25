package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Person;

/**
 * Lists all upcoming appointments to the user.
 */
public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "listappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists out all your upcoming appointments.\n"
            + "Parameters: None\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Hey Doc, here are your upcoming appointments! \n%s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Appointment> appointments = new ArrayList<>();
        for (Person p: lastShownList) {
            List<Appointment> appointmentList = p.getAppointments();
            for (Appointment appt : appointmentList) {
                appointments.add(appt.setPerson(p));
            }
        }
        Collections.sort(appointments);
        String allAppointments = "";
        for (Appointment appt : appointments) {
            allAppointments += appt + "\n";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, allAppointments));
    }

}
