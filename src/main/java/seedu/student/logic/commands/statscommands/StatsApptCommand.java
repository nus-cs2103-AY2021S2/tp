package seedu.student.logic.commands.statscommands;

import java.util.List;

import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;

public class StatsApptCommand extends Command {

    public static final String COMMAND_WORD = "statsAppt";

    public static final String MESSAGE_STATS_SUCCESS = "";
    public static final String MESSAGE_STATS_FAILURE = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> listAppointments = model.getAppointmentList();
        StringBuilder sb = new StringBuilder();
        for (Appointment a : listAppointments) {
            sb.append(a.getDate() + "\n"); // LocalDate type
        }
        return new CommandResult(sb.toString());
    }
}
