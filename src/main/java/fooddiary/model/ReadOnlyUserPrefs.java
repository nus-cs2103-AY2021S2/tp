package fooddiary.model;

import java.nio.file.Path;

import fooddiary.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFoodDiaryFilePath();

}
