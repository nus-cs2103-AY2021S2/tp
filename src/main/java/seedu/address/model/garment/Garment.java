package seedu.address.model.garment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.description.Description;

/**
 * Represents a Garment in the wardrobe.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Garment {

    // Identity fields
    private final Name name;
    private final Size size;
    private final Colour colour;
    private final Type type;

    // Data fields
    private final DressCode dresscode;
    private final Set<Description> descriptions = new HashSet<>();
    private LastUse lastuse;

    /**
     * Every field must be present and not null.
     */
    public Garment(Name name, Size size, Colour colour, DressCode dresscode, Type type, Set<Description> descriptions) {
        requireAllNonNull(name, size, colour, dresscode, descriptions);
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.dresscode = dresscode;
        this.type = type;
        this.descriptions.addAll(descriptions);
        this.lastuse = new LastUse(LocalDate.now());
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

    public Type getType(){ return type;}

    public LastUse getLastUse(){ return lastuse;}

    /**
     * Returns an immutable description set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Description> getDescriptions() {
        return Collections.unmodifiableSet(descriptions);
    }

    /**
     * Returns true if both garments have the same name.
     * This defines a weaker notion of equality between two garments.
     */
    public boolean isSameGarment(Garment otherGarment) {
        if (otherGarment == this) {
            return true;
        }

        return otherGarment != null
                && otherGarment.getName().equals(getName());
    }

    /**
     * Returns true if both garments have the same identity and data fields.
     * This defines a stronger notion of equality between two garments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Garment)) {
            return false;
        }

        Garment otherGarment = (Garment) other;
        return otherGarment.getName().equals(getName())
                && otherGarment.getSize().equals(getSize())
                && otherGarment.getColour().equals(getColour())
                && otherGarment.getDressCode().equals(getDressCode())
                && otherGarment.getType().equals(getType())
                && otherGarment.getDescriptions().equals(getDescriptions())
                && otherGarment.getLastUse().equals(getLastUse());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, size, colour, dresscode, type, descriptions, lastuse);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Size: ")
                .append(getSize())
                .append("; Colour: ")
                .append(getColour())
                .append("; DressCode: ")
                .append(getDressCode())
                .append("; Type: ")
                .append(getType());

        Set<Description> descriptions = getDescriptions();
        if (!descriptions.isEmpty()) {
            builder.append("; Descriptions: ");
            descriptions.forEach(builder::append);
        }

        builder.append("; Last Used: ")
                .append(getLastUse());

        return builder.toString();
    }

}
