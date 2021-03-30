package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;

/**
 * Lists all contacts in the contacts list to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listcontact";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.CONTACT);
    }

}
