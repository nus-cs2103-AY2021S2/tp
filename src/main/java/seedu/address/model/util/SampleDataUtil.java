package seedu.address.model.util;

import static seedu.address.commons.util.DateUtil.encodeDate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.ColabFolder;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Interval;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.todo.Todo;

/**
 * Contains utility methods for populating {@code ColabFolder} with sample data.
 */
public class SampleDataUtil {
    public static final Boolean DONE = true;
    public static final Boolean NOT_DONE = false;

    public static ReadOnlyColabFolder getSampleColabFolder() {
        ColabFolder sampleAb = new ColabFolder();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }
        return sampleAb;
    }

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an array of sample {@code Projects}
     * @return sample {@code Projects}
     */
    public static Project[] getSampleProjects() {
        try {
            return new Project[] { getCS2103TProject(), getCS2101Project() };
        } catch (DateConversionException e) {
            assert false : "error with sample projects";
            e.printStackTrace();
            return new Project[] {}; // return empty projects array
        }
    }

    /**
     * Creates a new CS2103T project.
     * @return typical {@code Project}.
     */
    public static Project getCS2103TProject() throws DateConversionException {
        ProjectName projectName = new ProjectName("CS2103T Team Project");
        return new Project(projectName, getCS2103TEventList(), getCS2103TTodosList(),
                getCS2103TDeadlineList(), getCS2103TParticipantsList());
    }

    /**
     * Creates a new CS2101 project.
     * @return typical {@code Project}.
     */
    public static Project getCS2101Project() throws DateConversionException {
        ProjectName projectName = new ProjectName("CS2101 OP2");
        return new Project(projectName, getCS2101EventList(), getCS2101TodosList(),
                getCS2101DeadlineList(), getCS2101ParticipantsList());
    }

    private static EventList getCS2103TEventList() throws DateConversionException {
        Event eventWeeklyMeeting = new Event("Weekly Project Meeting", Interval.WEEKLY,
                encodeDate("31-01-2021"));

        EventList eventList = new EventList();
        eventList.addEvent(eventWeeklyMeeting);

        return eventList;
    }

    private static DeadlineList getCS2103TDeadlineList() throws DateConversionException {
        Deadline deadlineMilestone1 = new Deadline("Milestone v1.1",
                encodeDate("01-03-2021"), DONE);
        Deadline deadlineMilestone2 = new Deadline("Milestone v1.2",
                encodeDate("15-03-2021"), DONE);
        Deadline deadlineMilestone3 = new Deadline("Milestone v1.3",
                encodeDate("31-03-2021"), NOT_DONE);
        Deadline deadlineMilestone4 = new Deadline("Milestone v1.4",
                encodeDate("12-04-2021"), NOT_DONE);

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineMilestone1);
        deadlineList.addDeadline(deadlineMilestone2);
        deadlineList.addDeadline(deadlineMilestone3);
        deadlineList.addDeadline(deadlineMilestone4);

        return deadlineList;
    }

    private static TodoList getCS2103TTodosList() throws DateConversionException {
        Todo todoUpdateDocs = new Todo("Update documentation", DONE);
        Todo todoAddCommand = new Todo("Finish add command", NOT_DONE);
        Todo todoTests = new Todo("Add unit tests", NOT_DONE);
        Todo todoBrainstormNewIdea = new Todo("Brainstorm new feature", NOT_DONE);

        TodoList todoList = new TodoList();
        todoList.addTodo(todoUpdateDocs);
        todoList.addTodo(todoAddCommand);
        todoList.addTodo(todoTests);
        todoList.addTodo(todoBrainstormNewIdea);

        return todoList;
    }

    private static ParticipantList getCS2103TParticipantsList() throws DateConversionException {
        Person dahn = new Person(new Name("Dahn"), new Phone("91234561"), new Email("dahn@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person ruochen = new Person(new Name("Ruochen"), new Phone("91234562"), new Email("ruochen@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person samuel = new Person(new Name("Samuel"), new Phone("91234563"), new Email("samuel@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person vevek = new Person(new Name("Vevek"), new Phone("91234564"), new Email("vevek@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());

        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(dahn);
        participantList.addParticipant(ruochen);
        participantList.addParticipant(samuel);
        participantList.addParticipant(vevek);

        return participantList;
    }

    private static EventList getCS2101EventList() throws DateConversionException {
        Event eventPlanningMeeting = new Event("OP2 Planning Meeting",
                Interval.NONE, encodeDate("17-03-2021"));
        Event eventOP2Consultation = new Event("OP2 Consultation",
                Interval.NONE, encodeDate("23-03-2021"));
        Event eventOP2ProductDemo = new Event("OP2 Product Demo",
                Interval.NONE, encodeDate("06-04-2021"));
        Event eventOP2Pitch = new Event("OP2 Pitch",
                Interval.NONE, encodeDate("09-04-2021"));

        EventList eventList = new EventList();
        eventList.addEvent(eventPlanningMeeting);
        eventList.addEvent(eventOP2Consultation);
        eventList.addEvent(eventOP2ProductDemo);
        eventList.addEvent(eventOP2Pitch);

        return eventList;
    }

    private static DeadlineList getCS2101DeadlineList() throws DateConversionException {
        Deadline deadlineDemo = new Deadline("Product Demo Internal Deadline",
                encodeDate("05-04-2021"), NOT_DONE);
        Deadline deadlinePitch = new Deadline("Product Pitch Internal Deadline",
                encodeDate("08-04-2021"), NOT_DONE);

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineDemo);
        deadlineList.addDeadline(deadlinePitch);

        return deadlineList;
    }

    private static TodoList getCS2101TodosList() throws DateConversionException {
        Todo todoSplitWorkload = new Todo("Split Workload", DONE);
        Todo todoSlidesForPitch = new Todo("Do up slides for pitch", NOT_DONE);
        Todo todoVideoForDemo = new Todo("Do up video for demo", NOT_DONE);

        TodoList todoList = new TodoList();
        todoList.addTodo(todoSplitWorkload);
        todoList.addTodo(todoSlidesForPitch);
        todoList.addTodo(todoVideoForDemo);

        return todoList;
    }

    private static ParticipantList getCS2101ParticipantsList() throws DateConversionException {
        Person dahn = new Person(new Name("Dahn"), new Phone("91234561"), new Email("dahn@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person ruochen = new Person(new Name("Ruochen"), new Phone("91234562"), new Email("ruochen@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person samuel = new Person(new Name("Samuel"), new Phone("91234563"), new Email("samuel@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());
        Person vevek = new Person(new Name("Vevek"), new Phone("91234564"), new Email("vevek@colab.com"),
                new Address("1 CoLAB Road"), new HashSet<>());

        ParticipantList participantList = new ParticipantList();
        participantList.addParticipant(dahn);
        participantList.addParticipant(ruochen);
        participantList.addParticipant(samuel);
        participantList.addParticipant(vevek);

        return participantList;
    }
}
