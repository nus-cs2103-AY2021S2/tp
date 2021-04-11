package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.Model;

public class SetTimetableCommand extends Command {
    public static final String COMMAND_WORD = "setTimetable";
    public static final String MESSAGE_USAGE = "setTimetable : Sets a starting date for the timetable."
            + " If no date is specified, it will set the starting date to be today's date."
            + " Parameters: (DATE must be in the format YYYY-mm-dd)."
            + "\n"
            + "Example Usage: "
            + "setTimetable 2021-03-21";
    public static final String MESSAGE_SUCCESS = "Timetable updated to start on %s!";
    private final LocalDate localDate;

    /**
     * The constructor of set time table command.
     */
    public SetTimetableCommand(LocalDate localDate) {
        requireNonNull(localDate);
        this.localDate = localDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setTimetableStartDate(localDate);
        String successMessage = String.format(MESSAGE_SUCCESS, DateTimeUtil.prettyPrintFormatLocalDate(localDate));
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTimetableCommand // instanceof handles nulls
                && localDate.equals(((SetTimetableCommand) other).localDate)); // state check
    }

}
