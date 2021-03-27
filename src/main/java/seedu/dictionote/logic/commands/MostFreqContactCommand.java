package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;

/**
 * Lists all contacts in the contacts list to the user.
 */
public class MostFreqContactCommand extends Command {

    public static final String COMMAND_WORD = "mostfreqcontact";

    public static final String MESSAGE_SUCCESS = "Listed all contacts, sorted by most frequently-contacted";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortContactsByFrequencyCounter();
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.CONTACT);
    }

}
