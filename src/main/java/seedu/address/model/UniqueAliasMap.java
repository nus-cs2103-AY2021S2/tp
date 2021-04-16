package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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

    private final ObservableMap<Alias, CommandAlias> internalMap = FXCollections.observableMap(new HashMap<>());
    private final ObservableMap<Alias, CommandAlias> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);

    public UniqueAliasMap() {}

    /**
     * Creates an UniqueAliasMap using the alias map in the {@code toBeCopied}
     */
    public UniqueAliasMap(ReadOnlyUniqueAliasMap toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the commandAliases with {@code commandAliases}.
     * {@code commandAliases} must not contain duplicate aliases.
     *
     * @throws DuplicateAliasException if there are duplicate aliases in {@code commandAliases}.
     */
    public void setCommandAliases(Map<Alias, CommandAlias> commandAliases) {
        requireAllNonNull(commandAliases);
        if (!commandAliasesAreUnique(commandAliases)) {
            throw new DuplicateAliasException();
        }
        internalMap.clear();
        internalMap.putAll(commandAliases);
    }

    /**
     * Resets the existing data of this {@code UniqueAliasMap} with {@code newData}.
     */
    public void resetData(ReadOnlyUniqueAliasMap newData) {
        requireNonNull(newData);

        setCommandAliases(newData.getCommandAliases());
    }

    /**
     * Returns true if the command aliases contains an equivalent alias as the given argument.
     */
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return internalMap.containsKey(alias);
    }

    /**
     * Returns true if the command aliases contains an equivalent command alias as the given argument.
     */
    public boolean hasCommandAlias(CommandAlias commandAlias) {
        requireNonNull(commandAlias);
        return hasAlias(commandAlias.getAlias());
    }

    /**
     * Adds a command alias to the command aliases.
     * The alias must not already exist in the command aliases.
     */
    public void addAlias(CommandAlias toAdd) {
        requireNonNull(toAdd);
        if (hasCommandAlias(toAdd)) {
            throw new DuplicateAliasException();
        }
        internalMap.put(toAdd.getAlias(), toAdd);
    }

    /**
     * Removes the equivalent alias from the command aliases.
     * The alias must exist in the command aliases.
     *
     * @throws AliasNotFoundException if the alias {@code toRemove} does not exist
     *     and not found in {@code command aliases}.
     */
    public void removeAlias(Alias toRemove) {
        requireNonNull(toRemove);
        if (!hasAlias(toRemove)) {
            throw new AliasNotFoundException();
        }
        internalMap.remove(toRemove);
    }

    /**
     * Returns command alias mapped to alias if alias is found in command aliases.
     * If alias is not found, null is returned.
     *
     * @param alias alias to search in command aliases.
     */
    public CommandAlias getCommandAlias(Alias alias) {
        return internalMap.get(alias);
    }

    /**
     * Returns command mapped to alias if alias is found in command aliases.
     * If alias is not found, null is returned.
     *
     * @param alias alias to search in command aliases.
     */
    public Command getCommand(Alias alias) {
        CommandAlias commandAlias = getCommandAlias(alias);

        if (commandAlias == null) {
            return null;
        }

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
    public ObservableMap<Alias, CommandAlias> getCommandAliases() {
        return internalUnmodifiableMap;
    }

    @Override
    public List<String> getCommandAliasesStringList() {
        return internalMap.values().stream().map(CommandAlias::toString).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public int getNumOfAlias() {
        return internalMap.size();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (CommandAlias commandAlias: internalMap.values()) {
            builder.append(commandAlias);
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAliasMap // instanceof handles nulls
                && internalMap.equals(((UniqueAliasMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    /**
     * Returns true if {@code commandAliases} contains only unique aliases.
     */
    private boolean commandAliasesAreUnique(Map<Alias, CommandAlias> commandAliases) {
        UniqueAliasMap checkDuplicate = new UniqueAliasMap();
        for (CommandAlias commandAlias: commandAliases.values()) {
            if (checkDuplicate.hasCommandAlias(commandAlias)) {
                return false;
            }
            checkDuplicate.addAlias(commandAlias);
        }
        return true;
    }

}
