package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;


/**
 * Lists all upcoming appointments with a patient.
 */
public class ViewPatientCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all information regarding the patient identified by the index number used in the "
            + "displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Hey Doc, here is the information regarding %s! \n";

    private final Index index;

    /**
     * @param index of the patient in the filtered patient list to view
     */
    public ViewPatientCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Patient patientToView = lastShownList.get(index.getZeroBased());
        model.selectPatient(patientToView);
        return new CommandResult(String.format(MESSAGE_SUCCESS, patientToView.getName()),
                            false, false, patientToView, null, null, null, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPatientCommand // instanceof handles nulls
                && index.equals(((ViewPatientCommand) other).index)); // state check
    }
}
