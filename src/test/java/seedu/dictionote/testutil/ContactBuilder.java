package seedu.dictionote.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.dictionote.model.contact.Address;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.Email;
import seedu.dictionote.model.contact.Name;
import seedu.dictionote.model.contact.Phone;
import seedu.dictionote.model.tag.Tag;
import seedu.dictionote.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {

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
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        address = contactToCopy.getAddress();
        tags = new HashSet<>(contactToCopy.getTags());
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
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
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

    public Contact build() {
        return new Contact(name, phone, email, address, tags);
    }

}
