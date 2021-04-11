package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;

public class DeleteSessionCommand extends Command {

    public static final String COMMAND_WORD = "delete_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the session identified by the session ID shown in the displayed session list.\n"
            + "Parameters: C/ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " c/1";

    public static final String MESSAGE_DELETE_SESSION_SUCCESS = "Deleted Session: ";

    private final SessionId targetClassId;

    public DeleteSessionCommand(SessionId targetClassId) {
        this.targetClassId = targetClassId;
    }

    public SessionId getTargetClassId() {
        return targetClassId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Session> lastShownList = model.getUnfilteredSessionList();

        Optional<Session> sessionToDelete = lastShownList.stream()
                .filter(x-> x.getClassId().equals(targetClassId)).findAny();

        if (!sessionToDelete.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        if (sessionToDelete.get().hasTutor() || sessionToDelete.get().hasStudent()) {
            throw new CommandException(Messages.MESSAGE_CANNOT_DELETE_SESSION);
        }

        model.deleteSession(sessionToDelete.get());
        model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
        return new CommandResult(MESSAGE_DELETE_SESSION_SUCCESS + String.format(Messages
                .MESSAGE_SESSION_PLACEHOLDER, sessionToDelete.get()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSessionCommand // instanceof handles nulls
                && targetClassId.equals(((DeleteSessionCommand) other).getTargetClassId())); // state check
    }
}
