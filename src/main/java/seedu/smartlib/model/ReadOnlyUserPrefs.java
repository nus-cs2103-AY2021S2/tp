package seedu.smartlib.model;

import java.nio.file.Path;

import seedu.smartlib.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /**
     * Returns the GUI settings of the userPrefs.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the file path to SmartLib registered in the userPrefs.
     */
    Path getSmartLibFilePath();

}
