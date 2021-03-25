package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Wraps all data at the projects level
 * Duplicates are not allowed (by .isSameProject comparison)
 */
public class ProjectsFolder implements ReadOnlyProjectsFolder {

    private final UniqueProjectList projects;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        projects = new UniqueProjectList();
    }

    public ProjectsFolder() {}

    /**
     * Creates a {@code Projects} using the Projects in {@code toBeCopied}
     */
    public ProjectsFolder(ReadOnlyProjectsFolder toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // list overwrite operations

    /**
     * Replaces the contents of the project folder with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
    }

    /**
     * Resets the existing data of this {@code ProjectsFolder} with {@code newData}.
     */
    public void resetData(ReadOnlyProjectsFolder newData) {
        requireNonNull(newData);

        setProjects(newData.getProjectsList());
    }

    //// project-level operations

    /**
     * Returns true if a project with the same identity as {@code project} exists in the projects folder.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a project to the project folder.
     * The project must not already exist in the projects folder.
     */
    public void addProject(Project project) {
        requireNonNull(project);

        projects.add(project);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the projects folder.
     * The project identity of {@code editedProject} must not be the same as another
     * existing project in the projects folder.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);

        projects.setProject(target, editedProject);
    }

    /**
     * Removes {@code key} from this {@code ProjectsFolder}.
     * {@code key} must exist in the projects folder.
     */
    public void removeProject(Project key) {
        requireNonNull(key);

        projects.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return projects.asUnmodifiableObservableList().size() + " projects";
        // TODO: refine later
    }

    public ObservableList<Project> getProjectsList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectsFolder // instanceof handles nulls
                && projects.equals(((ProjectsFolder) other).projects));
    }

    @Override
    public int hashCode() {
        return projects.hashCode();
    }
}
