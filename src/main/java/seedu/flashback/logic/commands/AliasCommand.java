package seedu.flashback.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_COMMAND;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_NAME;

import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.model.Model;

/**
 * Adds an alias to FlashBack.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an alias to FlashBack. "
            + "Parameters: "
            + PREFIX_ALIAS_COMMAND + "COMMAND "
            + PREFIX_ALIAS_NAME + "ALIAS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS_COMMAND + "add "
            + PREFIX_ALIAS_NAME + "ad";
    public static final String MESSAGE_SUCCESS = "New alias added for \"%1$s\" command:\n%2$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias \"%1$s\" already exists in FlashBack.";
    public static final String MESSAGE_ALIAS_IS_COMMAND = "The alias \"%1$s\" should not be a command in FlashBack.";
    public static final String MESSAGE_INVALID_COMMAND = "The command \"%1$s\" does not exist in FlashBack.";
    public static final String MESSAGE_COMMAND_IS_REVIEW =
            "The command \"%1$s\" should not be a command in review mode.";

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

        if (model.isAlias(alias)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ALIAS, alias));
        } else if (model.isCommand(alias)) {
            throw new CommandException(String.format(MESSAGE_ALIAS_IS_COMMAND, alias));
        } else if (model.isReview(alias)) {
            throw new CommandException(String.format(MESSAGE_ALIAS_IS_COMMAND, alias));
        } else if (model.isReview(command)) {
            throw new CommandException(String.format(MESSAGE_COMMAND_IS_REVIEW, command));
        } else if (!model.isCommand(command)) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND, command));
        }
        model.addAlias(command, alias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, command, alias));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasCommand // instanceof handles nulls
                && command.equals(((AliasCommand) other).command)
                && alias.equals(((AliasCommand) other).alias));
    }
}
