package seedu.budgetbaby.model;

import java.nio.file.Path;

import seedu.budgetbaby.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBudgetBabyFilePath();

}
