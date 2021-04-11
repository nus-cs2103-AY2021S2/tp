package seedu.module.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.module.commons.core.optionalfield.OptionalField;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    public int getPrefixesSize() {
        return argMultimap.keySet().size();
    }

    public Set<Prefix> getPrefixes() {
        return argMultimap.keySet();
    }

    public boolean contains(Prefix prefix) {
        return argMultimap.containsKey(prefix);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns the last value of {@code prefix} with wrapper.
     *
     * @param prefix requested.
     * @return the last value of {@code prefix} with wrapper.
     */
    public OptionalField<String> getLastValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty()
                ? new OptionalField<>(null)
                : new OptionalField<>(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns true if all the existing prefixes are without extra parameters.
     */
    public boolean arePrefixesNotWithParameter() {
        boolean isPrefixNotWithParameter = true;
        for (Prefix prefix : this.getPrefixes()) {
            isPrefixNotWithParameter = isPrefixNotWithParameter && this.getAllValues(prefix).get(0).isEmpty();
        }
        return isPrefixNotWithParameter;
    }

    /**
     * Because of the implementation inheriting from AB3, the prefixes will contain "", so here 2 means "" and the only
     * existing prefix.
     *
     * @return true if the command only has one prefix.
     */
    public boolean areLessThanTwoPrefixes() {
        return this.getPrefixesSize() <= 2;
    }
}
