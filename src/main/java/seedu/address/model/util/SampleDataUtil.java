package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
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
                    new Deadline("2020-12-13"), TaskStatus.valueOf("COMPLETED"), Priority.valueOf("MEDIUM")),
            new Task(new Title("Meeting with EXCO"), new Description("Meeting to discuss about club budget"),
                    new Deadline("2021-04-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH")),
            new Task(new Title("Meeting with MINDS stakeholders"),
                    new Description("Meeting with stakeholders to discuss about planning of charity event"),
                    new Deadline("2021-02-23"), TaskStatus.valueOf("COMPLETED"), Priority.valueOf("MEDIUM")),
            new Task(new Title("Cohesion Planning"),
                    new Description("Plan for Club cohesion. Decide on logistic and games"),
                    new Deadline("2021-08-30"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("LOW")),
            new Task(new Title("Buy materials for upcoming Cohesion"),
                    new Description("Buy N95 masks, alcohol wipes for participants to use"),
                    new Deadline("2021-05-15"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("UNASSIGNED")),
            new Task(new Title("NUSSU Hackathon"),
                    new Description("Hackathon event day"),
                    new Deadline("2021-09-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }
}
