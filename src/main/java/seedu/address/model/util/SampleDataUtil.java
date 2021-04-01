package seedu.address.model.util;

import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.commons.util.TimeUtil.encodeTime;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.TimeConversionException;
import seedu.address.model.ColabFolder;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Role;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;
import seedu.address.model.tag.Tag;
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
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }
        return sampleAb;
    }

    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @return A {@code Set<Tag>} containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a role set containing the list of strings given.
     *
     * @return A {@code Set<Role>} containing the list of strings given.
     */
    public static Set<Role> getRoleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Role::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an array of sample {@code Projects}
     *
     * @return sample {@code Projects}
     */
    public static Project[] getSampleProjects() {
        try {
            return new Project[] { getCS2103TProject(), getCS2101Project(), getWebDevProject() };
        } catch (DateConversionException | TimeConversionException e) {
            assert false : "error with sample projects";
            e.printStackTrace();
            return new Project[] {}; // return empty projects array
        }
    }

    /**
     * Creates a new CS2103T project.
     *
     * @return typical {@code Project}.
     */
    public static Project getCS2103TProject() throws DateConversionException, TimeConversionException {
        ProjectName projectName = new ProjectName("CS2103T Team Project");
        return new Project(projectName, getCS2103TEventList(), getCS2103TTodosList(),
                getCS2103TDeadlineList(), getCS2103TGroupmateList());
    }

    /**
     * Creates a new CS2101 project.
     *
     * @return typical {@code Project}.
     */
    public static Project getCS2101Project() throws DateConversionException, TimeConversionException {
        ProjectName projectName = new ProjectName("CS2101 OP2");
        return new Project(projectName, getCS2101EventList(), getCS2101TodosList(),
                getCS2101DeadlineList(), getCS2101GroupmateList());
    }

    /**
     * Creates a new Web Dev Project.
     *
     * @return typical {@code Project}.
     */
    public static Project getWebDevProject() throws DateConversionException, TimeConversionException {
        ProjectName projectName = new ProjectName("Web Dev Project");
        return new Project(projectName, getWebDevEventList(), getWebDevTodosList(),
                getWebDevDeadlineList(), getWebDevGroupmateList());
    }

    private static EventList getCS2103TEventList() throws DateConversionException, TimeConversionException {
        Event eventWeeklyMeeting = new Event("Weekly Project Meeting",
                encodeDate("31-01-2021"), encodeTime("1730") , true);
        Event eventPracticalDryRun = new Event("Practical Exam Dry Run",
                encodeDate("03-04-2021"), encodeTime("1400"), false);
        Event eventPractical = new Event("Practical Exam",
                encodeDate("16-04-2021"), encodeTime("1400"), false);

        EventList eventList = new EventList();
        eventList.addEvent(eventWeeklyMeeting);
        eventList.addEvent(eventPracticalDryRun);
        eventList.addEvent(eventPractical);

        return eventList;
    }

    private static DeadlineList getCS2103TDeadlineList() throws DateConversionException {
        Deadline deadlineMilestone1 = new Deadline("Milestone v1.1",
                encodeDate("01-03-2021"), DONE);
        Deadline deadlineMilestone2 = new Deadline("Milestone v1.2",
                encodeDate("15-03-2021"), DONE);
        Deadline deadlineMilestone3 = new Deadline("Milestone v1.3",
                encodeDate("31-03-2021"), DONE);
        Deadline deadlineMilestone4 = new Deadline("Milestone v1.4",
                encodeDate("12-04-2021"), NOT_DONE);

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineMilestone1);
        deadlineList.addDeadline(deadlineMilestone2);
        deadlineList.addDeadline(deadlineMilestone3);
        deadlineList.addDeadline(deadlineMilestone4);

        return deadlineList;
    }

    private static TodoList getCS2103TTodosList() {
        Todo todoUpdateDocs = new Todo("Update documentation", DONE);
        Todo todoAddCommand = new Todo("Finish add command", DONE);
        Todo todoTests = new Todo("Add unit tests", NOT_DONE);
        Todo todoBrainstormNewIdea = new Todo("Brainstorm new feature", NOT_DONE);

        TodoList todoList = new TodoList();
        todoList.addTodo(todoUpdateDocs);
        todoList.addTodo(todoAddCommand);
        todoList.addTodo(todoTests);
        todoList.addTodo(todoBrainstormNewIdea);

        return todoList;
    }

    private static GroupmateList getCS2103TGroupmateList() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role("Developer"));
        Groupmate danh = new Groupmate(new seedu.address.model.groupmate.Name("Danh"), roles);
        Groupmate ruochen = new Groupmate(new seedu.address.model.groupmate.Name("Ruochen"), roles);
        Groupmate samuel = new Groupmate(new seedu.address.model.groupmate.Name("Samuel"), roles);
        Groupmate vevek = new Groupmate(new seedu.address.model.groupmate.Name("Vevek"), roles);

        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(danh);
        groupmateList.addGroupmate(ruochen);
        groupmateList.addGroupmate(samuel);
        groupmateList.addGroupmate(vevek);

        return groupmateList;
    }

    private static EventList getCS2101EventList() throws DateConversionException, TimeConversionException {
        Event eventPlanningMeeting = new Event("OP2 Planning Meeting",
                encodeDate("17-03-2021"), encodeTime("2000"), false
        );
        Event eventOP2Consultation = new Event("OP2 Consultation",
                encodeDate("23-03-2021"), encodeTime("1000"), false
        );
        Event eventOP2ProductDemo = new Event("OP2 Product Demo",
                encodeDate("06-04-2021"), encodeTime("1000"), false
        );
        Event eventOP2Pitch = new Event("OP2 Pitch",
                encodeDate("09-04-2021"), encodeTime("1000"), false
        );

        EventList eventList = new EventList();
        eventList.addEvent(eventPlanningMeeting);
        eventList.addEvent(eventOP2Consultation);
        eventList.addEvent(eventOP2ProductDemo);
        eventList.addEvent(eventOP2Pitch);

        return eventList;
    }

    private static DeadlineList getCS2101DeadlineList() throws DateConversionException {
        Deadline deadlineDemo = new Deadline("Product Demo Internal Deadline",
                encodeDate("03-04-2021"), NOT_DONE);
        Deadline deadlinePitch = new Deadline("Product Pitch Internal Deadline",
                encodeDate("06-04-2021"), NOT_DONE);

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineDemo);
        deadlineList.addDeadline(deadlinePitch);

        return deadlineList;
    }

    private static TodoList getCS2101TodosList() {
        Todo todoSplitWorkload = new Todo("Split Workload", DONE);
        Todo todoSlidesForPitch = new Todo("Do up slides for pitch", NOT_DONE);
        Todo todoVideoForDemo = new Todo("Do up video for demo", NOT_DONE);

        TodoList todoList = new TodoList();
        todoList.addTodo(todoSplitWorkload);
        todoList.addTodo(todoSlidesForPitch);
        todoList.addTodo(todoVideoForDemo);

        return todoList;
    }

    private static GroupmateList getCS2101GroupmateList() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role("Team_Member"));
        Groupmate danh = new Groupmate(new seedu.address.model.groupmate.Name("Danh"), roles);
        Groupmate ruochen = new Groupmate(new seedu.address.model.groupmate.Name("Ruochen"), roles);
        Groupmate samuel = new Groupmate(new seedu.address.model.groupmate.Name("Samuel"), roles);
        Groupmate vevek = new Groupmate(new seedu.address.model.groupmate.Name("Vevek"), roles);

        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(danh);
        groupmateList.addGroupmate(ruochen);
        groupmateList.addGroupmate(samuel);
        groupmateList.addGroupmate(vevek);

        return groupmateList;
    }

    private static EventList getWebDevEventList() throws DateConversionException, TimeConversionException {
        Event eventMeeting = new Event("Web Dev Meeting",
                encodeDate("02-04-2021"), encodeTime("2000"), true
        );

        EventList eventList = new EventList();
        eventList.addEvent(eventMeeting);

        return eventList;
    }

    private static DeadlineList getWebDevDeadlineList() throws DateConversionException {
        Deadline deadlineFirst = new Deadline("First draft",
                encodeDate("03-04-2021"), DONE);
        Deadline deadlineSecond = new Deadline("Second draft",
                encodeDate("16-04-2021"), NOT_DONE);

        DeadlineList deadlineList = new DeadlineList();
        deadlineList.addDeadline(deadlineFirst);
        deadlineList.addDeadline(deadlineSecond);

        return deadlineList;
    }

    private static TodoList getWebDevTodosList() {
        Todo todoWriteTests = new Todo("Write unit tests", NOT_DONE);
        Todo todoStyle = new Todo("Style buttons", DONE);
        Todo todoHeader = new Todo("Do up header component", NOT_DONE);

        TodoList todoList = new TodoList();
        todoList.addTodo(todoWriteTests);
        todoList.addTodo(todoStyle);
        todoList.addTodo(todoHeader);

        return todoList;
    }

    private static GroupmateList getWebDevGroupmateList() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role("Web_Developer"));
        Groupmate danh = new Groupmate(new seedu.address.model.groupmate.Name("Danh"), roles);
        Groupmate ruochen = new Groupmate(new seedu.address.model.groupmate.Name("Ruochen"), roles);
        Groupmate samuel = new Groupmate(new seedu.address.model.groupmate.Name("Samuel"), roles);
        Groupmate vevek = new Groupmate(new seedu.address.model.groupmate.Name("Vevek"), roles);

        GroupmateList groupmateList = new GroupmateList();
        groupmateList.addGroupmate(danh);
        groupmateList.addGroupmate(ruochen);
        groupmateList.addGroupmate(samuel);
        groupmateList.addGroupmate(vevek);

        return groupmateList;
    }
}
