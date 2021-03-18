package seedu.partyplanet.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. '-t' in 'add James -t friend'.
 */
public class Prefix {

    private static final Set<String> allPrefixes = new HashSet<>();
    private final String representativePrefix;
    private final HashSet<String> prefixes;

    /**
     * Empty constructor for placeholder prefixes.
     * Used for placeholders, when a generic prefix is needed.
     */
    public Prefix() {
        this.representativePrefix = "";
        this.prefixes = new HashSet<>();
    }

    /**
     * Basic Prefix constructor.
     * Should never be initialized by user / user routine, due to checks on prefix duplicates
     * for developer safety. Where a generic Prefix is required, use the empty constructor {@code Prefix()} instead.
     *
     * @param prefix The prefix string representing the Prefix
     * @param altPrefixes Array of alternate prefix aliases
     */
    public Prefix(String prefix, String... altPrefixes) {
        requireNonNull(prefix);
        assert !prefix.equals(""); // ensure default value of empty constructor never initialized
        this.representativePrefix = prefix;
        this.prefixes = new HashSet<>(Arrays.asList(altPrefixes));
        this.prefixes.add(prefix);
        assertNoDuplicatePrefixes(this.prefixes);
    }

    /**
     * Raises runtime assertion if initialization of prefixes contain duplicates, for developer safety.
     *
     * @param prefixes Set of new prefix strings.
     */
    private static void assertNoDuplicatePrefixes(Set<String> prefixes) {
        int previousSize = allPrefixes.size();
        allPrefixes.addAll(prefixes);
        assert allPrefixes.size() == previousSize + prefixes.size();
    }

    /**
     * Returns the representative prefix representing the set of prefixes.
     */
    public String getPrefix() {
        return representativePrefix;
    }

    /**
     * Returns all equivalent forms of the same prefix.
     */
    public Set<String> getPrefixes() {
        return this.prefixes;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return representativePrefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
