package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import seedu.address.model.ColabFolder;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

/**
 * A utility class containing typical {@code ColabFolder} to use in tests.
 */
public class TypicalColabFolder {
    /**
     * Returns a {@code ColabFolder} with all the typical persons and projects.
     */
    public static ColabFolder getTypicalColabFolder() {
        ColabFolder colabFolder = new ColabFolder();
        for (Contact contact : getTypicalPersons()) {
            colabFolder.addPerson(contact);
        }
        for (Project project : getTypicalProjects()) {
            colabFolder.addProject(project);
        }
        return colabFolder;
    }
}
