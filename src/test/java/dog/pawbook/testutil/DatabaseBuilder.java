package dog.pawbook.testutil;

import dog.pawbook.model.Database;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withOwner("John", "Doe").build();}
 */
public class DatabaseBuilder {

    private Database database;

    public DatabaseBuilder() {
        database = new Database();
    }

    public DatabaseBuilder(Database database) {
        this.database = database;
    }

    /**
     * Adds a new {@code Owner} to the {@code AddressBook} that we are building.
     */
    public DatabaseBuilder withOwner(Owner owner) {
        database.addEntity(owner);
        return this;
    }

    public Database build() {
        return database;
    }
}
