package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Adds a session to the address book.
 */
public class AddSessionCommand extends Command {
    public static final String COMMAND_WORD = "add_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tuition session to the manager. "
            + "Parameters: "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + PREFIX_SUBJECT + "SUBJECT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DAY + "Wednesday "
            + PREFIX_TIMESLOT + "12:30 to 14:30 "
            + PREFIX_SUBJECT + "Piano "
            + PREFIX_TAG + "music";
    public static final String MESSAGE_SUCCESS = "New session added: ";

    private final Session toAdd;

    /**
     * Creates an AddSessionCommand to add the specified {@code Person}
     */
    public AddSessionCommand(Session session) {
        requireNonNull(session);
        toAdd = session;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.addSession(toAdd);
        model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
        return new CommandResult(MESSAGE_SUCCESS + String.format(Messages.MESSAGE_SESSION_PLACEHOLDER,
                toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSessionCommand // instanceof handles nulls
                && toAdd.equals(((AddSessionCommand) other).toAdd));
    }
}
