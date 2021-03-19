package seedu.address.testutil;

import seedu.address.model.RemindMe;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code RemindMe ab = new RemindMeBuilder().withPerson("John", "Doe").build();}
 */
public class RemindMeBuilder {

    private RemindMe remindMe;

    public RemindMeBuilder() {
        remindMe = new RemindMe();
    }

    public RemindMeBuilder(RemindMe remindMe) {
        this.remindMe = remindMe;
    }

    /**
     * Adds a new {@code Person} to the {@code RemindMe} that we are building.
     */
    public RemindMeBuilder withPerson(Person person) {
        remindMe.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Module} to the {@code RemindMe} that we are building.
     */
    public RemindMeBuilder withModule(Module module) {
        remindMe.addModule(module);
        return this;
    }

    public RemindMe build() {
        return remindMe;
    }
}
