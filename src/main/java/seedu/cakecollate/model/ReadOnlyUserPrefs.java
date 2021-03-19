package seedu.cakecollate.model;

import java.nio.file.Path;

import seedu.cakecollate.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getCakeCollateFilePath();

}
