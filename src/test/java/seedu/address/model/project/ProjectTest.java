package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalProjects.CS1101S_NAME;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.Interval;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.ProjectBuilder;

public class ProjectTest {

    private static final Project TEST_PROJECT_ONE = new ProjectBuilder().withName("Test One").build();
    private static final Project TEST_PROJECT_TWO = new ProjectBuilder().withName("Test Two").build();

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(TEST_PROJECT_ONE.isSameProject(TEST_PROJECT_ONE));

        // null -> returns false
        assertFalse(TEST_PROJECT_ONE.isSameProject(null));
    }

    @Test
    public void constructor_emptyProjectWithName_isValid() {
        ProjectName name = new ProjectName("Test Project");
        assertDoesNotThrow(() -> new Project(name));
    }

    @Test
    public void constructor_projectWithParameters_isValid() {
        ProjectName name = new ProjectName("Test Project");

        Event event = new Event("Test Event", Interval.NONE, LocalDate.of(2020, 1, 1));
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        EventList eventList = new EventList(events);

        Todo todo = new Todo("Test Description");
        ArrayList<CompletableTodo> todos = new ArrayList<>();
        todos.add(todo);
        TodoList todoList = new TodoList(todos);

        LocalDate validDate = LocalDate.of(2020, 1, 1);
        Deadline deadline = new Deadline("Test Description", validDate);
        ArrayList<CompletableDeadline> deadlines = new ArrayList<>();
        deadlines.add(deadline);
        DeadlineList deadlineList = new DeadlineList(deadlines);

        ArrayList<Person> participants = new ArrayList<>();
        participants.add(ALICE);
        ParticipantList participantList = new ParticipantList(participants);

        assertDoesNotThrow(() -> new Project(name, eventList, todoList, deadlineList, participantList));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(TEST_PROJECT_ONE.equals(TEST_PROJECT_ONE));

        // null -> returns false
        assertFalse(TEST_PROJECT_ONE.equals(null));

        // different type -> returns false
        assertFalse(TEST_PROJECT_ONE.equals(5));

        // different project -> returns false
        assertFalse(TEST_PROJECT_ONE.equals(TEST_PROJECT_TWO));
    }

    @Test
    public void getProjectName_validProject_success() {
        assertEquals(new ProjectBuilder().withName(CS1101S_NAME.toString()).build().getProjectName(), CS1101S_NAME);
    }

    @Test
    public void getParticipant_validProject_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        project.addParticipant(ALICE);
        project.addParticipant(ALICE);
        assertEquals(project.getParticipants().get(0), project.getParticipant(0));
    }

    @Test
    public void deleteParticipant_validProject_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        project.addParticipant(ALICE);
        project.addParticipant(BOB);
        int size = project.getParticipants().size();
        assertEquals(project.getParticipant(size - 1), BOB);
        assertEquals(project.getParticipant(size - 2), ALICE);
        project.deleteParticipant(size - 1);
        assertEquals(project.getParticipants().size(), size - 1);
        assertEquals(project.getParticipant(size - 2), ALICE);
        project.deleteParticipant(size - 2);
        assertEquals(project.getParticipants().size(), size - 2);
    }
}
