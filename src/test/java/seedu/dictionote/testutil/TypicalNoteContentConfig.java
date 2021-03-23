package seedu.dictionote.testutil;

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
        private Note note;
        @Override
        public void setNote(Note note) {
            this.note = note;
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
            return "edited";
        }

        @Override
        public Note getNote() {
            return note;
        }

        @Override
        public boolean onEditMode() {
            return true;
        }
    }
}
