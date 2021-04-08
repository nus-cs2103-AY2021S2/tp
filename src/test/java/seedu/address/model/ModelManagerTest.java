package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.COMPARATOR_NATURAL_ORDERED_TAG_LIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_HUSBAND;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_VIM;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_YELLOW;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_ZOO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagListTestUtil;
import seedu.address.model.task.predicates.TitleContainsKeywordsPredicate;
import seedu.address.testutil.PlannerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Planner(), new Planner(modelManager.getPlanner()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPlannerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPlannerFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPlannerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPlannerFilePath(null));
    }

    @Test
    public void setPlannerFilePath_validPath_setsPlannerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPlannerFilePath(path);
        assertEquals(path, modelManager.getPlannerFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInPlanner_returnsFalse() {
        assertFalse(modelManager.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInPlanner_returnsTrue() {
        modelManager.addTask(ALICE);
        assertTrue(modelManager.hasTask(ALICE));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInPlanner_returnsFalse() {
        assertFalse(modelManager.hasTag(TAG_HUSBAND));
    }

    @Test
    public void hasTag_tagInPlanner_returnsTrue() {
        modelManager.addTag(TAG_HUSBAND);
        assertTrue(modelManager.hasTag(TAG_HUSBAND));
    }

    @Test
    public void addTags_tagNotInOrder_maintainsSortedList() {
        Set<Tag> setOfTags = UniqueTagListTestUtil.buildWithTags(TAG_VIM, TAG_ZOO, TAG_HUSBAND);
        modelManager.addTagsIfAbsent(setOfTags);

        List<Tag> expectedSortedList = new ArrayList<>();
        expectedSortedList.add(TAG_HUSBAND);
        expectedSortedList.add(TAG_VIM);
        expectedSortedList.add(TAG_ZOO);
        assertEquals(modelManager.getSortedTagList(), expectedSortedList);
    }

    @Test
    public void setTags_tagNotInOrder_maintainsSortedList() {
        Set<Tag> ogSetOfTags = UniqueTagListTestUtil.buildWithTags(TAG_FRIEND, TAG_YELLOW, TAG_VIM);
        modelManager.addTagsIfAbsent(ogSetOfTags);
        Set<Tag> oldSetOfTags = UniqueTagListTestUtil.buildWithTags(TAG_FRIEND, TAG_YELLOW);
        Set<Tag> newSetOfTags = UniqueTagListTestUtil.buildWithTags(TAG_ZOO, TAG_HUSBAND);
        modelManager.setTags(oldSetOfTags, newSetOfTags);

        List<Tag> expectedSortedList = new ArrayList<>();
        expectedSortedList.add(TAG_HUSBAND);
        expectedSortedList.add(TAG_VIM);
        expectedSortedList.add(TAG_ZOO);
        assertEquals(modelManager.getSortedTagList(), expectedSortedList);
    }

    @Test
    public void updateSortedTagList_unsortedList_returnsSortedList() {
        Planner planner =
                new PlannerBuilder().withTag(TAG_ZOO).withTag(TAG_FRIEND).withTag(TAG_HUSBAND).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager unsortedModelManager = new ModelManager(planner, userPrefs);
        unsortedModelManager.updateSortedTagList(COMPARATOR_NATURAL_ORDERED_TAG_LIST);

        Set<Tag> setOfTags = UniqueTagListTestUtil.buildWithTags(TAG_FRIEND, TAG_HUSBAND, TAG_ZOO);
        modelManager.addTagsIfAbsent(setOfTags);
        assertEquals(modelManager, unsortedModelManager);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void getSortedTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getSortedTagList().remove(0));
    }

    @Test
    public void equals() {
        Planner planner =
                new PlannerBuilder().withTask(ALICE).withTask(BENSON).withTag(TAG_FRIEND).withTag(TAG_HUSBAND).build();
        Planner differentPlanner = new Planner();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(planner, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(planner, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different planner -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPlanner, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredTaskList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(planner, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different sortedTagList -> returns false
        modelManager.updateSortedTagList(Comparator.reverseOrder());
        assertFalse(modelManager.equals(new ModelManager(planner, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateSortedTagList(COMPARATOR_NATURAL_ORDERED_TAG_LIST);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPlannerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(planner, differentUserPrefs)));
    }
}
