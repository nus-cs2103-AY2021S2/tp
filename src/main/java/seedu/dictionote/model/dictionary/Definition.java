package seedu.dictionote.model.dictionary;

import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a DefinitionBook in the dictionote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Definition implements DisplayableContent {
    private final String term;
    private final String defs;

    /**
     * Every field must be present and not null.
     */
    public Definition(String term, String defs) {
        requireAllNonNull(term, defs);
        this.term = term;
        this.defs = defs;
    }

    public String getTerm() {
        return term;
    }

    public String getDefs() {
        return defs;
    }

    /**
     * Returns true if both definitions have the same content.
     */
    public boolean isSameDefinition(Definition otherDefinition) {
        if (otherDefinition == this) {
            return true;
        }

        return otherDefinition != null
                && otherDefinition.getDefs().equals(getDefs());
    }

    /**
     * Returns true if both definition have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Definition)) {
            return false;
        }

        Definition otherDefinition = (Definition) other;
        return otherDefinition.getTerm().equals(getTerm())
                && otherDefinition.getDefs().equals(getDefs());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(term, defs);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTerm() + ": ")
                .append(getDefs());
        return builder.toString();
    }


    @Override
    public String getDictionaryWeek() {
        return null;
    }

    @Override
    public String getDictionaryHeader() {
        return getTerm();
    }

    @Override
    public String getDictionaryContent() {
        return getDefs();
    }
}
