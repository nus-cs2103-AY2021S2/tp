package seedu.student.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;

/**
 * Calculates the number of upcoming and previous appointments within 6 days from the current day and
 * including the day itself.
 */
public class StatsApptCommand extends Command {

    public static final String COMMAND_WORD = "statsAppt";

    public static final String MESSAGE_STATS_SUCCESS = "Number of upcoming appointments in the next week: %d" + "\n"
            + "Number of appointments in the past week: %d";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Appointment> listAppointments = model.getAppointmentList();
        LocalDate currDate = LocalDate.now();
        LocalDate pastWeek = currDate.minusWeeks(1);
        LocalDate nextWeek = currDate.plusWeeks(1);

        LocalTime currTime = LocalTime.now();

        int numPastWeek = 0;
        int numNextWeek = 0;

        for (Appointment appt : listAppointments) {
            LocalDate apptDate = appt.getDate();
            if (apptDate.isAfter(pastWeek) && apptDate.isBefore(currDate)) {
                numPastWeek++;
            }
            if (apptDate.isAfter(currDate) && apptDate.isBefore(nextWeek)) {
                numNextWeek++;
            }
            if (apptDate.equals(currDate)) {
                LocalTime apptEndTime = appt.getEndTime();
                LocalTime apptStartTime = appt.getStartTime();
                if (apptEndTime.isBefore(currTime)) { // Appt is today and has ended
                    numPastWeek++;
                }
                if (apptStartTime.isAfter(currTime)) { // Appt is today but has not started
                    numNextWeek++;
                }
            }
        }

        return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, numNextWeek, numPastWeek));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsApptCommand); // instanceof handles nulls
    }

}
