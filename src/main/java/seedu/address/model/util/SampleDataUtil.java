package seedu.address.model.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Debt;
import seedu.address.model.person.Email;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Goal.Frequency;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Picture;
import seedu.address.model.person.SpecialDate;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Birthday("01-01-1998"), new Goal(Frequency.NONE),
                        new Address("Blk 30 Geylang Street 29, #06-40"), getPicture(), getDebt(),
                        getTagSet("friends"), getDateList(), getMeetingList()),
                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Birthday("01-02-1999"), new Goal(Frequency.NONE),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getPicture(), getDebt(),
                        getTagSet("colleagues", "friends"), getDateList(), getMeetingList()),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Birthday("03-11-1979"), new Goal(Frequency.NONE),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getPicture(), getDebt(),
                        getTagSet("neighbours"), getDateList(), getMeetingList()),
                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Birthday("14-12-1998"), new Goal(Frequency.NONE),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getPicture(), getDebt(),
                        getTagSet("family"), getDateList(), getMeetingList()),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Birthday("12-12-1995"), new Goal(Frequency.NONE),
                        new Address("Blk 47 Tampines Street 20, #17-35"), getPicture(), getDebt(),
                        getTagSet("classmates"), getDateList(), getMeetingList()),
                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Birthday("05-02-1998"), new Goal(Frequency.NONE),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), getPicture(), getDebt(),
                        getTagSet("colleagues"), getDateList(), getMeetingList())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    public static Set<Name> getPersonSet(Name... personNames) {
        return Arrays.stream(personNames)
                .collect(Collectors.toSet());
    }

    public static Set<Group> getGroupSet(Group... groups) {
        return Arrays.stream(groups)
                .collect(Collectors.toSet());
    }

    public static Picture getPicture() {
        return null;
    }

    public static Debt getDebt() {
        return new Debt("0");
    }

    public static List<Meeting> getMeetingList() {
        return Collections.emptyList();
    }

    public static List<Meeting> getMeetingList(Meeting... meetings) {
        return Arrays.stream(meetings)
                .collect(Collectors.toList());
    }

    public static List<SpecialDate> getDateList() {
        return Collections.emptyList();
    }

    public static List<SpecialDate> getDateList(SpecialDate... dates) {
        return Arrays.stream(dates)
                .collect(Collectors.toList());
    }

}
