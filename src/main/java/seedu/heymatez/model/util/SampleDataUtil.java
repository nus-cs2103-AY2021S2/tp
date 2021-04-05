package seedu.heymatez.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.person.Email;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.person.Phone;
import seedu.heymatez.model.person.Role;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

/**
 * Contains utility methods for populating {@code HeyMatez} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Role("Member")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Role("Member")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Role("Member")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Role("Member")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Role("Member")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Role("Member")),
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("End of Year Club Performance Review"),
                    new Description("End of Year Club Performance Review with the other EXCO member"),
                    new Deadline("2020-12-13"), TaskStatus.valueOf("COMPLETED"), Priority.valueOf("MEDIUM"),
                    getAssigneeSet("Alex Yeoh")),
            new Task(new Title("Meeting with EXCO"), new Description("Meeting to discuss about club budget"),
                    new Deadline("2021-04-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH"),
                    getAssigneeSet("Bernice Yu")),
            new Task(new Title("Meeting with MINDS stakeholders"),
                    new Description("Meeting with stakeholders to discuss about planning of charity event"),
                    new Deadline("2021-02-23"), TaskStatus.valueOf("COMPLETED"), Priority.valueOf("MEDIUM"),
                    getAssigneeSet("Alex Yeoh")),
            new Task(new Title("Cohesion Planning"),
                    new Description("Plan for Club cohesion. Decide on logistic and games"),
                    new Deadline("2021-08-30"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("LOW"),
                    getAssigneeSet("Alex Yeoh")),
            new Task(new Title("Buy materials for upcoming Cohesion"),
                    new Description("Buy N95 masks, alcohol wipes for participants to use"),
                    new Deadline("2021-05-15"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("UNASSIGNED"),
                    getAssigneeSet("Bernice Yu")),
            new Task(new Title("NUSSU Hackathon"),
                    new Description("Hackathon event day"),
                    new Deadline("2021-09-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH"),
                    getAssigneeSet("Bernice Yu")),
        };
    }

    public static ReadOnlyHeyMatez getSampleHeyMatez() {
        HeyMatez sampleHm = new HeyMatez();
        for (Person samplePerson : getSamplePersons()) {
            sampleHm.addPerson(samplePerson);
        }

        for (Task sampleTask : getSampleTasks()) {
            sampleHm.addTask(sampleTask);
        }
        return sampleHm;
    }

    /**
     * Returns an assignee set containing the list of strings given.
     */
    public static Set<Assignee> getAssigneeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Assignee::new)
                .collect(Collectors.toSet());
    }
}
