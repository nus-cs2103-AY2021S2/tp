package seedu.us.among.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Email;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.endpoint.Phone;
import seedu.us.among.model.tag.Tag;
import seedu.us.among.model.util.SampleDataUtil;

/**
 * A utility class to help with building Endpoint objects.
 */
public class EndpointBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code EndpointBuilder} with the default details.
     */
    public EndpointBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EndpointBuilder with the data of {@code endpointToCopy}.
     */
    public EndpointBuilder(Endpoint endpointToCopy) {
        name = endpointToCopy.getName();
        phone = endpointToCopy.getPhone();
        email = endpointToCopy.getEmail();
        address = endpointToCopy.getAddress();
        tags = new HashSet<>(endpointToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Endpoint} that we are building.
     */
    public EndpointBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Endpoint build() {
        return new Endpoint(name, phone, email, address, tags);
    }

}
