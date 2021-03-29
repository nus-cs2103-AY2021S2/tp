package seedu.plan.model;

import java.nio.file.Path;

import seedu.plan.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPlansFilePath();
}
