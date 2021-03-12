package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FlashBack;
import seedu.address.model.ReadOnlyFlashBack;
import seedu.address.model.flashcard.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashBack} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY = new Remark("");
    public static Flashcard[] getSampleCards() {
        return new Flashcard[] {
            new Flashcard(new Question("What is the formula for hydrochloric acid?"),
                    new Answer("HCl"),
                    new Category("chemistry 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("formula", "chemistry")),
            new Flashcard(new Question("What is Newton's Second Law of Motion?"),
                    new Answer("Force = Mass * Acceleration"),
                    new Category("physics 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("mechanics", "physics")),
            new Flashcard(new Question("What is ATP?"),
                    new Answer("Adenosine Triphosphate"),
                    new Category("biology 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("biology")),
            new Flashcard(new Question("What is the time complexity of merge sort"),
                    new Answer("O(nlogn)"),
                    new Category("computer science"),
                    new Priority("High"),
                    EMPTY,
                    getTagSet("sorting", "runtime")),
            new Flashcard(new Question("When did Charles Darwin stop believing in Christianity?"),
                    new Answer("After the Voyage of the Beagle"),
                    new Category("history"),
                    new Priority("Low"),
                    EMPTY,
                    getTagSet("Darwinian")),
            new Flashcard(new Question("What is recursion?"),
                    new Answer("recursion"),
                    new Category("computer science"),
                    new Priority("Low"),
                    EMPTY,
                    getTagSet("random"))
        };
    }

    public static ReadOnlyFlashBack getSampleFlashBack() {
        FlashBack sampleAb = new FlashBack();
        for (Flashcard sampleFlashcard : getSampleCards()) {
            sampleAb.addCard(sampleFlashcard);
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
