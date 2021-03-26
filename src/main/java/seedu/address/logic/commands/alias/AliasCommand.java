//Solution below adapted from https://github.com/briyanii/main
package seedu.address.logic.commands.alias;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sets a shortcut command for a longer command.
 */
public class AliasCommand extends Command {
    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a shortcut command for a longer command.\n"
            + "Parameters: a/ALIAS cmd/COMMAND\n"
            + "Example: " + COMMAND_WORD + " a/rl cmd/rlist";

    public static final String MESSAGE_SUCCESS = "Alias created: %1$s";

    public static final String MESSAGE_RESERVED_KEYWORD =
            "%1$s is a reserved keyword and cannot be used as an alias";

    public static final String MESSAGE_RECURSIVE =
            "Recursive alias is not allowed";

    private final Alias alias;

    /**
     * Creates an AliasCommand object.
     */
    public AliasCommand(Alias alias) {
        requireAllNonNull(alias);
        this.alias = alias;
    }

    /**
     * Excecutes an AliasCommand to create a new Alias
     * @param model {@code Model} which the command should operate on.
     * @return result of command execution
     * @throws CommandException if input is invalid
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        // if the command word is a reserved keyword
        String aliasName = alias.getAliasName();
        if (model.getUserPrefs().isReservedKeyword(aliasName)) {
            throw new CommandException(String.format(MESSAGE_RESERVED_KEYWORD, aliasName));
        }

        // if the command word is recursive keyword
        String commandWord = alias.getCommand().stripLeading().split("\\s+")[0];
        if (commandWord.equals(aliasName)
                || commandWord.equals(COMMAND_WORD)
                || model.getUserPrefs().isRecursiveKeyword(commandWord)) {
            throw new CommandException(MESSAGE_RECURSIVE);
        }

        // update model
        model.addAlias(alias);

        return new CommandResult(String.format(MESSAGE_SUCCESS, alias.getAliasName()));
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof AliasCommand // instanceof handles nulls
                && this.alias.equals(((AliasCommand) obj).alias));
    }
}
