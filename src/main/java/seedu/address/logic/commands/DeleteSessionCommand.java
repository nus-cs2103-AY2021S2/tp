package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;

import static java.util.Objects.requireNonNull;

public class DeleteSessionCommand extends Command {

    public static final String COMMAND_WORD = "delete_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the session identified by the index number used in the student's list of sessions.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " John Doe" + " 1";

    public static final String MESSAGE_DELETE_SESSION_SUCCESS = "Deleted Session: %1$s";

    private final Name studentName;

    private final Index targetIndex;

    public DeleteSessionCommand(Name studentName, Index targetIndex) {
        this.studentName = studentName;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //use Jonah's findStudent command, then findSession command to add session.
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSessionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSessionCommand) other).targetIndex)); // state check
    }
}
