package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

        // equal project name -> returns true
        assertTrue(TEST_PROJECT_ONE.isSameProject(new ProjectBuilder().withName("Test One").build()));
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

        // same project name, default for other fields -> returns true
        assertTrue(TEST_PROJECT_ONE.equals(new ProjectBuilder().withName("Test One").build()));

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
        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(ALICE);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withParticipantList(participantList).build();
        assertEquals(project.getParticipants().get(0), project.getParticipant(0));
    }

    @Test
    public void addParticipant_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getParticipants().size());
        project.addParticipant(ALICE);
        assertEquals(1, project.getParticipants().size());
        assertEquals(ALICE, project.getParticipants().getParticipants().get(0));
    }

    @Test
    public void hasParticipant_success() {
        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(ALICE);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withParticipantList(participantList).build();
        assertTrue(project.hasParticipant(ALICE));
        assertFalse(project.hasParticipant(BOB));
        assertFalse(new ProjectBuilder().withName(CS1101S_NAME.toString()).build().hasParticipant(ALICE));
    }

    @Test
    public void deleteParticipant_success() {
        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(ALICE);
        participantList.addParticipant(BOB);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withParticipantList(participantList).build();
        int size = project.getParticipants().size();
        assertEquals(project.getParticipant(size - 1), BOB);
        assertEquals(project.getParticipant(size - 2), ALICE);
        project.deleteParticipant(size - 1);
        assertEquals(project.getParticipants().size(), size - 1);
        assertEquals(project.getParticipant(size - 2), ALICE);
        project.deleteParticipant(size - 2);
        assertEquals(project.getParticipants().size(), size - 2);
    }

    @Test void addDeadline_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getDeadlines().getDeadlines().size());
        Deadline deadline = new Deadline("deadline", LocalDate.now());
        project.addDeadline(deadline);
        assertEquals(1, project.getDeadlines().getDeadlines().size());
        assertEquals(deadline, project.getDeadlines().getDeadlines().get(0));
    }

    @Test void addEvent_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getEvents().getEvents().size());
        Event event = new Event("event", Interval.NONE, LocalDate.now());
        project.addEvent(event);
        assertEquals(1, project.getEvents().getEvents().size());
        assertEquals(event, project.getEvents().getEvents().get(0));
    }

    @Test void addTodo_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getTodos().getTodos().size());
        Todo todo = new Todo("deadline");
        project.addTodo(todo);
        assertEquals(1, project.getTodos().getTodos().size());
        assertEquals(todo, project.getTodos().getTodos().get(0));
    }

    @Test void deleteDeadline_success() {
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withDeadlineList(deadlineList).build();
        assertEquals(1, project.getDeadlines().getDeadlines().size());
        project.deleteDeadline(0);
        assertEquals(0, project.getDeadlines().getDeadlines().size());
    }

    @Test void deleteEvent_success() {
        EventList eventList = new EventList();
        eventList.addEvent(new Event("event", Interval.NONE, LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withEventList(eventList).build();
        assertEquals(1, project.getEvents().getEvents().size());
        project.deleteEvent(0);
        assertEquals(0, project.getEvents().getEvents().size());
    }

    @Test void deleteTodo_success() {
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withTodoList(todoList).build();
        assertEquals(1, project.getTodos().getTodos().size());
        project.deleteTodo(0);
        assertEquals(0, project.getTodos().getTodos().size());
    }

    @Test void markDeadline_success() {
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withDeadlineList(deadlineList).build();
        assertEquals(1, project.getDeadlines().getDeadlines().size());
        assertEquals(false, project.getDeadlines().getDeadlines().get(0).getIsDone());
        project.markDeadline(0);
        assertEquals(true, project.getDeadlines().getDeadlines().get(0).getIsDone());
    }

    @Test void markEvent_success() {
        EventList eventList = new EventList();
        eventList.addEvent(new Event("event", Interval.NONE, LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withEventList(eventList).build();
        assertEquals(1, project.getEvents().getEvents().size());
        assertEquals(false, project.getEvents().getEvents().get(0).getIsDone());
        project.markEvent(0);
        assertEquals(true, project.getEvents().getEvents().get(0).getIsDone());
    }

    @Test void markTodo_success() {
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withTodoList(todoList).build();
        assertEquals(1, project.getTodos().getTodos().size());
        assertEquals(false, project.getTodos().getTodos().get(0).getIsDone());
        project.markTodo(0);
        assertEquals(true, project.getTodos().getTodos().get(0).getIsDone());
    }

    @Test
    public void hashCode_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        int hashcode1 = project.hashCode();
        project.addParticipant(ALICE);
        int hashcode2 = project.hashCode();
        project.addTodo(new Todo("todo"));
        int hashcode3 = project.hashCode();
        assertNotEquals(hashcode1, hashcode2);
        assertNotEquals(hashcode1, hashcode3);
        assertNotEquals(hashcode2, hashcode3);
    }

    @Test void toString_success() {
        Project project1 = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        String project1String = CS1101S_NAME.toString();
        assertEquals(project1String, project1.toString());
        EventList eventList = new EventList();
        eventList.addEvent(new Event("event", Interval.NONE, LocalDate.now()));
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(ALICE);
        Project project2 = new ProjectBuilder()
                .withName(CS1101S_NAME.toString())
                .withEventList(eventList)
                .withTodoList(todoList)
                .withDeadlineList(deadlineList)
                .withParticipantList(participantList)
                .build();
        final StringBuilder builder = new StringBuilder();
        builder.append(CS1101S_NAME.toString());
        builder.append("; Events: ");
        builder.append(eventList.getEvents().get(0).toString());
        builder.append("; Todos: ");
        builder.append(todoList.getTodos().get(0).toString());
        builder.append("; Deadlines: ");
        builder.append(deadlineList.getDeadlines().get(0).toString());
        builder.append("; Participants: ");
        builder.append(participantList.get(0).toString());
        String project2String = builder.toString();
        assertEquals(project2String, project2.toString());
    }
}
