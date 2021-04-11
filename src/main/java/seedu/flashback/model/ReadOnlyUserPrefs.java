package seedu.flashback.model;

import java.nio.file.Path;

import seedu.flashback.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFlashBackFilePath();

    AliasMap getAliasMap();

}
