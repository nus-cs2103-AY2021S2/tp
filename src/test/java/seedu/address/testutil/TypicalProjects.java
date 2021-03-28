package seedu.address.testutil;

import static seedu.address.commons.util.DateUtil.encodeDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.person.Person;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;
import seedu.address.model.task.Interval;
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
        } catch (DateConversionException e) {
            throw new AssertionError("Unreachable statement executed");
        }
    }

    // Typical Projects

    /**
     * Creates a new CS2103T project.
     * @return typical {@code Project}.
     */
    public static Project getCS2103TProject() throws DateConversionException {
        return new ProjectBuilder().withName("CS2103T Team Project")
                .withEventList(getCS2103TEventList())
                .withDeadlineList(getCS2103TDeadlineList())
                .withTodoList(getCS2103TTodosList())
                .withParticipantList(getCS2103TParticipantsList())
                .build();
    }

    /**
     * Creates a new CS2101 project.
     * @return typical {@code Project}.
     */
    public static Project getCS2101Project() throws DateConversionException {
        return new ProjectBuilder().withName("CS2101 OP2")
                .withEventList(getCS2101EventList())
                .withDeadlineList(getCS2101DeadlineList())
                .withTodoList(getCS2101TodosList())
                .withParticipantList(getCS2101ParticipantsList())
                .build();
    }

    private static EventList getCS2103TEventList() throws DateConversionException {
        Event eventWeeklyMeeting = new EventBuilder().withDescription("Weekly Project Meeting")
                .withAtDate(encodeDate("31-01-2021")).withInterval(Interval.WEEKLY).build();

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

    private static TodoList getCS2103TTodosList() throws DateConversionException {
        Todo todoUpdateDocs = new TodoBuilder().withDescription("Update documentation")
                .withIsDone(DONE).build();
        Todo todoAddCommand = new TodoBuilder().withDescription("Finish add command")
                .withIsDone(NOT_DONE).build();
        Todo todoTests = new TodoBuilder().withDescription("Add unit tests")
                .withIsDone(NOT_DONE).build();
        Todo todoBrainstormNewIdea = new TodoBuilder().withDescription("Brainstorm new feature")
                .withIsDone(NOT_DONE).build();

        TodoList todoList = new TodoList();
        todoList.addTodo(todoUpdateDocs);
        todoList.addTodo(todoAddCommand);
        todoList.addTodo(todoTests);
        todoList.addTodo(todoBrainstormNewIdea);

        return todoList;
    }

    private static ParticipantList getCS2103TParticipantsList() {
        Person dahn = new PersonBuilder().withName("Dahn").withAddress("1 CoLAB Road")
                .withEmail("dahn@colab.com").withPhone("91234561").build();
        Person ruochen = new PersonBuilder().withName("Ruochen").withAddress("1 CoLAB Road")
                .withEmail("ruochen@colab.com").withPhone("91234562").build();
        Person samuel = new PersonBuilder().withName("Samuel").withAddress("1 CoLAB Road")
                .withEmail("samuel@colab.com").withPhone("91234563").build();
        Person vevek = new PersonBuilder().withName("Vevek").withAddress("1 CoLAB Road")
                .withEmail("vevek@colab.com").withPhone("91234564").build();

        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(dahn);
        participantList.addParticipant(ruochen);
        participantList.addParticipant(samuel);
        participantList.addParticipant(vevek);

        return participantList;
    }

    private static EventList getCS2101EventList() throws DateConversionException {
        Event eventPlanningMeeting = new EventBuilder().withDescription("OP2 Planning Meeting")
                .withAtDate(encodeDate("17-03-2021")).withInterval(Interval.NONE).build();
        Event eventOP2Consultation = new EventBuilder().withDescription("OP2 Consultation")
                .withAtDate(encodeDate("23-03-2021")).withInterval(Interval.NONE).build();
        Event eventOP2ProductDemo = new EventBuilder().withDescription("OP2 Product Demo")
                .withAtDate(encodeDate("06-04-2021")).withInterval(Interval.NONE).build();
        Event eventOP2Pitch = new EventBuilder().withDescription("OP2 Pitch")
                .withAtDate(encodeDate("09-04-2021")).withInterval(Interval.NONE).build();

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

    private static TodoList getCS2101TodosList() throws DateConversionException {
        Todo todoSplitWorkload = new TodoBuilder().withDescription("Split Workload")
                .withIsDone(DONE).build();
        Todo todoSlidesForPitch = new TodoBuilder().withDescription("Do up slides for pitch")
                .withIsDone(NOT_DONE).build();
        Todo todoVideoForDemo = new TodoBuilder().withDescription("Do up video for demo")
                .withIsDone(NOT_DONE).build();

        TodoList todoList = new TodoList();
        todoList.addTodo(todoSplitWorkload);
        todoList.addTodo(todoSlidesForPitch);
        todoList.addTodo(todoVideoForDemo);

        return todoList;
    }

    private static ParticipantList getCS2101ParticipantsList() throws DateConversionException {
        Person dahn = new PersonBuilder().withName("Dahn").withAddress("1 CoLAB Road")
                .withEmail("dahn@colab.com").withPhone("91234561").build();
        Person ruochen = new PersonBuilder().withName("Ruochen").withAddress("1 CoLAB Road")
                .withEmail("ruochen@colab.com").withPhone("91234562").build();
        Person samuel = new PersonBuilder().withName("Samuel").withAddress("1 CoLAB Road")
                .withEmail("samuel@colab.com").withPhone("91234563").build();
        Person vevek = new PersonBuilder().withName("Vevek").withAddress("1 CoLAB Road")
                .withEmail("vevek@colab.com").withPhone("91234564").build();

        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(dahn);
        participantList.addParticipant(ruochen);
        participantList.addParticipant(samuel);
        participantList.addParticipant(vevek);

        return participantList;
    }
}
