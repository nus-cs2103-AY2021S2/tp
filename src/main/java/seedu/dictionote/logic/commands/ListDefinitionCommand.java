package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_DEFINITION;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;

/**
 * Lists all contacts in the contacts list to the user.
 */
public class ListDefinitionCommand extends Command {

    public static final String COMMAND_WORD = "listdef";

    public static final String MESSAGE_SUCCESS = "All existing definitions shown.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDefinitionList(PREDICATE_SHOW_ALL_DEFINITION);
        return new CommandResult(MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.DICTIONARY_DEFINITIONS);
    }

}
