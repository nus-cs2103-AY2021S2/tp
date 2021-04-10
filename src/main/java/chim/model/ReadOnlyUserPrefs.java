package chim.model;

import java.nio.file.Path;

import chim.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getChimFilePath();

}
