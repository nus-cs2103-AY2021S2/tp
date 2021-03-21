package seedu.partyplanet.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.ReadOnlyEventBook;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.person.Remark;
import seedu.partyplanet.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Birthday("1995-02-01"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Remark("hates vegetables"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Birthday("1997-03-07"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Remark("likes red"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Birthday("1998-05-20"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Remark("hates pink"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Birthday("1999-11-21"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Remark("loves meat"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Birthday("2000-08-23"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Remark("hates blue"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Birthday("2002-10-27"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Remark("hates black"), getTagSet("colleagues"))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Jan celebration"), new EventDate("2020-01-01"), new Remark("10 people")),
            new Event(new Name("CNY celebration"), new EventDate("2020-02-01"), new Remark("get pineapple tarts")),
            new Event(new Name("Feb celebration"), new EventDate("2020-02-01"), new Remark("2 people")),
            new Event(new Name("March celebration"), new EventDate("2020-03-01"), new Remark("do outside school")),
            new Event(new Name("Christmas celebration"), new EventDate("2020-12-01"), new Remark("get turkey")),
            new Event(new Name("Graduation party"), new EventDate("2022-05-31"), new Remark("Get job"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEventBook getSampleEventBook() {
        EventBook sampleEb = new EventBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleEb.addEvent(sampleEvent);
        }
        return sampleEb;
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
