package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.LastUse;
import seedu.address.model.garment.Size;
import seedu.address.model.garment.Type;

import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Garment objects.
 */
public class GarmentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SIZE = "25";
    public static final String DEFAULT_COLOUR = "blue";
    public static final String DEFAULT_DRESSCODE = "FORMAL";
    public static final String DEFAULT_TYPE = "upper";
    public static final String DEFAULT_LASTUSE = "2021-03-22";

    private Name name;
    private Size size;
    private Colour colour;
    private DressCode dresscode;
    private Type type;
    private Set<Description> descriptions;
    private LastUse lastUse;

    /**
     * Creates a {@code GarmentBuilder} with the default details.
     */
    public GarmentBuilder() {
        name = new Name(DEFAULT_NAME);
        size = new Size(DEFAULT_SIZE);
        colour = new Colour(DEFAULT_COLOUR);
        dresscode = new DressCode(DEFAULT_DRESSCODE);
        type = new Type(DEFAULT_TYPE);
        descriptions = new HashSet<>();
        lastUse = new LastUse(DEFAULT_LASTUSE);
    }

    /**
     * Initializes the GarmentBuilder with the data of {@code garmentToCopy}.
     */
    public GarmentBuilder(Garment garmentToCopy) {
        name = garmentToCopy.getName();
        size = garmentToCopy.getSize();
        colour = garmentToCopy.getColour();
        dresscode = garmentToCopy.getDressCode();
        type = garmentToCopy.getType();
        descriptions = new HashSet<>(garmentToCopy.getDescriptions());
        lastUse = garmentToCopy.getLastUse();
    }

    /**
     * Sets the {@code Name} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>}
     * and set it to the {@code Garment} that we are building.
     */
    public GarmentBuilder withDescriptions(String ... descriptions) {
        this.descriptions = SampleDataUtil.getDescriptionSet(descriptions);
        return this;
    }

    /**
     * Sets the {@code DressCode} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withDressCode(String dresscode) {
        this.dresscode = new DressCode(dresscode);
        return this;
    }

    /**
     * Sets the {@code Size} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withSize(String size) {
        this.size = new Size(size);
        return this;
    }

    /**
     * Sets the {@code Colour} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withColour(String colour) {
        this.colour = new Colour(colour);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code LastUse} of the {@code Garment} that we are building.
     */
    public GarmentBuilder withLastUse(String lastUse) {
        this.lastUse = new LastUse(lastUse);
        return this;
    }

    public Garment build() {
        return new Garment(name, size, colour, dresscode, type, descriptions, lastUse);
    }

}
