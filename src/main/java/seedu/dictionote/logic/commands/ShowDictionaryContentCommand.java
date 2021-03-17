package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.DisplayableContent;

/**
 * Show a specific dictionary content in the content list to the user.
 */
public class ShowDictionaryContentCommand extends Command {
    public static final String COMMAND_WORD = "showdc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Showed a specific dictionary content identified by the index number used in the displayed"
            + "dictionary content list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_CONTENT_SUCCESS = "Here is the content";

    private final Index targetIndex;

    public ShowDictionaryContentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends DisplayableContent> lastShownList = model.getFilteredCurrentDictionaryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DICTIONARY_CONTENT_DISPLAYED_INDEX);
        }

        DisplayableContent contentToShow = lastShownList.get(targetIndex.getZeroBased());
        model.showDictionaryContent(contentToShow);
        return new CommandResult(String.format(MESSAGE_SHOW_CONTENT_SUCCESS, contentToShow),
                UiAction.OPEN, UiActionOption.DICTIONARY_CONTENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowDictionaryContentCommand // instanceof handles nulls
                && targetIndex.equals(((ShowDictionaryContentCommand) other).targetIndex)); // state check
    }
}
