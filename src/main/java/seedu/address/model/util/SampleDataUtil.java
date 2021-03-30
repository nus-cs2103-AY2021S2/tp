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
                    new Category("Chemistry 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("formula", "chemistry"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("What is Newton's Second Law of Motion?"),
                    new Answer("Force = Mass * Acceleration"),
                    new Category("Physics 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("mechanics", "physics"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("What is ATP?"),
                    new Answer("Adenosine Triphosphate"),
                    new Category("Biology 101"),
                    new Priority("Mid"),
                    EMPTY,
                    getTagSet("biology"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("What is the time complexity of merge sort?"),
                    new Answer("O(nlogn)"),
                    new Category("Computer Science"),
                    new Priority("High"),
                    EMPTY,
                    getTagSet("sorting", "runtime"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("When did Charles Darwin stop believing in Christianity?"),
                    new Answer("After the Voyage of the Beagle"),
                    new Category("History"),
                    new Priority("Low"),
                    EMPTY,
                    getTagSet("Darwinian"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("What is recursion?"),
                    new Answer("recursion"),
                    new Category("Computer Science"),
                    new Priority("Low"),
                    EMPTY,
                    getTagSet("random"),
                    new Statistics(0, 0)),
            new Flashcard(new Question("What is the author of Romeo and Juliet?"),
                    new Answer("William Shakespeare"),
                    new Category("Literature"),
                    new Priority("High"),
                    EMPTY,
                    getTagSet("tragedy"),
                    new Statistics(0, 0))
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
