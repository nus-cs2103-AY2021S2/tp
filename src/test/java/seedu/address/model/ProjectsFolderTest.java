package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.testutil.ProjectBuilder;

public class ProjectsFolderTest {

    private static final Project TEST_PROJECT = new ProjectBuilder().build();
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
    public void resetData_withValidReadOnlyProjectsFolder_replacesData() throws DateConversionException {
        ProjectsFolder newData = getTypicalProjectsFolder();
        projectsFolder.resetData(newData);
        assertEquals(newData, projectsFolder);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> newProjects = Arrays.asList(TEST_PROJECT, TEST_PROJECT);
        ProjectsFolderStub newData = new ProjectsFolderStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> projectsFolder.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectsFolder.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInProjectsFolder_returnsFalse() {
        assertFalse(projectsFolder.hasProject(TEST_PROJECT));
    }

    @Test
    public void hasProject_projectInProjectsFolder_returnsTrue() {
        projectsFolder.addProject(TEST_PROJECT);
        assertTrue(projectsFolder.hasProject(TEST_PROJECT));
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

    @Test
    public void hashCode_success() {
        int hashcode1 = projectsFolder.hashCode();
        projectsFolder.addProject(new ProjectBuilder().withName("project").build());
        int hashcode2 = projectsFolder.hashCode();
        assertNotEquals(hashcode1, hashcode2);
        assertEquals(hashcode2, projectsFolder.hashCode());
    }

    @Test
    public void toString_success() {
        assertEquals("0 projects", projectsFolder.toString());
    }

}
