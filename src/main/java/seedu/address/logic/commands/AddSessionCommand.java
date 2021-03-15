package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

public class AddSessionCommand extends Command {

    public static final String COMMAND_WORD = "add_session";
    public static final String MESSAGE_SUCCESS = "New session added: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a session to a particular student. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DURATION + "LENGTH OF SESSION "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_FEE + "FEE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2020-01-01 "
            + PREFIX_TIME + "12:00 "
            + PREFIX_DURATION + "90 "
            + PREFIX_SUBJECT+ "Math"
            + PREFIX_FEE + "40 ";

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
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addSession(sessionToAdd, name);
        return new CommandResult(MESSAGE_SUCCESS + sessionToAdd + " for " + name);
    }


}
