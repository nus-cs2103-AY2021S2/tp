package seedu.address.testutil;

import seedu.address.model.ProjectsFolder;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building ProjectsFolder objects.
 * Example usage: <br>
 *     {@code ProjectsFolder pf = new ProjectsFolderBuilder().withProject(project).build();}
 */
public class ProjectsFolderBuilder {

    private ProjectsFolder projectsFolder;

    public ProjectsFolderBuilder() {
        projectsFolder = new ProjectsFolder();
    }

    /**
     * Constructs a {@code ProjectsFolderBuilder} object using the given {@code projectsFolder}
     *
     * @param projectsFolder the {@code ProjectsFolder} to construct the {@code ProjectsFolderBuilder} with
     */
    public ProjectsFolderBuilder(ProjectsFolder projectsFolder) {
        this.projectsFolder = projectsFolder;
    }

    /**
     * Adds a new {@code Project} to the {@code ProjectsFolder} that we are building.
     */
    public ProjectsFolderBuilder withPerson(Project project) {
        projectsFolder.addProject(project);
        return this;
    }

    /**
     * Builds the {@code ProjectsFolder} object.
     *
     * @return {@code ProjectsFolder}.
     */
    public ProjectsFolder build() {
        return projectsFolder;
    }
}
