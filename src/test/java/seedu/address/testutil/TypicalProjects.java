package seedu.address.testutil;

import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.commons.util.TimeUtil.encodeTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.TimeConversionException;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.todo.Todo;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {
    public static final ProjectName CS1101S_NAME = new ProjectName("CS1101S");

    public static final Boolean DONE = true;
    public static final Boolean NOT_DONE = false;

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns a list of typical projects.
     *
     * @return {@code List<Project>} containing typical projects.
     */
    public static List<Project> getTypicalProjects() {
        try {
            return new ArrayList<>(Arrays.asList(getCS2103TProject(), getCS2101Project()));
        } catch (DateConversionException | TimeConversionException e) {
            throw new AssertionError("Unreachable statement executed");
        }
    }

    // Typical Projects

    /**
     * Creates a new CS2103T project.
     * @return typical {@code Project}.
     */
    public static Project getCS2103TProject() throws DateConversionException, TimeConversionException {
        return new ProjectBuilder().withName("CS2103T Team Project")
                .withEventList(getCS2103TEventList())
                .withDeadlineList(getCS2103TDeadlineList())
                .withTodoList(getCS2103TTodosList())
                .withGroupmateList(getCS2103TGroupmateList())
                .build();
    }

    /**
     * Creates a new CS2101 project.
     * @return typical {@code Project}.
     */
    public static Project getCS2101Project() throws DateConversionException, TimeConversionException {
        return new ProjectBuilder().withName("CS2101 OP2")
                .withEventList(getCS2101EventList())
                .withDeadlineList(getCS2101DeadlineList())
                .withTodoList(getCS2101TodosList())
                .withGroupmateList(getCS2101GroupmateList())
                .build();
    }

    private static EventList getCS2103TEventList() throws DateConversionException, TimeConversionException {
        Event eventWeeklyMeeting = new EventBuilder().withDescription("Weekly Project Meeting")
                .withDate(encodeDate("31-01-2021")).withTime(encodeTime("2000")).withIsWeekly(true).build();

        EventList eventList = new EventList();
        eventList.addEvent(eventWeeklyMeeting);

        return eventList;
    }

    private static DeadlineList getCS2103TDeadlineList() throws DateConversionException {
        Deadline deadlineMilestone1 = new DeadlineBuilder().withDescription("Milestone v1.1")
                .withByDate(encodeDate("01-03-2021")).withIsDone(DONE).build();
        Deadline deadlineMilestone2 = new DeadlineBuilder().withDescription("Milestone v1.2")
                .withByDate(encodeDate("15-03-2021")).withIsDone(DONE).build();
        Deadline deadlineMilestone3 = new DeadlineBuilder().withDescription("Milestone v1.3")
                .withByDate(encodeDate("31-03-2021")).withIsDone(NOT_DONE).build();
        Deadline deadlineMilestone4 = new DeadlineBuilder().withDescription("Milestone v1.4")
                .withByDate(encodeDate("12-04-2021")).withIsDone(NOT_DONE).build();

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineMilestone1);
        deadlineList.addDeadline(deadlineMilestone2);
        deadlineList.addDeadline(deadlineMilestone3);
        deadlineList.addDeadline(deadlineMilestone4);

        return deadlineList;
    }

    private static TodoList getCS2103TTodosList() {
        Todo todoTests = new TodoBuilder().withDescription("Add unit tests")
                .withIsDone(NOT_DONE).build();
        Todo todoBrainstormNewIdea = new TodoBuilder().withDescription("Brainstorm new feature")
                .withIsDone(NOT_DONE).build();
        Todo todoAddCommand = new TodoBuilder().withDescription("Finish add command")
                .withIsDone(DONE).build();
        Todo todoUpdateDocs = new TodoBuilder().withDescription("Update documentation")
                .withIsDone(NOT_DONE).build();

        TodoList todoList = new TodoList();
        todoList.addTodo(todoTests);
        todoList.addTodo(todoBrainstormNewIdea);
        todoList.addTodo(todoAddCommand);
        todoList.addTodo(todoUpdateDocs);

        return todoList;
    }

    private static GroupmateList getCS2103TGroupmateList() {
        Groupmate danh = new GroupmateBuilder().withName("Danh").build();
        Groupmate ruochen = new GroupmateBuilder().withName("Ruochen").build();
        Groupmate samuel = new GroupmateBuilder().withName("Samuel").build();
        Groupmate vevek = new GroupmateBuilder().withName("Vevek").build();

        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(danh);
        groupmateList.addGroupmate(ruochen);
        groupmateList.addGroupmate(samuel);
        groupmateList.addGroupmate(vevek);

        return groupmateList;
    }

    private static EventList getCS2101EventList() throws DateConversionException, TimeConversionException {
        Event eventPlanningMeeting = new EventBuilder().withDescription("OP2 Planning Meeting")
                .withDate(encodeDate("17-03-2021")).withTime(encodeTime("17:30")).withIsWeekly(false).build();
        Event eventOP2Consultation = new EventBuilder().withDescription("OP2 Consultation")
                .withDate(encodeDate("23-03-2021")).withTime(encodeTime("1000")).withIsWeekly(false).build();
        Event eventOP2ProductDemo = new EventBuilder().withDescription("OP2 Product Demo")
                .withDate(encodeDate("06-04-2021")).withTime(encodeTime("1000")).withIsWeekly(false).build();
        Event eventOP2Pitch = new EventBuilder().withDescription("OP2 Pitch")
                .withDate(encodeDate("09-04-2021")).withTime(encodeTime("1000")).withIsWeekly(false).build();

        EventList eventList = new EventList();
        eventList.addEvent(eventPlanningMeeting);
        eventList.addEvent(eventOP2Consultation);
        eventList.addEvent(eventOP2ProductDemo);
        eventList.addEvent(eventOP2Pitch);

        return eventList;
    }

    private static DeadlineList getCS2101DeadlineList() throws DateConversionException {
        Deadline deadlineDemo = new DeadlineBuilder().withDescription("Product Demo Internal Deadline")
                .withByDate(encodeDate("05-04-2021")).withIsDone(NOT_DONE).build();
        Deadline deadlinePitch = new DeadlineBuilder().withDescription("Product Pitch Internal Deadline")
                .withByDate(encodeDate("08-04-2021")).withIsDone(NOT_DONE).build();

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineDemo);
        deadlineList.addDeadline(deadlinePitch);

        return deadlineList;
    }

    private static TodoList getCS2101TodosList() {
        Todo todoSlidesForPitch = new TodoBuilder().withDescription("Do up slides for pitch")
                .withIsDone(NOT_DONE).build();
        Todo todoVideoForDemo = new TodoBuilder().withDescription("Do up video for demo")
                .withIsDone(NOT_DONE).build();
        Todo todoSplitWorkload = new TodoBuilder().withDescription("Split Workload")
                .withIsDone(DONE).build();

        TodoList todoList = new TodoList();
        todoList.addTodo(todoSlidesForPitch);
        todoList.addTodo(todoVideoForDemo);
        todoList.addTodo(todoSplitWorkload);

        return todoList;
    }

    private static GroupmateList getCS2101GroupmateList() {
        Groupmate danh = new GroupmateBuilder().withName("Danh").build();
        Groupmate ruochen = new GroupmateBuilder().withName("Ruochen").build();
        Groupmate samuel = new GroupmateBuilder().withName("Samuel").build();
        Groupmate vevek = new GroupmateBuilder().withName("Vevek").build();

        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(danh);
        groupmateList.addGroupmate(ruochen);
        groupmateList.addGroupmate(samuel);
        groupmateList.addGroupmate(vevek);

        return groupmateList;
    }
}
