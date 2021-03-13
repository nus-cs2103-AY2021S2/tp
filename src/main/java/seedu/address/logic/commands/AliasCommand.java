package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sets a shortcut command for a longer command.
 */
public class AliasCommand extends Command {
    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_ARGUMENTS = "Alias: %1$s, Command: %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a shortcut command for a longer command.\n"
            + "Parameters: a/ALIAS c/COMMAND\n"
            + "Example: " + COMMAND_WORD + " a/rl c/rlist";

    public static final String MESSAGE_SUCCESS = "Alias created: %1$s";
    public static final String MESSAGE_RESERVED_KEYWORD =
            "%1$s is a reserved keyword and cannot be used as an alias";
    public static final String MESSAGE_RECURSIVE =
            "Recursive alias is not allowed";

    private final Alias alias;

    public AliasCommand(Alias alias) {
        requireAllNonNull(alias);
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        // if command word is reserved
        String aliasName = alias.getAliasName();
        if (model.getUserPrefs().isReservedKeyword(aliasName)) {
            throw new CommandException(String.format(MESSAGE_RESERVED_KEYWORD, aliasName));
        }

        // if recursive
        String commandWord = alias.getCommand().stripLeading().split("\\s+")[0];
        if (commandWord.equalsIgnoreCase(aliasName)
                || commandWord.equalsIgnoreCase(COMMAND_WORD)
                || model.getUserPrefs().aliasCommandWordContainsAlias(commandWord)) {
            throw new CommandException(MESSAGE_RECURSIVE);
        }

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
