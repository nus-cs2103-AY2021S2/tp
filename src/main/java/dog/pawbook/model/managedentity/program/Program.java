package dog.pawbook.model.managedentity.program;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a program in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Program extends Entity {
    public static final String ENTITY_WORD = "program";

    // Data fields
    private final Set<DateOfProgram> dateSet = new HashSet<>();
    private final Set<Integer> dogidSet = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Program(Name name, Set<DateOfProgram> dateSet, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(name, tags);
        this.dateSet.addAll(dateSet);
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor.
     */
    public Program(Name name, Set<DateOfProgram> dateSet, Set<Tag> tags, Set<Integer> dogIDs) {
        super(name, tags);
        requireAllNonNull(name, tags);
        this.dogidSet.addAll(dogIDs);
        this.dateSet.addAll(dateSet);
    }



    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Integer> getDogIdSet() {
        return Collections.unmodifiableSet(dogidSet);
    }

    public Set<DateOfProgram> getDateSet() {
        return Collections.unmodifiableSet(dateSet);
    }

    /**
     * Returns true if both owners have the same identity and data fields.
     * This defines a stronger notion of equality between two owners.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Program)) {
            return false;
        }

        Program otherProgram = (Program) other;
        return super.equals(other)
            && otherProgram.getDateSet().equals(getDateSet())
            && otherProgram.getDogIdSet().equals(getDogIdSet());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dateSet, dogidSet, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        Set<DateOfProgram> dates = getDateSet();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(builder::append);
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public void addDogId(int dogId) {
        this.dogidSet.add(dogId);
    }

    @Override
    public String[] getOtherPropertiesAsString() {
        return new String[]{getDateSet().toString()};
    }

}
