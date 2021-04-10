package seedu.address.model.date;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class ImportantDate implements Comparable<ImportantDate> {

    private final Description description;
    private final Details details;

    /**
     * Constructs a {@code ImportantDate}.
     *
     * @param description Description of the important date.
     * @param details Details of the important date.
     */
    public ImportantDate(Description description, Details details) {
        requireAllNonNull(description, details);
        this.description = description;
        this.details = details;
    }

    public Description getDescription() {
        return description;
    }

    public Details getDetails() {
        return details;
    }

    @Override
    public int compareTo(ImportantDate other) {
        return this.details.compareTo(other.details);
    }

    /**
     * Returns true if both important dates have the same description.
     * This defines a weaker notion of equality between two important dates.
     */
    public boolean isSameImportantDate(ImportantDate otherImportantDate) {
        if (otherImportantDate == this) {
            return true;
        }

        return otherImportantDate != null
            && otherImportantDate.getDescription().equals(getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportantDate)) {
            return false;
        }

        ImportantDate otherImportantDate = (ImportantDate) other;
        return otherImportantDate.getDescription().equals(getDescription())
                && otherImportantDate.getDetails().equals(getDetails());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, details);
    }

    @Override
    public String toString() {
        return getDetails() + ": " + getDescription();
    }


}
