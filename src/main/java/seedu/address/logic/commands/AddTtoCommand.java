package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddTtoCommand {

    public static final String COMMAND_WORD = "addTto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Task to CoLAB. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_DEADLINE_DATE + "DUE DATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Submit project report "
            + PREFIX_TAG + "17-10-2021";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in CoLAB";

    private final Completable toAdd;

    /**
     * Creates an AddTtoCommand to add the specified {@code Task}
     */
    public AddTtoCommand(Completable completable) {
        requireNonNull(completable);
        toAdd = completable;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTtoCommand // instanceof handles nulls
                && toAdd.equals(((AddTtoCommand) other).toAdd));
    }
}
