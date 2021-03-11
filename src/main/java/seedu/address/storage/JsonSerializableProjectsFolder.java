package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ProjectsFolder;
import seedu.address.model.ReadOnlyProjectsFolder;
import seedu.address.model.project.Project;

/**
 * An Immutable Projects Folder that is serializable to JSON format.
 */
@JsonRootName(value = "projects")
class JsonSerializableProjectsFolder {

    public static final String MESSAGE_DUPLICATE_PROJECTS = "Projects list contains duplicate project(s).";

    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableProjectsFolder} with the given projects.
     */
    @JsonCreator
    public JsonSerializableProjectsFolder(@JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.projects.addAll(projects);
    }

    /**
     * Converts a given {@code ReadOnlyProjectsFolder} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableProjectsFolder}.
     */
    public JsonSerializableProjectsFolder(ReadOnlyProjectsFolder source) {
        projects.addAll(source.getProjectsList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
    }

    /**
     * Converts this projects folder into the model's {@code ProjectsFolder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ProjectsFolder toModelType() throws IllegalValueException {
        ProjectsFolder projectsFolder = new ProjectsFolder();
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (projectsFolder.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECTS);
            }
            projectsFolder.addProject(project);
        }
        return projectsFolder;
    }

}
