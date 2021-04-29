package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.student.Name;


/**
 * Adds a recurring session to the address book.
 */
public class AddRecurringSessionCommand extends AddSessionCommand {
    public static final String COMMAND_WORD = "add_rec_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recurring session to the student. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE "
            + PREFIX_INTERVAL + "INTERVAL "
            + PREFIX_TIME + "TIME "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_FEE + "FEE"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2020-01-01 "
            + PREFIX_END_DATE + "2020-01-15 "
            + PREFIX_INTERVAL + "7 "
            + PREFIX_TIME + "12:00 "
            + PREFIX_DURATION + "90 "
            + PREFIX_SUBJECT + "Math "
            + PREFIX_FEE + "40";

    public static final String MESSAGE_SUCCESS = "Added recurring session: %1$s";
    private RecurringSession sessionToAdd;
    private Name name;
    /**
     * Creates an AddRecurringSessionCommand to add the specified {@code RecurringSession}
     */
    public AddRecurringSessionCommand(RecurringSession recurringSession, Name name) {
        super(recurringSession, name);
        sessionToAdd = recurringSession;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        super.execute(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sessionToAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecurringSessionCommand // instanceof handles nulls
                && name.equals(((AddRecurringSessionCommand) other).name)
                && sessionToAdd.equals(((AddRecurringSessionCommand) other).sessionToAdd));
    }

}
