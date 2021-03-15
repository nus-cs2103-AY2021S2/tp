package seedu.address.model;

import java.util.Map;

import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;

/**
 * Unmodifiable view of an alias map.
 */
public interface ReadOnlyUniqueAliasMap {

    /**
     * Returns the command mapped to alias if alias is found in alias map.
     * Otherwise, userInput is returned.
     */
    String parseAliasToCommand(String userInput);

    /**
     * Returns an unmodifiable view of the aliases.
     * This list will not contain any duplicate aliases.
     */
    Map<Alias, CommandAlias> getAliases();

}
