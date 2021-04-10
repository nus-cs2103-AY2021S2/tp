package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the doctor records to the user.
 */
public class ListDoctorCommand extends Command {

    public static final String COMMAND_WORD = "list-doctor";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(Messages.MESSAGE_LIST_DOCTOR_SUCCESS);
    }
}
