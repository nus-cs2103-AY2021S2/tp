package seedu.iscam.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Image;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_LOCATION = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PLAN = "Plan A";
    public static final String DEFAULT_IMAGE = "default.png";

    private Name name;
    private Phone phone;
    private Email email;
    private Location location;
    private Set<InsurancePlan> insurancePlans;
    private Set<Tag> tags;
    private Image imageRef;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        location = new Location(DEFAULT_LOCATION);
        insurancePlans = new HashSet<>();
        tags = new HashSet<>();
        imageRef = new Image(DEFAULT_IMAGE);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        location = clientToCopy.getLocation();
        insurancePlans = clientToCopy.getPlans();
        tags = new HashSet<>(clientToCopy.getTags());
        imageRef = clientToCopy.getImageRes();
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
    public ClientBuilder withPlan(String ... plans) {
        this.insurancePlans = SampleDataUtil.getPlanSet();
        return this;
    }

    /**
     * Sets the {@code Image} of the {@code Client} that we are building.
     */
    public ClientBuilder withImage(String imageRef) {
        this.imageRef = new Image(imageRef);
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, location, insurancePlans, tags, imageRef);
    }

}
