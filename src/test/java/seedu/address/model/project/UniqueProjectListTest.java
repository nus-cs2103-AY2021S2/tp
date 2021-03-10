package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.CS1101S;
import static seedu.address.testutil.TypicalProjects.CS2103T_PROJECT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

public class UniqueProjectListTest {

    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.contains(null));
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(CS1101S));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.add(CS1101S);
        assertTrue(uniqueProjectList.contains(CS1101S));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.add(null));
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(CS1101S);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.add(CS1101S));
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(null, CS1101S));
    }

    @Test
    public void setProject_nullEditedProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(CS1101S, null));
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.setProject(CS1101S, CS1101S));
    }

    @Test
    public void setProject_editedProjectIsSameProject_success() {
        uniqueProjectList.add(CS1101S);
        uniqueProjectList.setProject(CS1101S, CS1101S);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(CS1101S);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.remove(null));
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.remove(CS1101S));
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.add(CS1101S);
        uniqueProjectList.remove(CS1101S);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullUniqueProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((UniqueProjectList) null));
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvidedUniqueProjectList() {
        uniqueProjectList.add(CS1101S);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(CS2103T_PROJECT);
        uniqueProjectList.setProjects(expectedUniqueProjectList);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((List<Project>) null));
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.add(CS1101S);
        List<Project> projectList = Collections.singletonList(CS2103T_PROJECT);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(CS2103T_PROJECT);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(CS1101S, CS1101S);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }
}
