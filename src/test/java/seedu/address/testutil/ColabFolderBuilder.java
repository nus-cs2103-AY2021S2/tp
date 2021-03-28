package seedu.address.testutil;

import seedu.address.model.ColabFolder;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building {@code ColabFolder} objects.
 * Example usage: <br>
 *     {@code ColabFolder ab = new ColabFolderBuilder().withContact("John", "Doe").build();}
 */
public class ColabFolderBuilder {

    private ColabFolder colabFolder;

    public ColabFolderBuilder() {
        colabFolder = new ColabFolder();
    }

    public ColabFolderBuilder(ColabFolder colabFolder) {
        this.colabFolder = colabFolder;
    }

    /**
     * Adds a new {@code Contact} to the {@code ColabFolder} that we are building.
     */
    public ColabFolderBuilder withContact(Contact contact) {
        colabFolder.addContact(contact);
        return this;
    }

    /**
     * Adds a new {@code Project} to the {@code ColabFolder} that we are building.
     */
    public ColabFolderBuilder withContact(Project project) {
        colabFolder.addProject(project);
        return this;
    }

    /**
     * Builds the {@code ColabFolder}.
     *
     * @return {@code ColabFolder}.
     */
    public ColabFolder build() {
        return colabFolder;
    }
}
