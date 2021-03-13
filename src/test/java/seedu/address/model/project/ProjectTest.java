package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.CS1101S;
import static seedu.address.testutil.TypicalProjects.CS2103T_PROJECT;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.Interval;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.todo.Todo;
import seedu.address.model.task.repeatable.Event;

public class ProjectTest {

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(CS1101S.isSameProject(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.isSameProject(null));
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
        assertTrue(CS1101S.equals(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.equals(null));

        // different type -> returns false
        assertFalse(CS1101S.equals(5));

        // different project -> returns false
        assertFalse(CS1101S.equals(CS2103T_PROJECT));
    }
}
