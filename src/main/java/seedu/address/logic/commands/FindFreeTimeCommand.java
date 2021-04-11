package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_PASSED;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Date;

public class FindFreeTimeCommand extends Command {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    public static final String COMMAND_WORD = "free_time";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all free time slots on the given date\n"
            + "Example: " + COMMAND_WORD + " 2022-01-07";

    public static final String MESSAGE_FIND_FREE_TIME_SUCCESS = "Found free time slots on %s: \n";
    public static final String MESSAGE_NO_FREE_TIME = "There is no free time in the day!\n";

    private final Date date;

    public FindFreeTimeCommand(Date date) {
        this.date = date;
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(DATE_FORMATTER);
        Date now = new Date(currentDateStr);

        ArrayList<String> freeTimeSlots = model.getFreeTimeSlots(date);
        if (date.compareTo(now) < 0) {
            throw new CommandException(MESSAGE_DATE_PASSED);
        } else if (freeTimeSlots.size() == 0) {
            return new CommandResult(MESSAGE_NO_FREE_TIME);
        } else {
            String timeSlots = "";
            for (int i = 0; i < freeTimeSlots.size(); i++) {
                timeSlots = timeSlots + freeTimeSlots.get(i) + "\n";
            }
            return new CommandResult(String.format(MESSAGE_FIND_FREE_TIME_SUCCESS + timeSlots, date.toString()));
        }
    }
}
