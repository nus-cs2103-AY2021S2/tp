package seedu.smartlib.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.tag.Tag;
import seedu.smartlib.model.util.SampleDataUtil;

/**
 * A utility class to help with building Reader objects.
 */
public class ReaderBuilder {

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
     * Creates a {@code ReaderBuilder} with the default details.
     */
    public ReaderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ReaderBuilder with the data of {@code readerToCopy}.
     */
    public ReaderBuilder(Reader readerToCopy) {
        name = readerToCopy.getName();
        phone = readerToCopy.getPhone();
        email = readerToCopy.getEmail();
        address = readerToCopy.getAddress();
        tags = new HashSet<>(readerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Reader} that we are building.
     */
    public ReaderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Reader} that we are building.
     */
    public ReaderBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Reader} that we are building.
     */
    public ReaderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Reader} that we are building.
     */
    public ReaderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Reader} that we are building.
     */
    public ReaderBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Reader build() {
        return new Reader(name, phone, email, address, tags, new HashMap<>());
    }

}
