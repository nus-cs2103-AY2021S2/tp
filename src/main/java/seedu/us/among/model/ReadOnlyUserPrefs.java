package seedu.us.among.model;

import java.nio.file.Path;

import seedu.us.among.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getEndpointListFilePath();

}
