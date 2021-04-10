package seedu.address.logic.commands.alias;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.AliasMapping;

/**
 * Lists all current aliases.
 */
public class ListAliasCommand extends Command {
    public static final String COMMAND_WORD = "aliases";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all user-defined aliases.";

    public static final String MESSAGE_EMPTY_ALIAS = "No aliases have been created yet.";

    public static final String MESSAGE_SUCCESS_HEADER = "All %d aliases listed: \n";

    /**
     * Executes an ListAliasCommand to list all aliases.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution.
     * @throws CommandException If input is invalid.
     * @throws NullPointerException If the input is null.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        AliasMapping mapping = model.getAliasMapping();
        assert mapping != null;

        int aliasCount = mapping.size();
        assert aliasCount >= 0;

        if (aliasCount == 0) {
            return new CommandResult(MESSAGE_EMPTY_ALIAS);
        }

        StringBuilder msg = new StringBuilder();
        msg.append(String.format(MESSAGE_SUCCESS_HEADER, aliasCount));
        msg.append(model.getAliasMapping().toString());

        return new CommandResult(msg.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListAliasCommand;
    }
}
