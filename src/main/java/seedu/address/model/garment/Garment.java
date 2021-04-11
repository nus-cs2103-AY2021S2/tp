package seedu.address.model.garment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.description.Description;

/**
 * Represents a Garment in the wardrobe.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Garment {
    public static final HashMap<String, HashMap<String, String>> SAMPLES = new HashMap<>();

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
        requireAllNonNull(name, size, colour, dresscode, type, descriptions);
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.dresscode = dresscode;
        this.type = type;
        this.descriptions.addAll(descriptions);
        this.lastuse = new LastUse("Never");
    }

    /**
     * To maintain LastUse when editing
     */
    public Garment(Name name, Size size, Colour colour, DressCode dresscode,
                   Type type, Set<Description> descriptions, LastUse lastUse) {
        requireAllNonNull(name, size, colour, dresscode, type, descriptions);
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.dresscode = dresscode;
        this.type = type;
        this.descriptions.addAll(descriptions);
        this.lastuse = lastUse;
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

    public Type getType() {
        return type;
    }

    public LastUse getLastUse() {
        return lastuse;
    }

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
     * Initialises the HashMap of colours and their respective sample images.
     */
    public static void initialiseSamples() {
        SAMPLES.put("red", new HashMap<>());
        SAMPLES.get("red").put("upper", "images/redSamples/upper.png");
        SAMPLES.get("red").put("lower", "images/redSamples/lower.png");
        SAMPLES.get("red").put("footwear", "images/redSamples/footwear.png");
        SAMPLES.put("orange", new HashMap<>());
        SAMPLES.get("orange").put("upper", "images/orangeSamples/upper.png");
        SAMPLES.get("orange").put("lower", "images/orangeSamples/lower.png");
        SAMPLES.get("orange").put("footwear", "images/orangeSamples/footwear.png");
        SAMPLES.put("yellow", new HashMap<>());
        SAMPLES.get("yellow").put("upper", "images/yellowSamples/upper.png");
        SAMPLES.get("yellow").put("lower", "images/yellowSamples/lower.png");
        SAMPLES.get("yellow").put("footwear", "images/yellowSamples/footwear.png");
        SAMPLES.put("green", new HashMap<>());
        SAMPLES.get("green").put("upper", "images/greenSamples/upper.png");
        SAMPLES.get("green").put("lower", "images/greenSamples/lower.png");
        SAMPLES.get("green").put("footwear", "images/greenSamples/footwear.png");
        SAMPLES.put("blue", new HashMap<>());
        SAMPLES.get("blue").put("upper", "images/blueSamples/upper.png");
        SAMPLES.get("blue").put("lower", "images/blueSamples/lower.png");
        SAMPLES.get("blue").put("footwear", "images/blueSamples/footwear.png");
        SAMPLES.put("pink", new HashMap<>());
        SAMPLES.get("pink").put("upper", "images/pinkSamples/upper.png");
        SAMPLES.get("pink").put("lower", "images/pinkSamples/lower.png");
        SAMPLES.get("pink").put("footwear", "images/pinkSamples/footwear.png");
        SAMPLES.put("purple", new HashMap<>());
        SAMPLES.get("purple").put("upper", "images/purpleSamples/upper.png");
        SAMPLES.get("purple").put("lower", "images/purpleSamples/lower.png");
        SAMPLES.get("purple").put("footwear", "images/purpleSamples/footwear.png");
        SAMPLES.put("brown", new HashMap<>());
        SAMPLES.get("brown").put("upper", "images/brownSamples/upper.png");
        SAMPLES.get("brown").put("lower", "images/brownSamples/lower.png");
        SAMPLES.get("brown").put("footwear", "images/brownSamples/footwear.png");
        SAMPLES.put("black", new HashMap<>());
        SAMPLES.get("black").put("upper", "images/blackSamples/upper.png");
        SAMPLES.get("black").put("lower", "images/blackSamples/lower.png");
        SAMPLES.get("black").put("footwear", "images/blackSamples/footwear.png");
        SAMPLES.put("white", new HashMap<>());
        SAMPLES.get("white").put("upper", "images/whiteSamples/upper.png");
        SAMPLES.get("white").put("lower", "images/whiteSamples/lower.png");
        SAMPLES.get("white").put("footwear", "images/whiteSamples/footwear.png");
        SAMPLES.put("grey", new HashMap<>());
        SAMPLES.get("grey").put("upper", "images/greySamples/upper.png");
        SAMPLES.get("grey").put("lower", "images/greySamples/lower.png");
        SAMPLES.get("grey").put("footwear", "images/greySamples/footwear.png");
        SAMPLES.put("beige", new HashMap<>());
        SAMPLES.get("beige").put("upper", "images/beigeSamples/upper.png");
        SAMPLES.get("beige").put("lower", "images/beigeSamples/lower.png");
        SAMPLES.get("beige").put("footwear", "images/beigeSamples/footwear.png");
        //Dresscode
        SAMPLES.put("DressCode", new HashMap<>());
        SAMPLES.get("DressCode").put("formal", "images/DressCode/formal3.png");
        SAMPLES.get("DressCode").put("casual", "images/DressCode/casual3.png");
        SAMPLES.get("DressCode").put("active", "images/DressCode/active3.png");
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
                && otherGarment.getDescriptions().equals(getDescriptions());
        //&& otherGarment.getLastUse().equals(getLastUse());
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
