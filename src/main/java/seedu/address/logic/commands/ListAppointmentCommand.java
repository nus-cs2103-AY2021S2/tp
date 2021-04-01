package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.Model;

public class ListAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "listAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists appointments in the appointment book.\n"
            + "Parameters: [" + PREFIX_OPTION + "OPTION]\n";
    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    public ListAppointmentCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
