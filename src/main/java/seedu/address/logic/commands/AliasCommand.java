package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a person to the address book.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a alias to FlashBack. "
            + "Parameters: "
            + PREFIX_ALIAS_COMMAND + "COMMAND "
            + PREFIX_ALIAS_NAME + "ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS_COMMAND + "add "
            + PREFIX_ALIAS_NAME + "a";
    public static final String MESSAGE_SUCCESS = "New alias added for %1$s command:\n%2$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias already exists in FlashBack.";

    private final String command;
    private final String alias;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AliasCommand(String command, String alias) {
        requireNonNull(command);
        requireNonNull(alias);
        this.command = command;
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.canAddAlias(command, alias)) {
            throw new CommandException(MESSAGE_DUPLICATE_ALIAS);
        }

        model.addAlias(command, alias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, command, alias));
    }
}
