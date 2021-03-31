package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGroupmates.ROXY;
import static seedu.address.testutil.TypicalGroupmates.SYLPH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalProjects.CS1101S_NAME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
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

        Event event = new Event("Test Event", LocalDate.of(2020, 1, 1),
                LocalTime.of(17, 30), false);
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

        ArrayList<Groupmate> groupmates = new ArrayList<>();
        groupmates.add(ROXY);
        GroupmateList groupmateList = new GroupmateList(groupmates);

        assertDoesNotThrow(() -> new Project(name, eventList, todoList, deadlineList, groupmateList));
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
    public void getGroupmate_validProject_success() {
        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(ROXY);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString())
                .withGroupmateList(groupmateList).build();
        assertEquals(project.getGroupmates().get(0), project.getGroupmate(0));
    }

    @Test
    public void addGroupmate_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getGroupmates().size());
        project.addGroupmate(SYLPH);
        assertEquals(1, project.getGroupmates().size());
        assertEquals(SYLPH, project.getGroupmates().getSortedGroupmates().get(0));
    }

    @Test
    public void hasGroupmate_success() {
        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(SYLPH);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString())
                .withGroupmateList(groupmateList).build();
        assertTrue(project.hasGroupmate(SYLPH));
        assertFalse(project.hasGroupmate(ROXY));
        assertFalse(new ProjectBuilder().withName(CS1101S_NAME.toString()).build().hasGroupmate(SYLPH));
    }

    @Test
    public void deleteGroupmate_success() {
        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(ROXY);
        groupmateList.addGroupmate(SYLPH);
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString())
                .withGroupmateList(groupmateList).build();
        int size = project.getGroupmates().size();
        assertEquals(project.getGroupmate(size - 1), SYLPH);
        assertEquals(project.getGroupmate(size - 2), ROXY);
        project.deleteGroupmate(size - 1);
        assertEquals(project.getGroupmates().size(), size - 1);
        assertEquals(project.getGroupmate(size - 2), ROXY);
        project.deleteGroupmate(size - 2);
        assertEquals(project.getGroupmates().size(), size - 2);
    }

    @Test void addDeadline_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getDeadlines().size());
        Deadline deadline = new Deadline("deadline", LocalDate.now());
        project.addDeadline(deadline);
        assertEquals(1, project.getDeadlines().size());
        assertEquals(deadline, project.getDeadlines().getSortedDeadlineList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test void addEvent_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getEvents().size());
        Event event = new Event("event", LocalDate.now(), LocalTime.now(), false);
        project.addEvent(event);
        assertEquals(1, project.getEvents().size());
        assertEquals(event, project.getEvents().getSortedEventList().get(INDEX_FIRST.getZeroBased()));
    }

    @Test void addTodo_success() {
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        assertEquals(0, project.getTodos().size());
        Todo todo = new Todo("deadline");
        project.addTodo(todo);
        assertEquals(1, project.getTodos().size());
        assertEquals(todo, project.getTodos().getSortedTodos().get(INDEX_FIRST.getZeroBased()));
    }

    @Test void deleteDeadline_success() {
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withDeadlineList(deadlineList).build();
        assertEquals(1, project.getDeadlines().size());
        project.deleteDeadline(INDEX_FIRST.getZeroBased());
        assertEquals(0, project.getDeadlines().size());
    }

    @Test void deleteEvent_success() {
        EventList eventList = new EventList();
        eventList.addEvent(new Event("event", LocalDate.now(), LocalTime.now(), false));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withEventList(eventList).build();
        assertEquals(1, project.getEvents().size());
        project.deleteEvent(INDEX_FIRST.getZeroBased());
        assertEquals(0, project.getEvents().size());
    }

    @Test void deleteTodo_success() {
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withTodoList(todoList).build();
        assertEquals(1, project.getTodos().size());
        project.deleteTodo(INDEX_FIRST.getZeroBased());
        assertEquals(0, project.getTodos().size());
    }

    @Test void markDeadline_success() {
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withDeadlineList(deadlineList).build();
        assertEquals(1, project.getDeadlines().size());
        assertEquals(false, project.getDeadlines().getSortedDeadlineList().get(0).getIsDone());
        project.markDeadline(INDEX_FIRST.getZeroBased());
        assertEquals(true, project.getDeadlines().getSortedDeadlineList().get(0).getIsDone());
    }

    @Test void markTodo_success() {
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        Project project = new ProjectBuilder().withName(CS1101S_NAME.toString()).withTodoList(todoList).build();
        assertEquals(1, project.getTodos().size());
        assertEquals(false, project.getTodos().getSortedTodos().get(0).getIsDone());
        project.markTodo(INDEX_FIRST.getZeroBased());
        assertEquals(true, project.getTodos().getSortedTodos().get(0).getIsDone());
    }

    @Test
    public void hashCode_success() {
        Project project1 = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        int hashcode1 = project1.hashCode();

        // invoked on the same object: _must_ be equal
        assertEquals(hashcode1, project1.hashCode());

        Project project2 = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();

        // objects are equal according to equals(): _must_ be equal
        assertEquals(hashcode1, project2.hashCode());

        project1.addTodo(new Todo("todo"));
        int hashcode3 = project1.hashCode();
        project1.addGroupmate(SYLPH);
        int hashcode4 = project1.hashCode();

        // objects are unequal according to equals(): _should_ be distinct
        assertNotEquals(hashcode1, hashcode3);
        assertNotEquals(hashcode1, hashcode4);
        assertNotEquals(hashcode3, hashcode4);
    }

    @Test void toString_success() {
        Project project1 = new ProjectBuilder().withName(CS1101S_NAME.toString()).build();
        String project1String = CS1101S_NAME.toString();
        assertEquals(project1String, project1.toString());
        EventList eventList = new EventList();
        eventList.addEvent(new Event("event", LocalDate.now(), LocalTime.now(), false));
        TodoList todoList = new TodoList();
        todoList.addTodo(new Todo("todo"));
        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(new Deadline("deadline", LocalDate.now()));
        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(SYLPH);
        Project project2 = new ProjectBuilder()
                .withName(CS1101S_NAME.toString())
                .withEventList(eventList)
                .withTodoList(todoList)
                .withDeadlineList(deadlineList)
                .withGroupmateList(groupmateList)
                .build();
        final StringBuilder builder = new StringBuilder();
        builder.append(CS1101S_NAME.toString());
        builder.append("; Events: ");
        builder.append(eventList.getSortedEventList().get(0).toString());
        builder.append("; Todos: ");
        builder.append(todoList.getSortedTodos().get(0).toString());
        builder.append("; Deadlines: ");
        builder.append(deadlineList.getSortedDeadlineList().get(0).toString());
        builder.append("; Groupmates: ");
        builder.append(groupmateList.get(0).toString());
        String project2String = builder.toString();
        assertEquals(project2String, project2.toString());
    }
}
