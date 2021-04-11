package seedu.flashback.testutil;

import seedu.flashback.model.FlashBack;
import seedu.flashback.model.flashcard.Flashcard;

/**
 * A utility class to help with building FlashBack objects.
 * Example usage: <br>
 *     {@code FlashBack fb = new FlashBackBuilder().withFlashcard(new Flashcard(new Question("Pythagorean theorem"),
 *     new Answer("a^2 + b^2 = c^2"), new Category("Maths"), new Priority("Low"))).build();}
 */
public class FlashBackBuilder {

    private FlashBack flashBack;

    public FlashBackBuilder() {
        flashBack = new FlashBack();
    }

    public FlashBackBuilder(FlashBack flashBack) {
        this.flashBack = flashBack;
    }

    /**
     * Adds a new {@code Flashcard} to {@code FlashBack} that we are building.
     */
    public FlashBackBuilder withFlashcard(Flashcard flashcard) {
        flashBack.addCard(flashcard);
        return this;
    }

    public FlashBack build() {
        return flashBack;
    }
}
