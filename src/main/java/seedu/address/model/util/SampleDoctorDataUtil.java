package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Doctor;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDoctorDataUtil {
    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
                new Doctor(new Name("Alex Yeoh"), getTagSet("medicine")),
                new Doctor(new Name("Bernice Yu"), getTagSet("pharmacist", "medicine")),
                new Doctor(new Name("Charlotte Oliveiro"), getTagSet("drug")),
                new Doctor(new Name("David Li"), getTagSet("physician")),
                new Doctor(new Name("Irfan Ibrahim"), getTagSet("mental")),
                new Doctor(new Name("Roy Balakrishnan"), getTagSet("stomach"))
        };
    }

    public static ReadOnlyAddressBook<Doctor> getSampleDoctorRecords() {
        AddressBook<Doctor> sampleDoctorRecords = new AddressBook<>();
        for (Doctor samplePerson : getSampleDoctors()) {
            sampleDoctorRecords.addPerson(samplePerson);
        }
        return sampleDoctorRecords;
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
