package seedu.smartlib.testutil;

import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private SmartLib smartLib;

    public AddressBookBuilder() {
        smartLib = new SmartLib();
    }

    public AddressBookBuilder(SmartLib smartLib) {
        this.smartLib = smartLib;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Reader reader) {
        smartLib.addReader(reader);
        return this;
    }

    public SmartLib build() {
        return smartLib;
    }
}
