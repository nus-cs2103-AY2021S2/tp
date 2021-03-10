package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Colour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SIZE = "25";
    public static final String DEFAULT_COLOUR = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Size size;
    private Colour colour;
    private Address address;
    private Set<Description> descriptions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        size = new Size(DEFAULT_SIZE);
        colour = new Colour(DEFAULT_COLOUR);
        address = new Address(DEFAULT_ADDRESS);
        descriptions = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        size = personToCopy.getSize();
        colour = personToCopy.getColour();
        address = personToCopy.getAddress();
        descriptions = new HashSet<>(personToCopy.getDescriptions());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withDescriptions(String ... descriptions) {
        this.descriptions = SampleDataUtil.getDescriptionSet(descriptions);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withSize(String size) {
        this.size = new Size(size);
        return this;
    }

    /**
     * Sets the {@code Colour} of the {@code Person} that we are building.
     */
    public PersonBuilder withColour(String colour) {
        this.colour = new Colour(colour);
        return this;
    }

    public Person build() {
        return new Person(name, size, colour, address, descriptions);
    }

}
