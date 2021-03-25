package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.address.model.Model;

/**
 * Lists all properties and appointments in the property book and appointment book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list all";

    public static final String MESSAGE_SUCCESS = "Listed all properties and appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
