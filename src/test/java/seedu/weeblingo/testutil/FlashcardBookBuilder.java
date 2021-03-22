package seedu.weeblingo.testutil;

import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * A utility class to help with building Flashcard Book objects.
 * Example usage: <br>
 *     {@code FlashcardBook ab = new FlashcardBookBuilder().withPerson("John", "Doe").build();}
 */
public class FlashcardBookBuilder {

    private FlashcardBook flashcardBook;

    public FlashcardBookBuilder() {
        flashcardBook = new FlashcardBook();
    }

    public FlashcardBookBuilder(FlashcardBook flashcardBook) {
        this.flashcardBook = flashcardBook;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code FlashcardBook} that we are building.
     */
    public FlashcardBookBuilder withFlashcard(Flashcard flashcard) {
        flashcardBook.addFlashcard(flashcard);
        return this;
    }

    public FlashcardBook build() {
        return flashcardBook;
    }
}
