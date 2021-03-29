package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.Model;

/**
 * Clears the contacts list.
 */
public class ClearContactCommand extends Command {

    public static final String COMMAND_WORD = "clearcontact";
    public static final String MESSAGE_SUCCESS = "Contacts list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactsList(new ContactsList());
        return new CommandResult(MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.CONTACT);
    }
}
