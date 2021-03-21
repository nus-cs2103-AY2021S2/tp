package seedu.timeforwheels.model;

import java.nio.file.Path;

import seedu.timeforwheels.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getDeliveryListFilePath();

}
