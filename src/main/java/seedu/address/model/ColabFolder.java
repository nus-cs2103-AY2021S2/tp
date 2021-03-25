package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Wraps all data at the CoLAB level
 * Duplicates are not allowed
 */
public class ColabFolder implements ReadOnlyColabFolder {

    private final UniquePersonList persons;
    private final UniqueProjectList projects;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        projects = new UniqueProjectList();
    }

    public ColabFolder() {}

    /**
     * Creates a {@code ColabFolder} using the persons and projects in {@code toBeCopied}
     */
    public ColabFolder(ReadOnlyColabFolder toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
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

        setPersons(newData.getPersonList());
        setProjects(newData.getProjectsList());
    }

    // person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the {@code ColabFolder}.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the {@code ColabFolder}.
     * The person must not already exist in the {@code ColabFolder}.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the {@code ColabFolder}.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the {@code ColabFolder}.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ColabFolder}.
     * {@code key} must exist in the {@code ColabFolder}.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    // project-level operations

    /**
     * Returns true if a project with the same identity as {@code project} exists in the {@code ColabFolder}.
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
                + persons.asUnmodifiableObservableList().size()
                + " persons and"
                + persons.asUnmodifiableObservableList().size()
                + " projects";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getProjectsList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ColabFolder // instanceof handles nulls
                && persons.equals(((ColabFolder) other).persons)
                && projects.equals(((ColabFolder) other).projects));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, projects);
    }
}
