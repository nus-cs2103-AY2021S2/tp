package seedu.budgetbaby.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.testutil.Assert.assertThrows;
import static seedu.budgetbaby.testutil.TypicalFinancialRecords.BREAKFAST;
import static seedu.budgetbaby.testutil.TypicalFinancialRecords.DINNER;
import static seedu.budgetbaby.testutil.TypicalFinancialRecords.LUNCH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.testutil.BudgetTrackerBuilder;

public class VersionedBudgetTrackerTest {

    private final ReadOnlyBudgetTracker budgetTrackerWithBreakfast = new BudgetTrackerBuilder()
            .withFinancialRecord(BREAKFAST).build();
    private final ReadOnlyBudgetTracker budgetTrackerWithLunch = new BudgetTrackerBuilder()
            .withFinancialRecord(LUNCH).build();
    private final ReadOnlyBudgetTracker budgetTrackerWithDinner = new BudgetTrackerBuilder()
            .withFinancialRecord(DINNER).build();
    private final ReadOnlyBudgetTracker emptyBudgetTracker = new BudgetTrackerBuilder().build();

    @Test
    public void commit_singleBudgetTracker_noStatesRemovedCurrentStateSaved() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(emptyBudgetTracker);

        versionedBudgetTracker.commit();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Collections.singletonList(emptyBudgetTracker),
                emptyBudgetTracker,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleBudgetTrackerPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        versionedBudgetTracker.commit();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Arrays.asList(emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch),
                budgetTrackerWithLunch,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleBudgetTrackerPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 2);

        versionedBudgetTracker.commit();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Collections.singletonList(emptyBudgetTracker),
                emptyBudgetTracker,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleBudgetTrackerPointerAtEndOfStateList_returnsTrue() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        assertTrue(versionedBudgetTracker.canUndo());
    }

    @Test
    public void canUndo_multipleBudgetTrackerPointerAtStartOfStateList_returnsTrue() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 1);

        assertTrue(versionedBudgetTracker.canUndo());
    }

    @Test
    public void canUndo_singleBudgetTracker_returnsFalse() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(emptyBudgetTracker);

        assertFalse(versionedBudgetTracker.canUndo());
    }

    @Test
    public void canUndo_multipleBudgetTrackerPointerAtStartOfStateList_returnsFalse() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 2);

        assertFalse(versionedBudgetTracker.canUndo());
    }

    @Test
    public void canRedo_multipleBudgetTrackerPointerNotAtEndOfStateList_returnsTrue() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 1);

        assertTrue(versionedBudgetTracker.canRedo());
    }

    @Test
    public void canRedo_multipleBudgetTrackerPointerAtStartOfStateList_returnsTrue() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 2);

        assertTrue(versionedBudgetTracker.canRedo());
    }

    @Test
    public void canRedo_singleBudgetTracker_returnsFalse() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(emptyBudgetTracker);

        assertFalse(versionedBudgetTracker.canRedo());
    }

    @Test
    public void canRedo_multipleBudgetTrackerPointerAtEndOfStateList_returnsFalse() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        assertFalse(versionedBudgetTracker.canRedo());
    }

    @Test
    public void undo_multipleBudgetTrackerPointerAtEndOfStateList_success() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        versionedBudgetTracker.undo();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Collections.singletonList(emptyBudgetTracker),
                budgetTrackerWithBreakfast,
                Collections.singletonList(budgetTrackerWithLunch));
    }

    @Test
    public void undo_multipleBudgetTrackerPointerNotAtStartOfStateList_success() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 1);

        versionedBudgetTracker.undo();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Collections.emptyList(),
                emptyBudgetTracker,
                Arrays.asList(budgetTrackerWithBreakfast, budgetTrackerWithLunch));
    }

    @Test
    public void undo_singleBudgetTracker_throwsNoUndoableStateException() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(emptyBudgetTracker);

        assertThrows(VersionedBudgetTracker.NoUndoableStateException.class, versionedBudgetTracker::undo);
    }

    @Test
    public void undo_multipleBudgetTrackerPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 2);

        assertThrows(VersionedBudgetTracker.NoUndoableStateException.class, versionedBudgetTracker::undo);
    }

    @Test
    public void redo_multipleBudgetTrackerPointerNotAtEndOfStateList_success() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 1);

        versionedBudgetTracker.redo();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Arrays.asList(emptyBudgetTracker, budgetTrackerWithBreakfast),
                budgetTrackerWithLunch,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleBudgetTrackerPointerAtStartOfStateList_success() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 2);

        versionedBudgetTracker.redo();
        assertBudgetTrackerListStatus(versionedBudgetTracker,
                Collections.singletonList(emptyBudgetTracker),
                budgetTrackerWithBreakfast,
                Collections.singletonList(budgetTrackerWithLunch));
    }

    @Test
    public void redo_singleBudgetTracker_throwsNoRedoableStateException() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(emptyBudgetTracker);

        assertThrows(VersionedBudgetTracker.NoRedoableStateException.class, versionedBudgetTracker::redo);
    }

    @Test
    public void redo_multipleBudgetTrackerPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                emptyBudgetTracker, budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        assertThrows(VersionedBudgetTracker.NoRedoableStateException.class, versionedBudgetTracker::redo);
    }

    @Test
    public void equals() {
        VersionedBudgetTracker versionedBudgetTracker = prepareBudgetTrackerList(
                budgetTrackerWithBreakfast, budgetTrackerWithLunch);

        // same values -> returns true
        VersionedBudgetTracker copy = prepareBudgetTrackerList(budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        assertTrue(versionedBudgetTracker.equals(copy));

        // same object -> returns true
        assertTrue(versionedBudgetTracker.equals(versionedBudgetTracker));

        // null -> returns false
        assertFalse(versionedBudgetTracker.equals(null));

        // different types -> returns false
        assertFalse(versionedBudgetTracker.equals(1));

        // different state list -> returns false
        VersionedBudgetTracker differentBudgetTrackerList = prepareBudgetTrackerList(
                budgetTrackerWithLunch, budgetTrackerWithDinner);
        assertFalse(versionedBudgetTracker.equals(differentBudgetTrackerList));

        // different current pointer index -> returns false
        VersionedBudgetTracker differentCurrentStatePointer = prepareBudgetTrackerList(
                budgetTrackerWithBreakfast, budgetTrackerWithLunch);
        shiftCurrentStatePointerLeftwards(versionedBudgetTracker, 1);
        assertFalse(versionedBudgetTracker.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedBudgetTracker} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedBudgetTracker#currentStatePointer} is equal
     * to {@code expectedStatesBeforePointer},
     * and states after {@code versionedBudgetTracker#currentStatePointer} is equal
     * to {@code expectedStatesAfterPointer}.
     */
    private void assertBudgetTrackerListStatus(VersionedBudgetTracker versionedBudgetTracker,
                                             List<ReadOnlyBudgetTracker> expectedStatesBeforePointer,
                                             ReadOnlyBudgetTracker expectedCurrentState,
                                             List<ReadOnlyBudgetTracker> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new BudgetTracker(versionedBudgetTracker), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedBudgetTracker.canUndo()) {
            versionedBudgetTracker.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyBudgetTracker expectedBudgetTracker : expectedStatesBeforePointer) {
            assertEquals(expectedBudgetTracker, new BudgetTracker(versionedBudgetTracker));
            versionedBudgetTracker.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyBudgetTracker expectedBudgetTracker : expectedStatesAfterPointer) {
            versionedBudgetTracker.redo();
            assertEquals(expectedBudgetTracker, new BudgetTracker(versionedBudgetTracker));
        }

        // check that there are no more states after pointer
        assertFalse(versionedBudgetTracker.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedBudgetTracker.undo());
    }

    /**
     * Creates and returns a {@code VersionedBudgetTracker} with the {@code budgetTrackerStates} added into it, and the
     * {@code versionedBudgetTracker#currentStatePointer} at the end of list.
     */
    private VersionedBudgetTracker prepareBudgetTrackerList(ReadOnlyBudgetTracker... budgetTrackerStates) {
        assertFalse(budgetTrackerStates.length == 0);

        VersionedBudgetTracker versionedBudgetTracker = new VersionedBudgetTracker(budgetTrackerStates[0]);
        for (int i = 1; i < budgetTrackerStates.length; i++) {
            versionedBudgetTracker.resetData(budgetTrackerStates[i]);
            versionedBudgetTracker.commit();
        }

        return versionedBudgetTracker;
    }

    /**
     * Shifts the {@code versionedBudgetTracker#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedBudgetTracker versionedBudgetTracker, int count) {
        for (int i = 0; i < count; i++) {
            versionedBudgetTracker.undo();
        }
    }
}
