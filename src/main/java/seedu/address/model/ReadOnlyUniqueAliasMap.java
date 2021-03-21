package seedu.address.model;

import javafx.collections.ObservableMap;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;

/**
 * Unmodifiable view of an aliases.
 */
public interface ReadOnlyUniqueAliasMap {

    /**
     * Returns the command mapped to alias if alias is found in aliases.
     * Otherwise, userInput is returned.
     */
    String parseAliasToCommand(String userInput);

    /**
     * Returns an unmodifiable view of the aliases.
     * This list will not contain any duplicate aliases.
     */
    ObservableMap<Alias, CommandAlias> getAliases();

}
