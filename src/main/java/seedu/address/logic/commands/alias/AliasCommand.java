//Solution below adapted from https://github.com/briyanii/main
package seedu.address.logic.commands.alias;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.Alias;

/**
 * Sets a shortcut command for a longer command.
 */
public class AliasCommand extends Command {
    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a shortcut command for a longer command.\n"
            + "Parameters: a/ALIAS cmd/COMMAND\n"
            + "Example: " + COMMAND_WORD + " a/rl cmd/rlist";

    public static final String MESSAGE_SUCCESS_NEW = "Alias created: %1$s";

    public static final String MESSAGE_SUCCESS_UPDATED = "Alias updated: %1$s";

    public static final String MESSAGE_RESERVED_KEYWORD =
            "%1$s is a reserved keyword and cannot be used as an alias";

    public static final String MESSAGE_RECURSIVE =
            "Recursive alias is not allowed";

    private final Logger logger = LogsCenter.getLogger(AliasCommand.class);

    private final Alias alias;

    /**
     * Creates an AliasCommand object.
     * The field must not be null.
     *
     * @param alias Alias object.
     * @throws NullPointerException If the input is null.
     */
    public AliasCommand(Alias alias) {
        requireAllNonNull(alias);
        this.alias = alias;
    }

    /**
     * Executes an AliasCommand to create a new Alias.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution.
     * @throws CommandException If input is invalid.
     * @throws NullPointerException If the input is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        // if the command word is a reserved keyword
        String aliasName = alias.getAliasName();
        assert aliasName != null;

        if (model.getAliasMapping().isReservedKeyword(aliasName)) {
            logger.warning("Reserved keyword for alias name given");
            throw new CommandException(String.format(MESSAGE_RESERVED_KEYWORD, aliasName));
        }
        boolean isExisting = model.getAliasMapping().containsAlias(aliasName);

        // if the command word is recursive keyword
        String commandWord = alias.getCommand().stripLeading().split("\\s+")[0];
        assert commandWord != null;

        if (commandWord.equals(aliasName)
                || commandWord.equals(COMMAND_WORD)
                || model.getAliasMapping().isRecursiveKeyword(commandWord)) {
            logger.warning("Recursive alias given");
            throw new CommandException(MESSAGE_RECURSIVE);
        }

        // update model
        model.addAlias(alias);
        model.commitAddressBook();

        if (isExisting) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_UPDATED, alias.getAliasName()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_NEW, alias.getAliasName()));
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof AliasCommand // instanceof handles nulls
                && this.alias.equals(((AliasCommand) obj).alias));
    }
}
