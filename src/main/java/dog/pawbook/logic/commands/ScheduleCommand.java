package dog.pawbook.logic.commands;

import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.ProgramOccursOnDatePredicate;

public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all programs on for the day or a given date."
            + "\n"
            + "Parameters: [DATE]...\n"
            + "Example: " + COMMAND_WORD + " 01-04-2021";

    public static final String MESSAGE_SUCCESS = "Here's your schedule!";

    private final LocalDate date;

    public ScheduleCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEntityList(IS_PROGRAM_PREDICATE.and(new ProgramOccursOnDatePredicate(date)));

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
