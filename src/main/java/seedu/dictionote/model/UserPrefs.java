package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.dictionote.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path contactsListFilePath = Paths.get("data" , "contactslist.json");
    private Path noteBookFilePath = Paths.get("data" , "notebook.json");
    private Path dictionaryFilePath = Paths.get("data", "dictionarybook.json");
    private Path definitionBookFilePath = Paths.get("data", "definitionbook.json");
    private final String localPath = "\nLocal data file location : ";

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setContactsListFilePath(newUserPrefs.getContactsListFilePath());
        setNoteBookFilePath(newUserPrefs.getNoteBookFilePath());
        setDictionaryFilePath(newUserPrefs.getDictionaryFilePath());
        setDefinitionBookFilePath(newUserPrefs.getDefinitionBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getContactsListFilePath() {
        return contactsListFilePath;
    }

    public void setContactsListFilePath(Path contactsListFilePath) {
        requireNonNull(contactsListFilePath);
        this.contactsListFilePath = contactsListFilePath;
    }

    public Path getNoteBookFilePath() {
        return noteBookFilePath;
    }

    public void setNoteBookFilePath(Path noteBookFilePath) {
        requireNonNull(noteBookFilePath);
        this.noteBookFilePath = noteBookFilePath;
    }

    public Path getDictionaryFilePath() {
        return dictionaryFilePath;
    }

    public void setDictionaryFilePath(Path dictionaryFilePath) {
        requireNonNull(dictionaryFilePath);
        this.dictionaryFilePath = dictionaryFilePath;
    }

    public Path getDefinitionBookFilePath() {
        return definitionBookFilePath;
    }

    public void setDefinitionBookFilePath(Path definitionBookFilePath) {
        requireNonNull(definitionBookFilePath);
        this.definitionBookFilePath = definitionBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && contactsListFilePath.equals(o.contactsListFilePath)
                && noteBookFilePath.equals(o.noteBookFilePath)
                && dictionaryFilePath.equals(o.dictionaryFilePath)
                && definitionBookFilePath.equals(o.definitionBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, contactsListFilePath, noteBookFilePath,
                dictionaryFilePath, definitionBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append(localPath + contactsListFilePath);
        sb.append(localPath + noteBookFilePath);
        sb.append(localPath + dictionaryFilePath);
        sb.append(localPath + definitionBookFilePath);
        return sb.toString();
    }

}
