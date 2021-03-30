package seedu.dictionote.testutil;

import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;

import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.model.note.Note;

/**
 * A utility class for Note.
 */
public class NoteUtil {

    /**
     * Returns an addNote command string for adding the {@code note}.
     * @param note Note to be added.
     */
    public static String getAddNoteCommand(Note note) {
        return AddNoteCommand.COMMAND_WORD + " " + getNoteDetails(note);
    }

    public static String getNoteDetails(Note note) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CONTENT + note.getNote());
        return sb.toString();
    }
}
