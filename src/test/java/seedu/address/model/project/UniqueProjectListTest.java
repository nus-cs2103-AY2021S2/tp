package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;
import seedu.address.testutil.ProjectBuilder;

public class UniqueProjectListTest {
    private static final Project TEST_PROJECT_ONE = new ProjectBuilder().withName("Test One").build();
    private static final Project TEST_PROJECT_TWO = new ProjectBuilder().withName("Test Two").build();

    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.contains(null));
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(TEST_PROJECT_ONE));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        assertTrue(uniqueProjectList.contains(TEST_PROJECT_ONE));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.add(null));
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.add(TEST_PROJECT_ONE));
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(null, TEST_PROJECT_ONE));
    }

    @Test
    public void setProject_nullEditedProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProject(TEST_PROJECT_ONE, null));
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () ->
                uniqueProjectList.setProject(TEST_PROJECT_ONE, TEST_PROJECT_ONE));
    }

    @Test
    public void setProject_setExistingProject_throwsDuplicateProjectException() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        uniqueProjectList.add(TEST_PROJECT_TWO);
        assertThrows(DuplicateProjectException.class, () ->
                uniqueProjectList.setProject(TEST_PROJECT_ONE, TEST_PROJECT_TWO));
    }

    @Test
    public void setProject_editedProjectIsSameProject_success() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        uniqueProjectList.setProject(TEST_PROJECT_ONE, TEST_PROJECT_ONE);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(TEST_PROJECT_ONE);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.remove(null));
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        assertThrows(ProjectNotFoundException.class, () -> uniqueProjectList.remove(TEST_PROJECT_ONE));
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        uniqueProjectList.remove(TEST_PROJECT_ONE);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullUniqueProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((UniqueProjectList) null));
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvidedUniqueProjectList() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(TEST_PROJECT_TWO);
        uniqueProjectList.setProjects(expectedUniqueProjectList);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProjectList.setProjects((List<Project>) null));
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        List<Project> projectList = Collections.singletonList(TEST_PROJECT_TWO);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.add(TEST_PROJECT_TWO);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(TEST_PROJECT_ONE, TEST_PROJECT_ONE);
        assertThrows(DuplicateProjectException.class, () -> uniqueProjectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProjectList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_success() {
        uniqueProjectList.add(TEST_PROJECT_ONE);
        Iterator<Project> iterator = uniqueProjectList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), TEST_PROJECT_ONE);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void hashcode_success() {
        int hashcode1 = uniqueProjectList.hashCode();
        uniqueProjectList.add(TEST_PROJECT_ONE);
        int hashcode2 = uniqueProjectList.hashCode();
        uniqueProjectList.add(TEST_PROJECT_TWO);
        int hashcode3 = uniqueProjectList.hashCode();
        assertNotEquals(hashcode1, hashcode2);
        assertNotEquals(hashcode1, hashcode3);
        assertNotEquals(hashcode2, hashcode3);
    }
}
