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
     *
     * @return The mapping.
     */
    AliasMapping getAliasMapping();

    /**
     * Returns an Alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return The alias with the specified name.
     */
    Alias getAlias(String aliasName);

    /**
     * Deletes a user-defined alias from the current mapping.
     *
     * @param aliasName The name of the alias to be deleted.
     */
    void deleteAlias(String aliasName);

    /**
     * Checks if the current mapping contains an alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the mapping contains the alias.
     */
    boolean containsAlias(String aliasName);

    /**
     * Checks if the alias name is a reserved keyword.
     *
     * @param aliasName Name of the alias.
     * @return Whether the name is reserved.
     */
    boolean isReservedKeyword(String aliasName);

    /**
     * Checks if the command word is recursive.
     *
     * @param commandWord The command word.
     * @return Whether the command word is recursive.
     */
    boolean isRecursiveKeyword(String commandWord);

    //=========== CommandHistory ====================================================

    /**
     * Returns file path to command history data.
     */
    Path getCommandHistoryFilePath();
}
