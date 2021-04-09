package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new DateOfBirth(LocalDate.of(2000, 1, 20)),
                    new Gender("M"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new BloodType("AB+"), new Height("175cm"), new Weight("69kg"),
                    getTagSet("bronchitis", "lung cancer")),
            new Patient(new Name("Bernice Yu"), new DateOfBirth(LocalDate.of(1995, 10, 10)),
                    new Gender("F"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new BloodType("A+"), new Height("168cm"), new Weight("50kg"),
                    getTagSet("asthma")),
            new Patient(new Name("Charlotte Oliveiro"), new DateOfBirth(LocalDate.of(1998, 5, 4)),
                    new Gender("F"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new BloodType("AB-"), new Height("170cm"), new Weight("53kg"),
                    getTagSet("fever")),
            new Patient(new Name("David Li"), new DateOfBirth(LocalDate.of(1986, 5, 10)),
                    new Gender("M"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new BloodType("O-"), new Height("163cm"), new Weight("63kg"),
                    getTagSet("lung cancer")),
            new Patient(new Name("Irfan Ibrahim"), new DateOfBirth(LocalDate.of(1989, 2, 15)),
                    new Gender("M"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new BloodType("AB+"), new Height("179cm"), new Weight("66kg"),
                    getTagSet("nut allergy")),
            new Patient(new Name("Roy Balakrishnan"), new DateOfBirth(LocalDate.of(2001, 12, 10)),
                    new Gender("M"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new BloodType("B-"), new Height("172cm"), new Weight("58kg"),
                    getTagSet("lactose intolerant"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
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
