package seedu.address.model.flashcard;

import java.util.List;
import java.util.Objects;

/**
 * Represents statistics of flash card(s) in FlashBack.
 */
public class Statistics {
    public static final String MESSAGE_CONSTRAINT = "Flash card(s) review count and correct count should be "
            + "positive integers, the correct count should be less than or equal to the review count";
    private int reviewCount;
    private int correctCount;

    /**
     * Constructs a {@code Statistics}.
     *
     * Review count and correct count are initialized to 0.
     */
    public Statistics() {
        this.reviewCount = 0;
        this.correctCount = 0;
    }

    /**
     * Constructs a {@code Statistics} where the review count and correct count are initialized to values specified
     * in the parameters.
     *
     * @param reviewCount The number of times the flash card is reviewed.
     * @param correctCount The number of times the user got the correct answer in review mode.
     */
    public Statistics(int reviewCount, int correctCount) {
        this.reviewCount = reviewCount;
        this.correctCount = correctCount;
    }

    /**
     * Constructs a {@code Statistics} where the review count is initialized to the sum of all review counts in the
     * list and the correct count is initialized to the sum of all correct counts in the list.
     *
     * @param flashcards A list of flashcards
     */
    public Statistics(List<Flashcard> flashcards) {
        for (Flashcard card : flashcards) {
            reviewCount += card.getStats().getReviewCount();
            correctCount += card.getStats().getCorrectCount();
        }
    }

    /**
     * Tests whether the statistics if valid or not.
     *
     * @param stats The statistics to test for validity.
     * @return false if the correct/review count is negative, or if the correct count is greater than the review count.
     *         true otherwise.
     */
    public static boolean isValidStats(Statistics stats) {
        if (stats.getCorrectCount() < 0 || stats.getReviewCount() < 0) {
            return false;
        } else if (stats.getCorrectCount() > stats.getReviewCount()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Increments the review count by 1.
     */
    public Statistics incrementReviewCount() {
        return new Statistics(reviewCount + 1, correctCount);
    }

    /**
     * Increments the correct count by 1.
     */
    public Statistics incrementCorrectCount() {
        return new Statistics(reviewCount, correctCount + 1);
    }

    /**
     * Gets the number of times the flash card is reviewed.
     *
     * @return The review count associated with the flash card.
     */
    public int getReviewCount() {
        assert(reviewCount >= 0 && correctCount >= 0);
        assert(correctCount <= reviewCount);

        return reviewCount;
    }

    /**
     * Gets the number of times the user got the answer correct during review mode.
     *
     * @return The correct count associated with the flash card.
     */
    public int getCorrectCount() {
        assert(reviewCount >= 0 && correctCount >= 0);
        assert(correctCount <= reviewCount);

        return correctCount;
    }

    /**
     * Gets the rate where the user got the correct answer during review mode.
     *
     * @return 0 if the review count is 0.
     *           otherwise return the percentage of times where user got the correct answer in review mode.
     */
    public double getCorrectRate() {
        assert(reviewCount >= 0 && correctCount >= 0);
        assert(correctCount <= reviewCount);

        if (reviewCount == 0) {
            return 0.0;
        } else {
            return ((double) correctCount / reviewCount * 100);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Statistics) {
            Statistics otherStatistics = (Statistics) other;
            return otherStatistics.getReviewCount() == this.getReviewCount()
                    && otherStatistics.getCorrectCount() == this.getCorrectCount();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewCount, correctCount);
    }

    @Override
    public String toString() {
        return String.format("Correct Count: %d\nReview Count: %d\nCorrect Rate: %.1f%%\n",
                correctCount, reviewCount, getCorrectRate());
    }
}
