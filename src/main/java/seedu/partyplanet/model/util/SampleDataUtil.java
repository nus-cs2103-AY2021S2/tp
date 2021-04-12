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
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.remark.Remark;
import seedu.partyplanet.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Birthday("1995-02-01"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Remark("hates vegetables"), getTagSet("choir", "dance", "year3")),
            new Person(new Name("Bernice Yu"), Phone.EMPTY_PHONE, new Email("berniceyu@example.com"),
                    new Birthday("1997-03-07"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Remark("likes red"), getTagSet("choir", "year4")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Birthday("1998-05-20"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Remark("hates pink"), getTagSet("choir", "year2")),
            new Person(new Name("David Li"), new Phone("91031282"), Email.EMPTY_EMAIL,
                    new Birthday("1998-01-13"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Remark("loves meat"), getTagSet("choir")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Birthday("2000-05-23"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Remark("hates blue"), getTagSet("dance", "year4")),
            new Person(new Name("John Trump"), new Phone("92624417"), new Email("jtrump@example.com"),
                    Birthday.EMPTY_BIRTHDAY, new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Remark("hates black"), getTagSet("dance", "year3"))
        };
    }


    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Jan celebration"), new EventDate("2021-01-01"), new Remark("10 people")),
            new Event(new Name("CNY celebration"), new EventDate("2021-02-12"), new Remark("get pineapple tarts")),
            new Event(new Name("Feb celebration"), new EventDate("2021-02-01"), new Remark("2 people"))
                    .setDone(),
            new Event(new Name("March celebration"), new EventDate("2021-03-01"), new Remark("do outside school")),
            new Event(new Name("May celebrations"), new EventDate("2021-05-01"), new Remark("4 people")),
            new Event(new Name("Christmas celebration"), new EventDate("2021-12-01"), new Remark("get turkey"))
                    .setDone(),
            new Event(new Name("Graduation party"), new EventDate("2021-05-31"), new Remark("Get job")),
            new Event(new Name("End of tP"), new EventDate("2021-04-31"), Remark.EMPTY_REMARK)
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
