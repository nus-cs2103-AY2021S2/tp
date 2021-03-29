package seedu.weeblingo.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;

/**
 * An Immutable FlashcardBook that is serializable to JSON format.
 */
@JsonRootName(value = "flashcardbook")
class JsonSerializableFlashcardBook {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    public static final String MESSAGE_DUPLICATE_SCORE = "Score history list contains duplicate score(s).";

    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    private final List<JsonAdaptedScore> scores = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashcardBook} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashcardBook(@JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards,
                                         @JsonProperty("scores") List<JsonAdaptedScore> scores) {
        this.flashcards.addAll(flashcards);
        this.scores.addAll(scores);
    }

    /**
     * Converts a given {@code ReadOnlyFlashcardBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashcardBook}.
     */
    public JsonSerializableFlashcardBook(ReadOnlyFlashcardBook source) {
        flashcards.addAll(source.getFlashcardList().stream().map(
                JsonAdaptedFlashcard::new).collect(Collectors.toList()));
        scores.addAll(source.getScoreHistoryList().stream().map(
                JsonAdaptedScore::new).collect(Collectors.toList()));
    }

    /**
     * Converts this flashcard book into the model's {@code FlashcardBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashcardBook toModelType() throws IllegalValueException {
        FlashcardBook flashcardBook = new FlashcardBook();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashcardBook.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            flashcardBook.addFlashcard(flashcard);
        }

        for (JsonAdaptedScore jsonAdaptedScore : scores) {
            Score score = jsonAdaptedScore.toModelType();
            if (flashcardBook.hasScore(score)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCORE);
            }
            flashcardBook.addScore(score);
        }
        return flashcardBook;
    }
}
