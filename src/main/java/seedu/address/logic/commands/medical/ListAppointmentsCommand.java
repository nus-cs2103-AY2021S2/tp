package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiManager;

public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "listappt";
    private static final Logger logger = LogsCenter.getLogger(ListAppointmentsCommand.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all upcoming appointments \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] \n" + Appointment.MESSAGE_CONSTRAINTS_DATE_FORMAT
            + "\n Example: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "24051800";

    public static final String MESSAGE_SUCCESS = "Here are your upcoming appointments: \n%s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String allAppointments = "";
        List<Appointment> allappt = new ArrayList<>();
        for (Person p: lastShownList) {
            List<Appointment> allappts = p.getAppointments();
            for (Appointment appt : allappts) {
                Appointment updatedAppt = appt.setPerson(p);
                allappt.add(updatedAppt);
                //allAppointments += appt.getDate().format(DATE_FORMAT_DISPLAY) + " " + p.getName() + "\n";;
            }
        }
        //Collections.sort(new ArrayList<Integer>());
        for (Appointment appt : allappt) {
            allAppointments += appt + "\n";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, allAppointments));
    }

}
