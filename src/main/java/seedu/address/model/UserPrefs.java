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
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private AliasMapping aliasMapping = new AliasMapping();

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
     */
    public AliasMapping getAliasMapping() {
        return aliasMapping;
    }

    /**
     * Sets the current mapping to the specified mapping.
     */
    public void setAliasMapping(AliasMapping aliasMappings) {
        requireNonNull(aliasMappings);
        this.aliasMapping = aliasMappings;
    }

    /**
     * Adds an user-defined alias to the current mapping.
     */
    public void addAlias(Alias alias) {
        requireNonNull(alias);
        aliasMapping.addAlias(alias);
    }

    /**
     * Deletes an user-defined alias from the current mapping.
     * @param aliasName The name of the alias to be deleted.
     */
    public void deleteAlias(String aliasName) {
        requireNonNull(aliasName);
        aliasMapping.deleteAlias(aliasName);
    }

    /**
     * Returns an Alias object based on alias name.
     */
    public Alias getAlias(String aliasName) {
        requireNonNull(aliasName);
        return aliasMapping.getAlias(aliasName);
    }

    /**
     * Checks if the current mapping contains an Alias based on alias name.
     */
    @Override
    public boolean containsAlias(String aliasName) {
        return aliasMapping.containsAlias(aliasName);
    }

    /**
     * Check if the alias name is a reserved keyword.
     */
    public boolean isReservedKeyword(String aliasName) {
        requireNonNull(aliasName);
        return aliasMapping.isReservedKeyword(aliasName);
    }

    /**
     * Check if the command word is a recursive keyword.
     */
    public boolean isRecursiveKeyword(String commandWord) {
        requireNonNull(commandWord);
        return aliasMapping.isRecursiveKeyword(commandWord);
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
                && aliasMapping.equals(o.aliasMapping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, aliasMapping);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }
}
