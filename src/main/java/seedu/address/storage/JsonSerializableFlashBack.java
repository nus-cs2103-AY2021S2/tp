package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FlashBack;
import seedu.address.model.ReadOnlyFlashBack;
import seedu.address.model.flashcard.Flashcard;

/**
 * An Immutable FlashBack that is serializable to JSON format.
 */
@JsonRootName(value = "flashback")
class JsonSerializableFlashBack {

    public static final String MESSAGE_DUPLICATE_CARD = "Flash cards list contains duplicate card(s).";

    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashBack} with the given flash card.
     */
    @JsonCreator
    public JsonSerializableFlashBack(@JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyFlashBack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashBack}.
     */
    public JsonSerializableFlashBack(ReadOnlyFlashBack source) {
        flashcards.addAll(source.getCardList().stream()
                .map(JsonAdaptedFlashcard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this flashback into the model's {@code FlashBack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashBack toModelType() throws IllegalValueException {
        FlashBack flashBack = new FlashBack();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashBack.hasCard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CARD);
            }
            flashBack.addCard(flashcard);
        }
        return flashBack;
    }

}
