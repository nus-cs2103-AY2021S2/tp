package seedu.address.model.date;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class ImportantDate {

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
