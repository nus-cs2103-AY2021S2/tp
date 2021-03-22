package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.StudentSessionPredicate;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionIdPredicate;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ViewSessionCommand extends Command{

    public static final String COMMAND_WORD = "view_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View individual session with the given session ID.\n"
            + "Parameters: SESSION ID\n"
            + "Example: " + COMMAND_WORD + " c/ID";

    public static final String MESSAGE_SUCCESS = "Displayed the relevant session.\n"
                                                + "Left Panel shows the session information.\n"
                                                + "Right Panel shows all the students in the session.";

    private final SessionIdPredicate sessionPredicate;

    public ViewSessionCommand(SessionIdPredicate sessionPredicate) {
        this.sessionPredicate = sessionPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);
        model.updateFilteredSessionList(sessionPredicate);
        List<Session> lastShownList = model.getFilteredSessionList();

        Optional<Session> sessionToView = lastShownList.stream()
                .filter(x-> x.getClassId().equals(sessionPredicate.getSessionId())).findAny();
        if (sessionToView.isPresent()) {
            List<PersonId> studentList = sessionToView.get().getStudents();
            StudentSessionPredicate studentSessionPredicate = new StudentSessionPredicate(studentList);
            model.updateFilteredPersonList(studentSessionPredicate);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewSessionCommand // instanceof handles nulls
                && sessionPredicate.equals(((ViewSessionCommand) other).sessionPredicate)); // state check
    }
}
