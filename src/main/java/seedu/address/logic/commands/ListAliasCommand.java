package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAliasCommand extends AliasCommand {

    public static final String SHOWING_ALIASES_MESSAGE = "You have %d aliases.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        int numOfAlias = model.getNumOfAlias();
        return new CommandResult(String.format(SHOWING_ALIASES_MESSAGE, numOfAlias),
                false, true, false);
    }
}
