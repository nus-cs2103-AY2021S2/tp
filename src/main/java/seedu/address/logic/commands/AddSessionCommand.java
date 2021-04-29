package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.student.Name;

public class AddSessionCommand extends Command {

    public static final String COMMAND_WORD = "add_session";
    public static final String SESSION_ALREADY_EXIST_ERROR = "A session already exists at this time!";
    public static final String SESSION_OVERLAP = "Session overlaps with an existing session";
    public static final String STUDENT_DOES_NOT_EXIST = "Student with such a name does not exists.";
    public static final String MESSAGE_ADD_SESSION_SUCCESS = "Added Session: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a session to the student. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_FEE + "FEE "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2020-01-01 "
            + PREFIX_TIME + "12:00 "
            + PREFIX_DURATION + "90 "
            + PREFIX_SUBJECT + "Math "
            + PREFIX_FEE + "40";

    private Session sessionToAdd;
    private Name name;

    /**
     * Creates an AddSessionCommand to add the specified {@code Session}
     */
    public AddSessionCommand(Session session, Name name) {
        requireAllNonNull(session, name);
        sessionToAdd = session;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasName(name)) {
            throw new CommandException(STUDENT_DOES_NOT_EXIST);
        }
        if (model.hasSession(sessionToAdd)) {
            throw new CommandException(SESSION_ALREADY_EXIST_ERROR);
        }

        if (sessionToAdd instanceof RecurringSession) {
            RecurringSession recurringSessionToAdd = (RecurringSession) sessionToAdd;
            if (model.hasOverlappingSession(recurringSessionToAdd)) {
                throw new CommandException(SESSION_OVERLAP);
            }
        } else if (model.hasOverlappingSession(sessionToAdd)) {
            throw new CommandException(SESSION_OVERLAP);
        }

        model.addSession(name, sessionToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_SESSION_SUCCESS, sessionToAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSessionCommand // instanceof handles nulls
                && name.equals(((AddSessionCommand) other).name)
                && sessionToAdd.equals(((AddSessionCommand) other).sessionToAdd));
    }
}
