package seedu.address.model;

/**
 * Unmodifiable view of an alias map.
 */
public interface ReadOnlyUniqueAliasMap {

    /**
     * Returns the command mapped to alias if alias is found in alias map.
     * Otherwise, userInput is returned.
     */
    String parseAliasToCommand(String userInput);

}
