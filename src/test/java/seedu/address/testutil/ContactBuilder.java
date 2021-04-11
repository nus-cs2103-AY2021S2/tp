package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Favourite;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.TimeAdded;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TIME_ADDED = "2021-03-21 06:55:40.11";
    public static final String DEFAULT_FAVOURITE = "false";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private TimeAdded timeAdded;
    private Favourite favourite;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        timeAdded = new TimeAdded(DEFAULT_TIME_ADDED);
        favourite = new Favourite(DEFAULT_FAVOURITE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        address = contactToCopy.getAddress();
        tags = new HashSet<>(contactToCopy.getTags());
        timeAdded = new TimeAdded(contactToCopy.getTimeAdded().toString());
        favourite = new Favourite(contactToCopy.getFavourite().toString());
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Contact} that we are building.
     */
    public ContactBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code TimeAdded} of the {@code Contact} that we are building.
     */
    public ContactBuilder withTimeAdded(String timeAdded) {
        this.timeAdded = new TimeAdded(timeAdded);
        return this;
    }

    /**
     * Sets the {@code Favourite} of the {@code Contact} that we are building.
     */
    public ContactBuilder withFavourite(String favourite) {
        this.favourite = new Favourite(favourite);
        return this;
    }

    public Contact build() {
        return new Contact(name, phone, email, address, tags, timeAdded, favourite);
    }

}
