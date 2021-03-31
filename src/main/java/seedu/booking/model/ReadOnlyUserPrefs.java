package seedu.booking.model;

import java.nio.file.Path;

import seedu.booking.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBookingSystemFilePath();

}
