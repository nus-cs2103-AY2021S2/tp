package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.EINSTEIN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Priority;
import seedu.address.model.flashcard.Question;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_QUESTION = "";
    private static final String INVALID_ANSWER = "";
    private static final String INVALID_CATEGORY = " ";
    private static final String INVALID_PRIORITY = "";
    private static final String INVALID_TAG = "#Equation";

    private static final String VALID_QUESTION = EINSTEIN.getQuestion().toString();
    private static final String VALID_ANSWER = EINSTEIN.getAnswer().toString();
    private static final String VALID_CATEGORY = EINSTEIN.getCategory().toString();
    private static final String VALID_PRIORITY = EINSTEIN.getPriority().toString();
    private static final String VALID_REMARK = EINSTEIN.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = EINSTEIN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(EINSTEIN);
        assertEquals(EINSTEIN, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(INVALID_QUESTION, VALID_ANSWER,
                        VALID_CATEGORY, VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(null, VALID_ANSWER, VALID_CATEGORY,
                VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, INVALID_ANSWER, VALID_CATEGORY,
                        VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(VALID_QUESTION, null, VALID_CATEGORY,
                VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, INVALID_PRIORITY,
                        VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, null,
                VALID_PRIORITY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, VALID_CATEGORY,
                        INVALID_CATEGORY, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER,
                VALID_CATEGORY, null, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, VALID_CATEGORY,
                        VALID_PRIORITY, VALID_REMARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
