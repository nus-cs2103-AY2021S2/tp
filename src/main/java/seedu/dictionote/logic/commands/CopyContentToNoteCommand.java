package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;

/**
 * Copies content over from the Dictionary to new Note.
 */
public class CopyContentToNoteCommand extends Command {
    public static final String COMMAND_WORD = "copytonote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the entire content of the specified content in the Dictionary into a note."
            + "The note can be edited later (call editnote) to trim the content details.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Note with the respective content added.";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the dictionote book";

    private final Index targetIndex;

    /**
     * Creates an CopyContentToNoteCommand to carry out the transfer of data at a specified {@code targetIndex}.
     */
    public CopyContentToNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        List<? extends DisplayableContent> lastShownList = model.getFilteredCurrentDictionaryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DICTIONARY_CONTENT_DISPLAYED_INDEX);
        }

        String contentToAdd = lastShownList.get(targetIndex.getZeroBased()).getDictionaryHeader() + "\n"
            + lastShownList.get(targetIndex.getZeroBased()).getDictionaryContent();

        Note createdNote = new Note(contentToAdd);

        if (model.hasNote(createdNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(createdNote);
        return new CommandResult(String.format(MESSAGE_SUCCESS, createdNote), UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyContentToNoteCommand // instanceof handles nulls
                && targetIndex.equals(((CopyContentToNoteCommand) other).targetIndex));
    }
}
