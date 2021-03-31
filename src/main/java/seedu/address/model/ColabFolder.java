package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Wraps all data at the CoLAB level
 * Duplicates are not allowed
 */
public class ColabFolder implements ReadOnlyColabFolder {

    private final UniqueContactList contacts;
    private final UniqueProjectList projects;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
        projects = new UniqueProjectList();
    }

    public ColabFolder() {}

    /**
     * Creates a {@code ColabFolder} using the Contacts and Projects in {@code toBeCopied}
     */
    public ColabFolder(ReadOnlyColabFolder toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Contacts list with {@code Contacts}.
     * {@code contacts} must not contain duplicate Contacts.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Replaces the contents of the project folder with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
    }

    /**
     * Resets the existing data of this {@code ColabFolder} with {@code newData}.
     */
    public void resetData(ReadOnlyColabFolder newData) {
        requireNonNull(newData);

        setContacts(newData.getContactList());
        setProjects(newData.getProjectsList());
    }

    // contact-level operations

    /**
     * Checks if contact exists in the {@code ColabFolder}.
     *
     * @return true if a Contact with the same identity as {@code contact} exists in the {@code ColabFolder},
     * false otherwise.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a Contact to the {@code ColabFolder}.
     * The Contact must not already exist in the {@code ColabFolder}.
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the {@code ColabFolder}.
     * The contact identity of {@code editedContact} must not be the same as another
     * existing contact in the {@code ColabFolder}.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code ColabFolder}.
     * {@code key} must exist in the {@code ColabFolder}.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    // project-level operations

    /**
     * Checks if project exists in the {@code ColabFolder}.
     *
     * @return true if a project with the same identity as {@code project} exists in the {@code ColabFolder},
     * false otherwise.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a project to the {@code ColabFolder}.
     * The project must not already exist in the {@code ColabFolder}.
     */
    public void addProject(Project project) {
        requireNonNull(project);

        projects.add(project);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the {@code ColabFolder}.
     * The project identity of {@code editedProject} must not be the same as another
     * existing project in the {@code ColabFolder}.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);

        projects.setProject(target, editedProject);
    }

    /**
     * Removes {@code key} from this {@code ColabFolder}.
     * {@code key} must exist in the {@code ColabFolder}.
     */
    public void removeProject(Project key) {
        requireNonNull(key);

        projects.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return "CoLAB Folder Containing "
                + contacts.asUnmodifiableObservableList().size()
                + " contacts and "
                + projects.asUnmodifiableObservableList().size()
                + " projects";
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getProjectsList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ColabFolder // instanceof handles nulls
                && contacts.equals(((ColabFolder) other).contacts)
                && projects.equals(((ColabFolder) other).projects));
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, projects);
    }
}
