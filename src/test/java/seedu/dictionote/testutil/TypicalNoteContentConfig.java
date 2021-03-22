package seedu.dictionote.testutil;

import static seedu.dictionote.logic.parser.CliSyntax.OPTION_ALL_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_CONTACT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_PANEL;
import static seedu.dictionote.testutil.TypicalNotes.CS2103T_EXAM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.ReadOnlyAddressBook;
import seedu.dictionote.model.ReadOnlyDefinitionBook;
import seedu.dictionote.model.ReadOnlyDictionary;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.ReadOnlyUserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.ui.NoteContentConfig;

/**
 * A utility class containing a arrau of {@code UiActionOption} objects to be used in tests.
 */
public class TypicalNoteContentConfig {


    public static NoteContentConfig getTypicalNoteContentConfigWitouthNote() {
        return new NoteContentConfigStubWithoutNote();
    }

    public static NoteContentConfig getTypicalNoteContentConfigWithNote() {
        return new NoteContentConfigStubWithNote();
    }

    public static NoteContentConfig getTypicalNoteContentConfigEditMode() {
        return new NoteContentConfigStubEditMode();
    }

    /**
     * A stub for note content config for failure test
     */
    private static class NoteContentConfigStubWithoutNote implements NoteContentConfig {
        @Override
        public void setNote(Note note) {

        }

        @Override
        public boolean haveNote() {
            return false;
        }

        @Override
        public void resetNote() {

        }

        @Override
        public String getEditedContent() {
            return null;
        }

        @Override
        public Note getNote() {
            return null;
        }

        @Override
        public boolean onEditMode() {
            return false;
        }
    }

    /**
     * A stub for note content config for success test
     */
    private static class NoteContentConfigStubWithNote implements NoteContentConfig {
        @Override
        public void setNote(Note note) {

        }

        @Override
        public boolean haveNote() {
            return true;
        }

        @Override
        public void resetNote() {

        }

        @Override
        public String getEditedContent() {
            return null;
        }

        @Override
        public Note getNote() {
            return null;
        }

        @Override
        public boolean onEditMode() {
            return false;
        }
    }

    /**
     * A stub for note content config for success test
     */
    private static class NoteContentConfigStubEditMode implements NoteContentConfig {
        @Override
        public void setNote(Note note) {

        }

        @Override
        public boolean haveNote() {
            return true;
        }

        @Override
        public void resetNote() {

        }

        @Override
        public String getEditedContent() {
            return null;
        }

        @Override
        public Note getNote() {
            return null;
        }

        @Override
        public boolean onEditMode() {
            return true;
        }
    }
}
