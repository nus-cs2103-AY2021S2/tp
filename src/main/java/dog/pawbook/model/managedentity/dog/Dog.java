package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private final int ownerId;

    // Data fields
    private final Breed breed;
    private final DateOfBirth dob;
    private final Sex sex;

    /**
     * Every field must be present and not null.
     */
    public Dog(Name name, Breed breed, DateOfBirth dob, Sex sex, Integer ownerId, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(name, breed, dob, sex, ownerId, tags);
        this.breed = breed;
        this.dob = dob;
        this.sex = sex;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
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
                && otherDog.getOwnerId() == getOwnerId();
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
                && otherDog.getOwnerId() == getOwnerId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, breed, dob, sex, ownerId, tags);
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

    @Override
    public List<Integer> getRelatedEntityIds() {
        return Collections.singletonList(ownerId);
    }

    @Override
    public List<String> getOtherPropertiesAsString() {
        List<String> properties = new ArrayList<>();
        properties.add("Breed: " + breed.value);
        properties.add("Date of Birth: " + dob.value);
        properties.add("Sex: " + sex.value);
        properties.add("Owner's ID: " + ownerId);
        return properties;
    }
}
