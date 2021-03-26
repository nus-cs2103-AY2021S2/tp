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

    /**
     * Returns the current alias mapping.
     */
    AliasMapping getAliasMapping();

    /**
     * Returns an Alias based on name.
     */
    Alias getAlias(String aliasName);

    /**
     * Deletes an user-defined alias from the current mapping.
     * @param aliasName The name of the alias to be deleted.
     */
    void deleteAlias(String aliasName);

    /**
     * Checks if the current mapping contains an alias based on name.
     */
    boolean containsAlias(String aliasName);

    /**
     * Checks if the alias name is a reserved keyword.
     */
    boolean isReservedKeyword(String aliasName);

    /**
     * Checks if the command word is recursive.
     */
    boolean isRecursiveKeyword(String commandWord);
}
