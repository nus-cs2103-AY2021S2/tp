package seedu.weeblingo.model;

import java.nio.file.Path;

import seedu.weeblingo.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFlashcardBookFilePath();

}
