package seedu.storemando.model;

import java.nio.file.Path;

import seedu.storemando.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStoreMandoFilePath();

}
