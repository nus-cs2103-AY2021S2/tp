package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableMap;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;

/**
 * Unmodifiable view of an aliases.
 */
public interface ReadOnlyUniqueAliasMap {

    /**
     * Returns the command mapped to alias if alias is found in command aliases.
     * Otherwise, userInput is returned.
     */
    String parseAliasToCommand(String userInput);

    /**
     * Returns an unmodifiable map of the command aliases.
     * This map will not contain any duplicate aliases.
     */
    ObservableMap<Alias, CommandAlias> getCommandAliases();

    /**
     * Returns the number of aliases.
     */
    int getNumOfAlias();

    /**
     * Returns a list of command aliases in String.
     */
    List<String> getCommandAliasesStringList();

}
