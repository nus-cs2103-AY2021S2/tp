package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.CS1101S;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;

public class ProjectsFolderTest {

    private final ProjectsFolder projectsFolder = new ProjectsFolder();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), projectsFolder.getProjectsList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectsFolder.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyProjectsFolder_replacesData() {
        ProjectsFolder newData = getTypicalProjectsFolder();
        projectsFolder.resetData(newData);
        assertEquals(newData, projectsFolder);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> newProjects = Arrays.asList(CS1101S, CS1101S);
        ProjectsFolderStub newData = new ProjectsFolderStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> projectsFolder.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectsFolder.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInProjectsFolder_returnsFalse() {
        assertFalse(projectsFolder.hasProject(CS1101S));
    }

    @Test
    public void hasProject_projectInProjectsFolder_returnsTrue() {
        projectsFolder.addProject(CS1101S);
        assertTrue(projectsFolder.hasProject(CS1101S));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> projectsFolder.getProjectsList().remove(0));
    }

    /**
     * A stub ReadOnlyProjectsFolder whose projects list can violate interface constraints.
     */
    private static class ProjectsFolderStub implements ReadOnlyProjectsFolder {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        ProjectsFolderStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getProjectsList() {
            return projects;
        }
    }

}
