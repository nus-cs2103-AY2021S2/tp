package seedu.address.model.flashcard;

import java.util.Objects;

public class Statistics {
    public static final String MESSAGE_CONSTRAINT = "Flash card review count and correct count should be "
            + "positive integers, the correct count should be less than or equal to the review count";
    private int reviewCount;
    private int correctCount;


    public Statistics() {
        this.reviewCount = 0;
        this.correctCount = 0;
    }

    public Statistics(int reviewCount, int correctCount) {
        this.reviewCount = reviewCount;
        this.correctCount = correctCount;
    }

    public static boolean isValidStats(Statistics stats) {
        if (stats.getCorrectCount() < 0 ||  stats.getReviewCount() < 0) {
            return false;
        } else if (stats.getCorrectCount() > stats.getReviewCount()) {
            return false;
        } else {
            return true;
        }
    }

    public void incrementReviewCount() {
        reviewCount++;
    }

    public void incrementCountCount() {
        correctCount++;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public double getCorrectRate() {
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
        return String.format("Review count: %d\nCorrect Count: %d\nCorrect Rate: %.1f%%\n",
                reviewCount, correctCount, getCorrectRate());
    }
}
