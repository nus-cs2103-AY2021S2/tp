package seedu.dictionote.model;

import java.nio.file.Path;

import seedu.dictionote.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getNoteBookFilePath();

    Path getDictionaryFilePath();

}
