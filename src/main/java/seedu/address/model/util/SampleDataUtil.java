package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Weeblingo} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Question("alexyeoh@example.com"),
                new Answer("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Flashcard(new Question("berniceyu@example.com"),
                new Answer("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Flashcard(new Question("charlotte@example.com"),
                new Answer("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Flashcard(new Question("lidavid@example.com"),
                new Answer("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Flashcard(new Question("irfan@example.com"),
                new Answer("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Flashcard(new Question("royb@example.com"),
                new Answer("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
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
