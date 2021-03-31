package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "sunrez.json");
    private AliasMapping aliasMapping = new AliasMapping();
    private Path commandHistoryFilePath = Paths.get("data", "commandhistory.txt");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setAliasMapping(newUserPrefs.getAliasMapping());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    /**
     * Returns the current alias mapping.
     *
     * @return The mapping.
     */
    public AliasMapping getAliasMapping() {
        return aliasMapping;
    }

    /**
     * Sets the current mapping to the specified mapping.
     *
     * @param aliasMappings The mapping.
     * @throws NullPointerException If the input is null.
     */
    public void setAliasMapping(AliasMapping aliasMappings) {
        requireNonNull(aliasMappings);
        this.aliasMapping = aliasMappings;
    }

    /**
     * Adds a user-defined alias to the current mapping.
     *
     * @param alias The alias to be added.
     * @throws NullPointerException If the input is null.
     */
    public void addAlias(Alias alias) {
        requireNonNull(alias);
        aliasMapping.addAlias(alias);
    }

    /**
     * Deletes a user-defined alias from the current mapping.
     *
     * @param aliasName The name of the alias to be deleted.
     * @throws NullPointerException If the input is null.
     */
    public void deleteAlias(String aliasName) {
        requireNonNull(aliasName);
        aliasMapping.deleteAlias(aliasName);
    }

    /**
     * Returns an Alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return The alias with the specified name.
     * @throws NullPointerException If the input is null.
     */
    public Alias getAlias(String aliasName) {
        requireNonNull(aliasName);
        return aliasMapping.getAlias(aliasName);
    }

    /**
     * Checks if the current mapping contains an alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the mapping contains the alias.
     */
    @Override
    public boolean containsAlias(String aliasName) {
        return aliasMapping.containsAlias(aliasName);
    }

    /**
     * Checks if the alias name is a reserved keyword.
     *
     * @param aliasName Name of the alias.
     * @return Whether the name is reserved.
     * @throws NullPointerException If the input is null.
     */
    public boolean isReservedKeyword(String aliasName) {
        requireNonNull(aliasName);
        return aliasMapping.isReservedKeyword(aliasName);
    }

    /**
     * Checks if the command word is recursive.
     *
     * @param commandWord The command word.
     * @return Whether the command word is recursive.
     * @throws NullPointerException If the input is null.
     */
    public boolean isRecursiveKeyword(String commandWord) {
        requireNonNull(commandWord);
        return aliasMapping.isRecursiveKeyword(commandWord);
    }

    /**
     * Returns the command history file path.
     * @return The command history file path.
     */
    @Override
    public Path getCommandHistoryFilePath() {
        return commandHistoryFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && aliasMapping.equals(o.aliasMapping)
                && commandHistoryFilePath.equals(o.commandHistoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, aliasMapping, commandHistoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nLocal command history file location : " + commandHistoryFilePath);
        return sb.toString();
    }
}
