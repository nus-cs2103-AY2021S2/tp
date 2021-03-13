package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.description.Description;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Size size;
    private final Colour colour;

    // Data fields
    private final DressCode dresscode;
    private final Set<Description> descriptions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Size size, Colour colour, DressCode dresscode, Set<Description> descriptions) {
        requireAllNonNull(name, size, colour, dresscode, descriptions);
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.dresscode = dresscode;
        this.descriptions.addAll(descriptions);
    }

    public Name getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public Colour getColour() {
        return colour;
    }

    public DressCode getDressCode() {
        return dresscode;
    }

    /**
     * Returns an immutable description set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Description> getDescriptions() {
        return Collections.unmodifiableSet(descriptions);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getSize().equals(getSize())
                && otherPerson.getColour().equals(getEmail())
                && otherPerson.getDressCode().equals(getDressCode())
                && otherPerson.getDescriptions().equals(getDescriptions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, size, colour, dresscode, descriptions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Size: ")
                .append(getSize())
                .append("; Colour: ")
                .append(getColour())
                .append("; Address: ")
                .append(getDressCode());

        Set<Description> descriptions = getDescriptions();
        if (!descriptions.isEmpty()) {
            builder.append("; Descriptions: ");
            descriptions.forEach(builder::append);
        }
        return builder.toString();
    }

}
