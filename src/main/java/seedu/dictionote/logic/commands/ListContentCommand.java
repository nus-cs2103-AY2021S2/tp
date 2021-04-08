package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTENTS;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;

/**
 * Lists all contacts in the contacts list to the user.
 */
public class ListContentCommand extends Command {

    public static final String COMMAND_WORD = "listcontent";

    public static final String MESSAGE_SUCCESS = "Dictionary shown in the Dictionary Panel.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContentList(PREDICATE_SHOW_ALL_CONTENTS);
        return new CommandResult(MESSAGE_SUCCESS, UiAction.OPEN, UiActionOption.DICTIONARY_LIST);
    }

}
