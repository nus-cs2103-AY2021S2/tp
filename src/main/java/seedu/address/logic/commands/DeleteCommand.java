package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

/**
 * Deletes a patient identified using it's displayed index from DocBob.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Patient: %1$s";

    public static final String MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX = "Goodbye, %s!";

    public static final String MESSAGE_NO_PATIENTS_LEFT = "Doc, you have no more patients left!\n"
                                                        + "Start adding more patients with the 'add' command!";


    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(patientToDelete);
        model.selectPatient(null);
        String displayMessage = MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX;
        if (lastShownList.size() - 1 < 0) {
            displayMessage = MESSAGE_NO_PATIENTS_LEFT;
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, patientToDelete),
        false, false, null, null, null, String.format(displayMessage, patientToDelete.getName().fullName), false);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
