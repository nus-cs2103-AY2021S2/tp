package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class DeleteSessionCommand extends Command{

    public static final String COMMAND_WORD = "delete_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the session identified by the session ID shown in the displayed session list.\n"
            + "Parameters: C/ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " c/1";

    public static final String MESSAGE_DELETE_SESSION_SUCCESS = "Deleted Session: %1$s";

    private final String targetClassId;

    public DeleteSessionCommand(String targetClassId) {
        this.targetClassId = targetClassId;
    }

    public String getTargetClassId() {
        return targetClassId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Session> lastShownList = model.getFilteredSessionList();

        Optional<Session> sessionToDelete = lastShownList.stream().filter(x-> x.getClassId().equals(targetClassId)).findAny();

        if(sessionToDelete.isPresent()){
            model.deleteSession(sessionToDelete.get());
            return new CommandResult(String.format(MESSAGE_DELETE_SESSION_SUCCESS, sessionToDelete));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSessionCommand // instanceof handles nulls
                && targetClassId.equals(((DeleteSessionCommand) other).getTargetClassId())); // state check
    }
}
