package seedu.address.testutil;

import seedu.address.model.HeyMatez;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building HeyMatez objects.
 * Example usage: <br>
 *     {@code HeyMatez ab = new HeyMatezBuilder().withPerson("John", "Doe").build();}
 */
public class HeyMatezBuilder {

    private HeyMatez heyMatez;

    public HeyMatezBuilder() {
        heyMatez = new HeyMatez();
    }

    public HeyMatezBuilder(HeyMatez heyMatez) {
        this.heyMatez = heyMatez;
    }

    /**
     * Adds a new {@code Person} to the {@code HeyMatez} that we are building.
     */
    public HeyMatezBuilder withPerson(Person person) {
        heyMatez.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Task} to the {@code HeyMatez} that we are building.
     */
    public HeyMatezBuilder withTask(Task task) {
        heyMatez.addTask(task);
        return this;
    }

    public HeyMatez build() {
        return heyMatez;
    }
}
