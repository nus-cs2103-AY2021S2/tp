package seedu.address.logic.commands.alias;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes the alias with the specified name.
 */
public class UnaliasCommand extends Command {

    public static final String COMMAND_WORD = "unalias";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete alias with the specified name.\n"
            + "Parameters: a/ALIAS\n"
            + "Example: unalias a/findBob";

    public static final String MESSAGE_SUCCESS = "Alias deleted: %1$s";

    public static final String MESSAGE_FAILURE = "Alias not found";

    private String aliasName;

    /**
     * Creates an UnaliasCommand with the specified {@code aliasName}
     */
    public UnaliasCommand(String aliasName) {
        requireNonNull(aliasName);
        this.aliasName = aliasName.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // if no alias with the specified name is found
        if (!model.getUserPrefs().containsAlias(aliasName)) {
            throw new CommandException(String.format(MESSAGE_FAILURE, aliasName));
        }

        //update model
        model.deleteAlias(aliasName);

        return new CommandResult(String.format(MESSAGE_SUCCESS, aliasName));
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof UnaliasCommand // instanceof handles nulls
                && this.aliasName.trim().equals(((UnaliasCommand) obj).aliasName.trim()));
    }
}
