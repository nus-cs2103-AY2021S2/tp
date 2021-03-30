package dog.pawbook.testutil;

import dog.pawbook.model.Database;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * A utility class to help with building Database objects.
 * Example usage: <br>
 *     {@code Database db = new DatabaseBuilder().withOwner("John", "Doe").build();}
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
     * Adds a new {@code Owner} to the {@code Database} that we are building.
     */
    public DatabaseBuilder withOwner(Owner owner) {
        database.addEntity(owner);
        return this;
    }

    public Database build() {
        return database;
    }
}
