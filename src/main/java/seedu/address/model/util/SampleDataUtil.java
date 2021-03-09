package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FlashBack;
import seedu.address.model.ReadOnlyFlashBack;
import seedu.address.model.flashcard.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY = new Remark("");
    public static Flashcard[] getSamplePersons() {
        return new Flashcard[] {
            new Flashcard(new Question("Alex Yeoh"), new Answer("87438807"), new Category("alexyeoh@example.com"),
                new Priority("Blk 30 Geylang Street 29, #06-40"), EMPTY,
                getTagSet("friends")),
            new Flashcard(new Question("Bernice Yu"), new Answer("99272758"), new Category("berniceyu@example.com"),
                new Priority("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY,
                getTagSet("colleagues", "friends")),
            new Flashcard(new Question("Charlotte Oliveiro"), new Answer("93210283"), new Category("charlotte@example.com"),
                new Priority("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY,
                getTagSet("neighbours")),
            new Flashcard(new Question("David Li"), new Answer("91031282"), new Category("lidavid@example.com"),
                new Priority("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY,
                getTagSet("family")),
            new Flashcard(new Question("Irfan Ibrahim"), new Answer("92492021"), new Category("irfan@example.com"),
                new Priority("Blk 47 Tampines Street 20, #17-35"), EMPTY,
                getTagSet("classmates")),
            new Flashcard(new Question("Roy Balakrishnan"), new Answer("92624417"), new Category("royb@example.com"),
                new Priority("Blk 45 Aljunied Street 85, #11-31"), EMPTY,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyFlashBack getSampleAddressBook() {
        FlashBack sampleAb = new FlashBack();
        for (Flashcard sampleFlashcard : getSamplePersons()) {
            sampleAb.addPerson(sampleFlashcard);
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
