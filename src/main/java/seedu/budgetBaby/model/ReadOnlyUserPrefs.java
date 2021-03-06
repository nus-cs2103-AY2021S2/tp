package seedu.budgetBaby.model;

import java.nio.file.Path;

import seedu.budgetBaby.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

}
