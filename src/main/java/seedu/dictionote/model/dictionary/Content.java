package seedu.dictionote.model.dictionary;

import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Note in the dictionote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Content implements DisplayableContent {
    private final String week;
    private final String header;
    private final String maincontent;

    /**
     * Every field must be present and not null.
     */
    public Content(String week, String header, String maincontent) {
        requireAllNonNull(week, header, maincontent);
        this.week = week;
        this.header = header;
        this.maincontent = maincontent;
    }

    public String getMainContent() {
        return maincontent;
    }

    public String getWeek() {
        return week;
    }

    public String getHeader() {
        return header;
    }

    /**
     * Returns true if both notes have the same note.
     */
    public boolean isSameContent(Content otherContent) {
        if (otherContent == this) {
            return true;
        }

        return otherContent != null
                && otherContent.getWeek().equals(getWeek())
                && otherContent.getHeader().equals(getHeader())
                && otherContent.getMainContent().equals(getMainContent());
    }

    /**
     * Returns true if both notes have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Content)) {
            return false;
        }

        Content otherContent = (Content) other;
        return otherContent.getWeek().equals(getWeek())
                && otherContent.getHeader().equals(getHeader())
                && otherContent.getMainContent().equals(getMainContent());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(week, header, maincontent);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getWeek())
            .append(getHeader())
            .append(getMainContent());
        return builder.toString();
    }

    @Override
    public String getDictionaryWeek() {
        return getWeek();
    }

    @Override
    public String getDictionaryHeader() {
        return getHeader();
    }

    @Override
    public String getDictionaryContent() {
        return getMainContent();
    }
}
