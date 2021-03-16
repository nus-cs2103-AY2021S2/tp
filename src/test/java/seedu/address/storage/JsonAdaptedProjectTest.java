package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.testutil.ProjectBuilder;

public class JsonAdaptedProjectTest {
    private static final String INVALID_DESCRIPTION = " ";

    private static final Project VALID_PROJECT = new ProjectBuilder().build();

    private static final String VALID_DESCRIPTION = VALID_PROJECT.getProjectName().toString();
    private static final List<JsonAdaptedEvent> VALID_EVENT_LIST = VALID_PROJECT.getEvents().stream()
            .map(JsonAdaptedEvent::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedDeadline> VALID_DEADLINE_LIST = VALID_PROJECT.getDeadlines().stream()
            .map(JsonAdaptedDeadline::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTodo> VALID_TODO_LIST = VALID_PROJECT.getTodos().stream()
            .map(JsonAdaptedTodo::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPerson> VALID_PARTICIPANT_LIST = VALID_PROJECT.getParticipants().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT);
        assertEquals(VALID_PROJECT, project.toModelType());
    }

    @Test
    public void toModelType_validParameters_success() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_DESCRIPTION, VALID_EVENT_LIST, VALID_TODO_LIST,
                        VALID_DEADLINE_LIST, VALID_PARTICIPANT_LIST);
        assertDoesNotThrow(project::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_DESCRIPTION, VALID_EVENT_LIST, VALID_TODO_LIST,
                        VALID_DEADLINE_LIST, VALID_PARTICIPANT_LIST);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_EVENT_LIST, VALID_TODO_LIST,
                VALID_DEADLINE_LIST, VALID_PARTICIPANT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }
}
