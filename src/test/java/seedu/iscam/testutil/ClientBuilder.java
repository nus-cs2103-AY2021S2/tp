package seedu.iscam.testutil;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.InsurancePlan;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
=======
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Location;
import seedu.iscam.model.client.Name;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.tag.Tag;
import seedu.iscam.model.util.SampleDataUtil;
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PLAN = "Plan A";
=======
    public static final String DEFAULT_LOCATION = "123, Jurong West Ave 6, #08-111";
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java

    private Name name;
    private Phone phone;
    private Email email;
<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
    private Address address;
    private InsurancePlan insurancePlan;
=======
    private Location location;
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java
    private Set<Tag> tags;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
        address = new Address(DEFAULT_ADDRESS);
        insurancePlan = new InsurancePlan(DEFAULT_PLAN);
=======
        location = new Location(DEFAULT_LOCATION);
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java
        tags = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
        address = clientToCopy.getAddress();
        insurancePlan = clientToCopy.getPlan();
=======
        location = clientToCopy.getLocation();
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Client} that we are building.
     */
    public ClientBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code InsurancePlan} of the {@code Client} that we are building.
     */
    public ClientBuilder withPlan(String plan) {
        this.insurancePlan = new InsurancePlan(plan);
        return this;
    }

    public Client build() {
<<<<<<< HEAD:src/test/java/seedu/address/testutil/ClientBuilder.java
        return new Client(name, phone, email, address, insurancePlan, tags);
=======
        return new Client(name, phone, email, location, tags);
>>>>>>> c68b40a391d7a044c4bfc4801e7a8f7461c1f96a:src/test/java/seedu/iscam/testutil/ClientBuilder.java
    }

}
