package seedu.weeblingo.model.flashcard.exceptions;

/**
 * Signals that the operation will result in duplicate Flashcards
 * (Flashcards are considered duplicates if they have the same identity).
 */
public class DuplicateFlashcardException extends RuntimeException {
    public DuplicateFlashcardException() {
        super("Operation would result in duplicate persons");
    }
}
