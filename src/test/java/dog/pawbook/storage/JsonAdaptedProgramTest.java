package dog.pawbook.storage;

import static dog.pawbook.storage.JsonAdaptedOwner.MISSING_FIELD_MESSAGE_FORMAT;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.INDEPENDENCE_TRAINING;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import org.junit.jupiter.api.Test;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import javafx.util.Pair;

public class JsonAdaptedProgramTest {
    private static final String INVALID_NAME = "@bedience Training`";
    private static final String INVALID_SESSION = "111-12";
    private static final String INVALID_DOGID = "  ";
    private static final String INVALID_TAG = "#friend";

    private static final Integer INDEPENDENCE_TRAINING_ID = 2;
    private static final String VALID_NAME = INDEPENDENCE_TRAINING.getName().toString();
    private static final List<JsonAdaptedSession> VALID_SESSIONS = INDEPENDENCE_TRAINING.getSessions().stream()
        .map(JsonAdaptedSession::new)
        .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = INDEPENDENCE_TRAINING.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    private static final List<Integer> VALID_DOG_IDS = new ArrayList<>(INDEPENDENCE_TRAINING.getDogIdSet());

    @Test
    public void toModelType_validProgramDetails_returnsProgram() throws Exception {
        Pair<Integer, Program> original = new Pair<>(INDEPENDENCE_TRAINING_ID, INDEPENDENCE_TRAINING);
        JsonAdaptedProgram program = new JsonAdaptedProgram(original);
        assertEquals(original, program.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProgram program = new JsonAdaptedProgram(INDEPENDENCE_TRAINING_ID, INVALID_NAME, VALID_SESSIONS,
            VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, program::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProgram program = new JsonAdaptedProgram(INDEPENDENCE_TRAINING_ID, null, VALID_SESSIONS,
            VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, program::toModelType);
    }

    @Test
    public void toModelType_invalidSessions_throwsIllegalValueException() {
        List<JsonAdaptedSession> invalidSessions = new ArrayList<>(VALID_SESSIONS);
        invalidSessions.add(new JsonAdaptedSession(INVALID_SESSION));
        JsonAdaptedProgram program = new JsonAdaptedProgram(INDEPENDENCE_TRAINING_ID, VALID_NAME, invalidSessions,
            VALID_TAGS, VALID_DOG_IDS);
        String expectedMessage = Session.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, program::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProgram program = new JsonAdaptedProgram(INDEPENDENCE_TRAINING_ID, VALID_NAME,
            VALID_SESSIONS, invalidTags, VALID_DOG_IDS);
        assertThrows(IllegalValueException.class, program::toModelType);
    }

}
