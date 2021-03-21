package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatisticsTest {
    @Test
    public void constructor_nullCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Statistics(null));
    }

    @Test
    public void constructor_withReviewCorrectCount_validCorrectRate() {
        // correct count is 2, review count is 4
        Statistics stats = new Statistics(4, 2);
        assertTrue(stats.getCorrectRate() == 50.0);
    }

    @Test
    public void constructor_noArguments_zeroReviewCorrectCount() {
        Statistics noArgsStats = new Statistics();
        assertTrue(noArgsStats.getCorrectCount() == 0 && noArgsStats.getReviewCount() == 0);
    }

    @Test
    public void incrementReviewCorrectCount_invalidStats() {
        Statistics stats = new Statistics(2, 2);
        stats.incrementReviewCount();
        stats.incrementCorrectCount();
        stats.incrementCorrectCount();
        // correct count is now greater than review count
        assertFalse(Statistics.isValidStats(stats));
    }

    @Test
    public void negativeReviewCorrectCount_invalidStats() {
        // review count is -1
        Statistics negativeReviewCountStats = new Statistics(-1, 2);
        assertFalse(Statistics.isValidStats(negativeReviewCountStats));

        // correct count is -1
        Statistics negativeCorrectCountStats = new Statistics(5, -1);
        assertFalse(Statistics.isValidStats(negativeCorrectCountStats));
    }

    @Test
    public void equals() {
        Statistics firstStats = new Statistics(2, 1);
        Statistics secondStats = new Statistics();

        // same object -> returns true
        assertTrue(firstStats.equals(firstStats));

        // same values -> returns true
        Statistics firstStatsCopy = new Statistics(2, 1);
        assertTrue(firstStats.equals(firstStatsCopy));

        // different types -> returns false
        assertFalse(firstStats.equals(1));

        // null -> returns false
        assertFalse(firstStats.equals(null));

        // different correct count or different review count -> returns false
        assertFalse(firstStats.equals(secondStats));
    }
}
