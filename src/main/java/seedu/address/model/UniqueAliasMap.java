package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.alias.exceptions.AliasNotFoundException;
import seedu.address.model.alias.exceptions.DuplicateAliasException;

/**
 * A map of aliases to commands that enforces uniqueness between its elements and does not allow nulls.
 * An alias is considered unique by comparing using {@code Alias#equals(Object)}.
 */
public class UniqueAliasMap implements ReadOnlyUniqueAliasMap {

    private static final String EMPTY_ALIASES = "You currently have 0 alias";

    private final HashMap<Alias, CommandAlias> aliasMap = new HashMap<>();

    /**
     * Returns true if the map contains an equivalent alias as the given argument.
     */
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return aliasMap.containsKey(alias);
    }

    /**
     * Returns true if the map contains an equivalent command alias as the given argument.
     */
    public boolean hasAlias(CommandAlias commandAlias) {
        requireNonNull(commandAlias);
        return hasAlias(commandAlias.getAlias());
    }

    /**
     * Adds a command alias to the map.
     * The alias must not already exist in the map.
     */
    public void addAlias(CommandAlias toAdd) {
        requireNonNull(toAdd);
        if (hasAlias(toAdd)) {
            throw new DuplicateAliasException();
        }
        aliasMap.put(toAdd.getAlias(), toAdd);
    }

    /**
     * Removes the equivalent alias from the map.
     * The alias must exist in the map.
     */
    public void removeAlias(Alias toRemove) {
        requireNonNull(toRemove);
        if (!hasAlias(toRemove)) {
            throw new AliasNotFoundException();
        }
        aliasMap.remove(toRemove);
    }

    /**
     * Returns command alias mapped to alias if alias is found in alias map.
     * If alias is not found, null is returned.
     *
     * @param alias alias to search in alias map.
     */
    public CommandAlias getCommandAlias(Alias alias) {
        return aliasMap.get(alias);
    }

    /**
     * Returns command mapped to alias if alias is found in alias map.
     * If alias is not found, null is returned.
     *
     * @param alias alias to search in alias map.
     */
    public Command getCommand(Alias alias) {
        return getCommandAlias(alias).getCommand();
    }

    @Override
    public String parseAliasToCommand(String userInput) {
        Alias alias;
        try {
            alias = ParserUtil.parseAlias(userInput);
        } catch (ParseException pe) {
            return userInput;
        }

        if (!hasAlias(alias)) {
            return userInput;
        }

        return getCommand(alias).toString();
    }

    @Override
    public String toString() {
        if (aliasMap.isEmpty()) {
            return EMPTY_ALIASES;
        }
        final StringBuilder builder = new StringBuilder();
        for (CommandAlias commandAlias: aliasMap.values()) {
            builder.append(commandAlias);
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAliasMap // instanceof handles nulls
                && aliasMap.equals(((UniqueAliasMap) other).aliasMap));
    }

    @Override
    public int hashCode() {
        return aliasMap.hashCode();
    }

}
