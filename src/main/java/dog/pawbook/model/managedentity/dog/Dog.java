package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

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
    private final Integer ownerID;

    // Data fields
    private final Breed breed;
    private final DateOfBirth dob;
    private final Sex sex;

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

    @Override
    public boolean isSameAs(Entity otherEntity) {
        if (otherEntity == this) {
            return true;
        }

        if (!(otherEntity instanceof Dog)) {
            return false;
        }

        Dog otherDog = (Dog) otherEntity;

        return super.isSameAs(otherEntity)
                && otherDog.getOwnerId().equals(getOwnerId());
    }

    /**
     * Returns true if both dogs have the same identity and data fields.
     * This defines a stronger notion of equality between two dogs.
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
    public Collection<String> getOtherPropertiesAsString() {
        Collection<String> properties = new Vector<>();
        properties.add("Breed: " + breed.value);
        properties.add("Date of Birth: " + dob.value);
        properties.add("Sex: " + sex.value);
        properties.add("Owner's ID: " + ownerID);
        return properties;
    }
}
