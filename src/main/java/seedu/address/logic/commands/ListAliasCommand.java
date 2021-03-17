package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAliasCommand extends AliasCommand {

    public static final String SHOWING_ALIASES_MESSAGE = "Opened aliases window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_ALIASES_MESSAGE, false, true, false);
    }
}
