package seedu.student.model;

import java.nio.file.Path;

import seedu.student.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudentBookFilePath();

}
