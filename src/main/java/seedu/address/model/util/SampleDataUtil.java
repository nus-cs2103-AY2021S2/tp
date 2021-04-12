package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.insurance.InsurancePlan;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("M"), new Birthdate("1992-05-06"),
                getTagSet("investment"))
                    .addPlan(new InsurancePlan("Guaranteed Protect Plus $4000.80"))
                    .setMeeting(Optional.of(new Meeting("Retirement Planning @ 2022-10-25 15:00"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("F"), new Birthdate("1995-12-24"),
                getTagSet("life", "medical"))
                .addNote(new Note("Received huge inheritance, wants to invest"))
                .setMeeting(Optional.of(new Meeting("Investment Crash Course @ 2021-05-15 18:00"))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("F"), new Birthdate("1990-01-06"),
                getTagSet("life"))
                .addPlan(new InsurancePlan("Covid-19 Protection $1000")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("M"), new Birthdate("1975-07-09"),
                getTagSet("investment")),
            new Person(new Name("Jeff Liu"), new Phone("92624417"), new Email("jeffliu@example.com"),
                new Address("10 Buangkok View, Buangkok Green"), new Gender("M"), new Birthdate("1985-06-30"),
                getTagSet("life"))
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

    /**
     * Returns a list of insurance plans containing the plans given.
     */
    public static List<InsurancePlan> getPlanList(String... strings) {
        return Arrays.stream(strings)
                .map(InsurancePlan::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of notes.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toList());
    }

}
