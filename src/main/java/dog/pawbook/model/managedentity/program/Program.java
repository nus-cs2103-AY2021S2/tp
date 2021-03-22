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
    //private final int programID;
    private final Set<Integer> dogidSet = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Program(Name name, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(name, tags);
        // this.programID = programID;
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor.
     */
    public Program(Name name, Set<Tag> tags, Set<Integer> dogIDs) {
        super(name, tags);
        requireAllNonNull(name, tags);
        this.dogidSet.addAll(dogIDs);
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
        return super.equals(other);
        /* && otherDog.getBreed().equals(getBreed())
            && otherDog.getSex().equals(getSex())
            && otherDog.getDob().equals(getDob());*/
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        /* .append("; Breed: ")
            .append(getBreed())
            .append("; Date Of Birth: ")
            .append(getDob())
            .append("; Sex: ")
            .append(getSex())
            .append("; OwnerID: ")
            .append(getOwnerId());*/

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
        return new String[]{};
    }

}
