package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Address;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Name;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Favourite;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.TimeAdded;
import seedu.address.model.tag.ChildTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        // Creating tag sets for normal tags and appending child tags
        Set<Tag> alexTags = getTagSet("formTeacher", "teacher", "bishanPriSch");
        Set<Tag> berniceTags = getTagSet("teacher", "chinese", "kovanSecSch");
        Set<Tag> irfanTags = getTagSet("mathTuition", "teacher");
        Set<Tag> sharonTags = getTagSet("teacher", "balletSchool");

        alexTags.addAll(getChildTagSet("bob"));
        berniceTags.addAll(getChildTagSet("alice"));
        irfanTags.addAll(getChildTagSet("bob", "denise"));
        sharonTags.addAll(getChildTagSet("denise"));

        return new Contact[] {
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("elliesMom", "neighbour", "psg", "parent"),
                        new TimeAdded("2021-03-21 06:55:41.11"), new Favourite("false")),
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@bps.edu.sg"),
                new Address(), alexTags, new TimeAdded("2021-03-21 06:55:40.11"), new Favourite("true")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@kovansec.edu.sg"),
                new Address(), berniceTags, new TimeAdded("2021-03-21 06:55:43.11"),
                new Favourite("true")),
            new Contact(new Name("Annie Li"), new Phone("91031282"), new Email("liannie@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("carolsMom", "psg", "parent"), new TimeAdded("2021-03-21 06:55:42.11"),
                    new Favourite("false")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan1999@example.com"),
                new Address(), irfanTags, new TimeAdded("2021-03-21 06:55:45.11"), new Favourite("true")),
            new Contact(new Name("Sharon Lee"), new Phone("99272777"), new Email("sharon_lee@example.com"),
                    new Address(), sharonTags, new TimeAdded("2021-03-21 06:55:43.11"),
                    new Favourite("false")),
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

    /**
     * Returns a ChildTag set containing the list of strings given.
     */
    public static Set<Tag> getChildTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(ChildTag::new)
                .collect(Collectors.toSet());
    }


    public static Appointment[] getSampleAppointments() {
        Contact[] sampleContacts = getSampleContacts();

        return new Appointment[] {
            new Appointment(new Name("Parent teacher meeting"), new Address("Kovan Secondary School"),
                    new DateTime("21/05/2021 10:00"), getContactSet("0"),
                    getChildTagSet("bob")),
            new Appointment(new Name("Ballet recital"), new Address("Ballet school"),
                    new DateTime("02/10/2021 18:00"), getContactSet(),
                    getChildTagSet("denise")),
            new Appointment(new Name("Play date with Carol and Ellie"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new DateTime("17/04/2021 11:00"), getContactSet("2", "3"),
                    getChildTagSet("alice")),
            new Appointment(new Name("PSG meeting"), new Address("Bishan Primary School"),
                    new DateTime("15/04/2021 14:00"), getContactSet("3", "2"),
                    getChildTagSet())
        };
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    /**
     * Returns a contact set containing the list of strings given.
     */
    public static Set<Contact> getContactSet(String... strings) {

        Contact[] sampleContacts = getSampleContacts();
        Set<Contact> contacts = new HashSet<>();

        for (String index : strings) {
            Integer i = Integer.parseInt(index);

            contacts.add(sampleContacts[i]);
        }

        return contacts;
    }



}
