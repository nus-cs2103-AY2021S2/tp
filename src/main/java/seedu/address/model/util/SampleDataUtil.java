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
            new Task(new Title("Wash dishes"), new Description("Wash dinner dishes for mum"),
                    new Deadline("2021-01-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH")),
            new Task(new Title("Go for run"), new Description("Train for IPPT"), new Deadline("2021-03-23"),
                    TaskStatus.valueOf("COMPLETED"), Priority.valueOf("MEDIUM")),
            new Task(new Title("Prepare for CS2103T finals"),
                    new Description("Mug for finals even though I will probably fail"),
                    new Deadline("2021-04-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("LOW")),
            new Task(new Title("Go to the gym"), new Description("Lift weights and get big"),
                    new Deadline("2019-02-01"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("HIGH")),
            new Task(new Title("Meeting with stakeholders"), new Description("Attend meeting with stakeholders"),
                    new Deadline("2021-03-25"), TaskStatus.valueOf("UNCOMPLETED"), Priority.valueOf("UNASSIGNED")),
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
