package seedu.weeblingo.storage;

import static seedu.weeblingo.testutil.TypicalFlashcards.BENSON;

import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_QUESTION = "how do you spell elephant?";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_QUESTION = BENSON.getQuestion().toString();
    private static final String VALID_ANSWER = BENSON.getAnswer().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

//    @Test
//    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(BENSON);
//        assertEquals(BENSON, flashcard.toModelType());
//    }


//    @Test
//    public void toModelType_invalidQuestion_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//                new JsonAdaptedFlashcard(INVALID_QUESTION, VALID_ANSWER, VALID_TAGS);
//        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
//    }

//    @Test
//    public void toModelType_nullQuestion_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_ANSWER, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
//    }

//    @Test
//    public void toModelType_invalidAnswer_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//                new JsonAdaptedFlashcard(VALID_QUESTION, INVALID_ANSWER, VALID_TAGS);
//        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
//    }

//    @Test
//    public void toModelType_nullAnswer_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_QUESTION, null, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
//    }

//    @Test
//    public void toModelType_invalidTags_throwsIllegalValueException() {
//        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
//        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
//        JsonAdaptedFlashcard flashcard =
//                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, invalidTags);
//        assertThrows(IllegalValueException.class, flashcard::toModelType);
//    }

}
