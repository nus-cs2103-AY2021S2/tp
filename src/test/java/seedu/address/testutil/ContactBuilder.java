package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building contact objects.
 */
public class ContactBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private ContactName name;
    private ContactPhone contactPhone;
    private ContactEmail email;
    private Set<Tag> tags;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new ContactName(DEFAULT_NAME);
        contactPhone = new ContactPhone(DEFAULT_PHONE);
        email = new ContactEmail(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        contactPhone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        tags = new HashSet<>(contactToCopy.getTags());
    }

    /**
     * Sets the contact's name as the given string.
     */
    public ContactBuilder withName(String name) {
        this.name = new ContactName(name);
        return this;
    }

    /**
     * Sets the contact's phone as the given string.
     */
    public ContactBuilder withPhone(String phone) {
        this.contactPhone = new ContactPhone(phone);
        return this;
    }

    /**
     * Sets the contact's email as the given string.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new ContactEmail(email);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and sets it as the contact's tags.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Contact build() {
        return new Contact(name, contactPhone, email, tags);
    }

}

