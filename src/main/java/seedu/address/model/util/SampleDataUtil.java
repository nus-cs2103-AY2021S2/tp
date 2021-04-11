package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new ContactName("Alex Yeoh"), new ContactPhone("87438807"),
                    new ContactEmail("alexyeoh@example.com"), getTagSet("friends")),
            new Contact(new ContactName("Bernice Yu"), new ContactPhone("99272758"),
                    new ContactEmail("berniceyu@example.com"), getTagSet("colleagues", "friends")),
            new Contact(new ContactName("Charlotte Oliveiro"), new ContactPhone("93210283"),
                    new ContactEmail("charlotte@example.com"), getTagSet("neighbours")),
            new Contact(new ContactName("David Li"), new ContactPhone("91031282"),
                    new ContactEmail("lidavid@example.com"), getTagSet("family")),
            new Contact(new ContactName("Irfan Ibrahim"), new ContactPhone("92492021"),
                    new ContactEmail("irfan@example.com"), getTagSet("classmates")),
            new Contact(new ContactName("Roy Balakrishnan"), new ContactPhone("92624417"),
                    new ContactEmail("royb@example.com"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
