package seedu.ta.model;

import java.nio.file.Path;

import seedu.ta.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTeachingAssistantFilePath();
}
