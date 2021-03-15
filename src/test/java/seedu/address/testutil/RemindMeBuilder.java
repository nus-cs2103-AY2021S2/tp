package seedu.address.testutil;

import seedu.address.model.ModulePlanner;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class RemindMeBuilder {

    private ModulePlanner modulePlanner;

    public RemindMeBuilder() {
        modulePlanner = new ModulePlanner();
    }

    public RemindMeBuilder(ModulePlanner modulePlanner) {
        this.modulePlanner = modulePlanner;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public RemindMeBuilder withPerson(Person person) {
        modulePlanner.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Module} to the {@code RemindMe} that we are building.
     */
    public RemindMeBuilder withModule(Module module) {
        modulePlanner.addModule(module);
        return this;
    }

    public ModulePlanner build() {
        return modulePlanner;
    }
}
