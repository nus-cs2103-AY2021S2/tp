package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedInsurancePolicy;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                Arrays.asList(new InsurancePolicy("POL_123")),
                Arrays.asList(new Meeting("20.02.2021", "15:00", "18:00", "MRT"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                Arrays.asList(new InsurancePolicy("POL#9999999999")),
                Arrays.asList(new Meeting("15.03.2021", "20:30", "22:30", "Mall"))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                Arrays.asList(new InsurancePolicy("POL_456")),
                Arrays.asList(new Meeting("30.06.2021", "14:00", "15:00", "Address"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"),
                Arrays.asList(new InsurancePolicy("#12345", "www.policycompany.com/#12345"),
                                new InsurancePolicy("#67890", "www.policycompany.com/#67890")),
                Arrays.asList(new Meeting("25.04.2021", "12:30", "15:30", "Address"))),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                Arrays.asList(new InsurancePolicy("POL_789")),
                Arrays.asList(new Meeting("03.05.2021", "15:00", "18:00", "MRT"),
                                new Meeting("08.05.2021", "12:00", "14:00", "MRT"))),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                Arrays.asList(new InsurancePolicy("POL#111111111")),
                Arrays.asList(new Meeting("28.02.2021", "09:00", "11:00", "Mall")))
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
     * Returns a list of {@code InsurancePolicy} from the list of strings given.
     */
    public static List<InsurancePolicy> getPolicyList(String... strings) {
        return Arrays.stream(strings)
                .map(policyString -> {
                    String[] policyIdAndUrl = JsonAdaptedInsurancePolicy.policyIdAndUrlParser(policyString);
                    return new InsurancePolicy(policyIdAndUrl[0], policyIdAndUrl[1]);
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of {@code Meeting} from the list of strings given.
     */
    public static List<Meeting> getMeetingList(String... strings) {
        return Arrays.stream(strings)
                .map(Meeting::newMeeting)
                .collect(Collectors.toList());
    }
}
