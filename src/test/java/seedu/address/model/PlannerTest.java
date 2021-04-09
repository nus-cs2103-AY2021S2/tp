package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_HUSBAND;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_VIM;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_YELLOW;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;
import static seedu.address.testutil.TypicalTasks.getTypicalTags;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.tag.UniqueTagListTestUtil;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class PlannerTest {

    private final Planner planner = new Planner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), planner.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPlanner_replacesData() {
        Planner newData = getTypicalPlanner();
        planner.resetData(newData);
        assertEquals(newData, planner);

        UniqueTagList expectedUniqueTagList = UniqueTagListTestUtil.buildWithTags(getTypicalTags());
        assertEquals(planner.getUniqueTagListObject(), expectedUniqueTagList);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        PlannerStub newData = new PlannerStub(newTasks, ALICE.getTags());

        assertThrows(DuplicateTaskException.class, () -> planner.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInPlanner_returnsFalse() {
        assertFalse(planner.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInPlanner_returnsTrue() {
        planner.addTask(ALICE);
        assertTrue(planner.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInPlanner_returnsTrue() {
        planner.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(planner.hasTask(editedAlice));
    }

    @Test
    public void addTagsIfAbsent_addDuplicateTags_returnsCorrectSet() {
        planner.addTag(TAG_FRIEND);

        Set<Tag> duplicateTagSet = new HashSet<>();
        duplicateTagSet.add(new Tag(VALID_TAG_FRIEND));

        Set<Tag> resultSet = planner.addTagsIfAbsent(duplicateTagSet);
        assertTrue(resultSet.contains(TAG_FRIEND));
    }

    @Test
    public void addTagsIfAbsent_addMultipleSimilarTags_maintainsCorrectCount() {
        Set<Tag> set1OfTags = UniqueTagListTestUtil.buildSetOfTags(TAG_YELLOW, TAG_FRIEND);
        Set<Tag> set2OfTags = UniqueTagListTestUtil.buildSetOfTags(TAG_YELLOW, TAG_VIM);
        planner.addTagsIfAbsent(set1OfTags);
        planner.addTagsIfAbsent(set2OfTags);

        List<Tag> newTags = Arrays.asList(TAG_YELLOW, TAG_FRIEND, TAG_YELLOW, TAG_VIM);
        UniqueTagList expectedUniqueTagList = UniqueTagListTestUtil.buildWithTags(newTags);
        assertEquals(expectedUniqueTagList, planner.getUniqueTagListObject());
    }

    @Test
    public void removeTag_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> planner.removeTag(TAG_HUSBAND));
    }

    @Test
    public void removeTag_existingSingleTag_removesTag() {
        planner.addTag(TAG_HUSBAND);
        planner.removeTag(TAG_HUSBAND);

        Planner expectedPlanner = new Planner();
        assertEquals(expectedPlanner, planner);
    }

    @Test
    public void removeTag_existingTagWithMultipleCount_editsTagCount() {
        planner.addTag(TAG_HUSBAND);
        planner.addTag(TAG_HUSBAND);
        planner.removeTag(TAG_HUSBAND);

        List<Tag> expectedList = new ArrayList<>();
        expectedList.add(TAG_HUSBAND);
        UniqueTagList expectedUniqueTagList = UniqueTagListTestUtil.buildWithTags(expectedList);
        assertEquals(planner.getUniqueTagListObject(), expectedUniqueTagList);
    }

    @Test
    public void setCalendarDate_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.setCalendarDate(null));
    }

    @Test
    public void setCalendarDate_validDate_success() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        planner.setCalendarDate(validDate);

        ObservableCalendarDate expectedDate =
                new ObservableCalendarDate(LocalDate.of(2020, 1, 1));
        assertEquals(expectedDate, planner.getCalendarDate());
    }

    @Test
    public void resetCalendarDate_success() {
        LocalDate validDate = LocalDate.now();
        planner.setCalendarDate(validDate);
        planner.resetCalendarDate();

        ObservableCalendarDate expectedDate =
                new ObservableCalendarDate(LocalDate.now());
        assertEquals(expectedDate, planner.getCalendarDate());
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> planner.getTaskList().remove(0));
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> planner.getTagList().remove(0));
    }

    /**
     * A stub ReadOnlyPlanner whose tasks list can violate interface constraints.
     */
    private static class PlannerStub implements ReadOnlyPlanner {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        PlannerStub(Collection<Task> tasks, Collection<Tag> tags) {
            this.tasks.setAll(tasks);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }

        @Override
        public UniqueTagList getUniqueTagListObject() {
            return UniqueTagListTestUtil.buildWithTags(tags);
        }
    }

}
