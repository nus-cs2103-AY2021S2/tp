package seedu.smartlib.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SmartLib} with sample data.
 */
public class SampleDataUtil {
    public static Reader[] getSampleReaders() {
        return new Reader[] {
            new Reader(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Reader(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Reader(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Reader(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Reader(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Reader(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Record[] getSampleRecords() {
        return new Record[] {
            new Record(new Name("Cloud Atlas"), new Name("Alex Yeoh"), new DateBorrowed("2020-11-23")),
            new Record(new Name("The Avengers"), new Name("Bernice Yu"), new DateBorrowed("2021-01-22"))
        };
    }

    public static ReadOnlySmartLib getSampleSmartLib() {
        SmartLib sampleAb = new SmartLib();
        for (Reader sampleReader : getSampleReaders()) {
            sampleAb.addReader(sampleReader);
        }
        for (Record sampleRecord : getSampleRecords()) {
            sampleAb.addRecord(sampleRecord);
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
