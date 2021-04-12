package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTeachingAssistant;
import seedu.address.model.TeachingAssistant;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TeachingAssistant} with sample data.
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

    public static Entry[] getSampleEntries() {
        return new Entry[] {
          new Entry(new EntryName("Consultation with 2A"), new EntryDate("2021-05-04 13:00"),
                  new EntryDate("2021-05-04 15:00"), getTagSet("consultation")),
          new Entry(new EntryName("Department meeting"), new EntryDate("2021-05-05 16:00"),
                  new EntryDate("2021-05-05 17:00"), getTagSet("meeting", "needprep")),
          new Entry(new EntryName("CCA"), new EntryDate("2021-05-11 16:00"),
                   new EntryDate("2021-05-11 18:00"), getTagSet("cca")),
          new Entry(new EntryName("Leadership Camp"), new EntryDate("2021-05-15 07:00"),
                   new EntryDate("2021-05-16 18:00"), getTagSet("event")),
          new Entry(new EntryName("Sec 2 teachers meeting"), new EntryDate("2021-05-17 13:00"),
                   new EntryDate("2021-05-17 14:00"), getTagSet("meeting")),
          new Entry(new EntryName("Consultation with 2B"), new EntryDate("2021-05-18 13:00"),
                   new EntryDate("2021-05-18 15:00"), getTagSet("consultation"))
        };
    }

    public static ReadOnlyTeachingAssistant getSampleTeachingAssistant() {
        TeachingAssistant sampleAb = new TeachingAssistant();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        for (Entry sampleEntry : getSampleEntries()) {
            sampleAb.addEntry(sampleEntry);
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
