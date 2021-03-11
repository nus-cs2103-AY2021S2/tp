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
@JsonRootName(value = "addressbook")
class JsonSerializableFlashBack {

    public static final String MESSAGE_DUPLICATE_CARD = "Flash cards list contains duplicate card(s).";

    private final List<JsonAdaptedFlashcard> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashBack} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashBack(@JsonProperty("persons") List<JsonAdaptedFlashcard> flashcards) {
        this.persons.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyFlashBack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashBack}.
     */
    public JsonSerializableFlashBack(ReadOnlyFlashBack source) {
        persons.addAll(source.getCardList().stream().map(JsonAdaptedFlashcard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashBack toModelType() throws IllegalValueException {
        FlashBack flashBack = new FlashBack();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : persons) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashBack.hasCard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CARD);
            }
            flashBack.addCard(flashcard);
        }
        return flashBack;
    }

}
