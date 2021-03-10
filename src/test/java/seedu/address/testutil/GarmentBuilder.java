package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Garment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Size;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class GarmentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SIZE = "25";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Size size;
    private Email email;
    private Address address;
    private Set<Description> descriptions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public GarmentBuilder() {
        name = new Name(DEFAULT_NAME);
        size = new Size(DEFAULT_SIZE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        descriptions = new HashSet<>();
    }

    /**
     * Initializes the GarmentBuilder with the data of {@code garmentToCopy}.
     */
    public GarmentBuilder(Garment garmentToCopy) {
        name = garmentToCopy.getName();
        size = garmentToCopy.getSize();
        email = garmentToCopy.getEmail();
        address = garmentToCopy.getAddress();
        descriptions = new HashSet<>(garmentToCopy.getDescriptions());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public GarmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>}
     * and set it to the {@code Person} that we are building.
     */
    public GarmentBuilder withDescriptions(String ... descriptions) {
        this.descriptions = SampleDataUtil.getDescriptionSet(descriptions);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public GarmentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Size} of the {@code Person} that we are building.
     */
    public GarmentBuilder withSize(String size) {
        this.size = new Size(size);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public GarmentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Garment build() {
        return new Garment(name, size, email, address, descriptions);
    }

}
