package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

/**
 * Converts a note identified using it's displayed index from the notes list into txt file.
 */
public class ConvertTxtNoteCommand extends Command {

    public static final String COMMAND_WORD = "converttxtnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Converts the note identified by the index number used in "
            + "the displayed note list into txt file.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CONVERT_TXT_NOTE_SUCCESS = "Converted note: %1$s";

    private static String FILE_PATH = "./data/";

    private final Index targetIndex;

    /**
     * Creates an ConvertTxtNoteCommand of at a index.
     *
     * @param targetIndex note index to be converted.
     */
    public ConvertTxtNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        List<Note> lastShownList = model.getFilteredNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToConvert = lastShownList.get(targetIndex.getZeroBased());

        try {
            convertTxtNote(noteToConvert);
        } catch (IOException e) {
            System.out.println("Something is wrong in the converting txt Note Process");
        }

        return new CommandResult(String.format(MESSAGE_CONVERT_TXT_NOTE_SUCCESS, noteToConvert),
                UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    private void convertTxtNote(Note note) throws IOException {
        String noteToString = note.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + note.createFileName()));
        writer.write(noteToString);
        writer.close();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConvertTxtNoteCommand // instanceof handles nulls
                && targetIndex.equals(((ConvertTxtNoteCommand) other).targetIndex)); // state check
    }
}
