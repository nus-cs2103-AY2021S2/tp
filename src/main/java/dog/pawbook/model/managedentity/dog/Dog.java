package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a dog in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Dog extends Entity {
    public static final String ENTITY_WORD = "dog";

    //Identity Fields
    private final Breed breed;
    private final DateOfBirth dob;
    private final Sex sex;

    // Data fields
    private final Integer ownerID;

    /**
     * Every field must be present and not null.
     */
    public Dog(Name name, Breed breed, DateOfBirth dob, Sex sex, Integer ownerID, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(name, breed, dob, sex, ownerID, tags);
        this.breed = breed;
        this.dob = dob;
        this.sex = sex;
        this.ownerID = ownerID;
    }

    public Name getName() {
        return name;
    }

    public Breed getBreed() {
        return breed;
    }


    public Sex getSex() {
        return sex;
    }

    public DateOfBirth getDob() {
        return dob;
    }

    public Integer getOwnerId() {
        return ownerID;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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

        if (!(other instanceof Dog)) {
            return false;
        }

        Dog otherDog = (Dog) other;
        return super.equals(other)
                && otherDog.getBreed().equals(getBreed())
                && otherDog.getSex().equals(getSex())
                && otherDog.getDob().equals(getDob())
                && otherDog.getOwnerId().equals(getOwnerId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, breed, dob, sex, ownerID, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Breed: ")
                .append(getBreed())
                .append("; Date Of Birth: ")
                .append(getDob())
                .append("; Sex: ")
                .append(getSex())
                .append("; OwnerID: ")
                .append(getOwnerId());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Returns an array of IDs that are closely related to the entity.
     */
    @Override
    public Collection<Integer> getRelatedEntityIds() {
        return Collections.singletonList(ownerID);
    }

    @Override
    public String[] getOtherPropertiesAsString() {
        return new String[] {breed.value, dob.value, sex.value, "Owner ID: " + ownerID};
    }
}
