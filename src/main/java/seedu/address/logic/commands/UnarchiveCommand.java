package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

/**
 * Unarchives a Patient in DocBob.
 */
public class UnarchiveCommand extends Command {
    public static final String COMMAND_WORD = "unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the patient identified by the index number used in the archived patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNARCHIVE_PERSON_SUCCESS = "Hey Doc, %s is now unarchived!\n"
            + "The patient is now back in your main list.";

    public static final String MESSAGE_PERSON_NOT_ARCHIVED = "This patient is not in your archives.\n";


    private final Index targetIndex;

    public UnarchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToUnarchive = lastShownList.get(targetIndex.getZeroBased());

        if (!patientToUnarchive.isArchived()) {
            throw new CommandException(MESSAGE_PERSON_NOT_ARCHIVED);
        }

        model.unarchivePerson(patientToUnarchive);
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_PERSON_SUCCESS,
                                                patientToUnarchive.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnarchiveCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveCommand) other).targetIndex)); // state check
    }
}
