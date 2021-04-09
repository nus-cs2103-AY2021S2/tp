package seedu.weeblingo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;
import static seedu.weeblingo.testutil.TypicalFlashcards.I_CARD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Question;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_QUESTION = "???";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_QUESTION = I_CARD.getQuestion().toString();
    private static final String VALID_ANSWER = I_CARD.getAnswer().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = I_CARD.getWeeblingoTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> EMPTY_USER_TAGS = Collections.emptyList();

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(A_CARD);
        assertEquals(A_CARD, flashcard.toModelType());
    }


    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(INVALID_QUESTION, VALID_ANSWER, VALID_TAGS, EMPTY_USER_TAGS);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_ANSWER, VALID_TAGS, EMPTY_USER_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_QUESTION, INVALID_ANSWER, VALID_TAGS, EMPTY_USER_TAGS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_QUESTION, null, VALID_TAGS, EMPTY_USER_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, invalidTags, EMPTY_USER_TAGS);
        assertThrows(IllegalValueException.class, flashcard::toModelType);
    }

}
