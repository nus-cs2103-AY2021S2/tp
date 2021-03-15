package seedu.iscam.model;

import java.nio.file.Path;

import seedu.iscam.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getClientBookFilePath();

}
