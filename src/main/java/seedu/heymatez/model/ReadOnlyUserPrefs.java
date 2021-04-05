package seedu.heymatez.model;

import java.nio.file.Path;

import seedu.heymatez.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getHeyMatezFilePath();

}
