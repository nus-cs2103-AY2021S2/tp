package seedu.address.logic.commands.alias;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.AliasMapping;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListAliasCommand extends Command {
    public static final String COMMAND_WORD = "aliases";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all user-defined aliases.";

    public static final String MESSAGE_EMPTY_ALIAS = "No aliases have been created yet.";

    public static final String MESSAGE_SUCCESS_HEADER = "All %d aliases listed: \n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        AliasMapping mapping = model.getAliasMapping();

        int aliasCount = mapping.size();

        if (mapping.size() == 0) {
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
