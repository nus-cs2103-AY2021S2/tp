package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    //=========== Alias =============================================================
    AliasMapping getAliasMapping();

    Alias getAlias(String aliasName);

    boolean containsAlias(String aliasName);

    boolean isReservedKeyword(String aliasName);

    boolean isRecursiveKeyword(String commandWord);
}
