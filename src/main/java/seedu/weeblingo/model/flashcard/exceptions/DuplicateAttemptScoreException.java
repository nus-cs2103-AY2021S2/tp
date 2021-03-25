package seedu.weeblingo.model.flashcard.exceptions;

/**
 * Signals that the operation will result in duplicate Scores (attempt scores)
 * (Attempt Scores are considered duplicates if they have the same creation datetime).
 */
public class DuplicateAttemptScoreException extends RuntimeException {
    public DuplicateAttemptScoreException() {
        super("There are duplicate attempts");
    }
}
