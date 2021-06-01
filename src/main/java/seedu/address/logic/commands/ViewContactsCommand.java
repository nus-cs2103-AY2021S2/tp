package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.logic.uicommands.ShowContactsUiCommand;
import seedu.address.model.Model;

/**
 * Shows all Contacts in the contact list to the user.
 */
public class ViewContactsCommand extends Command {

    public static final String COMMAND_WORD = "contacts";

    public static final String MESSAGE_SUCCESS = "Viewing Contacts.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS, new ShowContactsUiCommand()).setIgnoreHistory(true);
    }
}
