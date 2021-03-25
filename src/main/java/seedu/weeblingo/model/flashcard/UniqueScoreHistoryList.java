package seedu.weeblingo.model.flashcard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.weeblingo.model.flashcard.exceptions.DuplicateAttemptScoreException;
import seedu.weeblingo.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.weeblingo.model.score.Score;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.commons.util.CollectionUtil.requireAllNonNull;

/*
 * A list of user quiz scores that enforces uniqueness between its elements and does not allow nulls.
 * A score is considered unique by comparing using {@code Score#isSameAttempt(Score)}.
 * As such, adding quiz scores uses Score#isSameAttempt(Score) for equality so as to ensure
 * that the new quiz score for a certain attempt being added is unique in terms of identity
 * in the UniqueScoreHistoryList. Users are not expected to remove entries from the scores history through the app.
 *
 * Supports a minimal set of list operations.
 *
 * @see Score#isSameAttempt(Score)
 */
public class UniqueScoreHistoryList implements Iterable<Score> {
    private final ObservableList<Score> internalList = FXCollections.observableArrayList();
    private final ObservableList<Score> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent flashcard as the given argument.
     */
    public boolean contains(Score toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAttempt);
    }

    /**
     * Adds an attempt Score to the list.
     * The Score must not already exist in the list.
     */
    public void add(Score toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAttemptScoreException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the UniqueScoreHistoryList with {@code replacement}.
     * {@code replacement} is meant to contain unique score histories.
     */
    public void setScores(UniqueScoreHistoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code scores}.
     * {@code scores} must not contain duplicate scores.
     */
    public void setScores(List<Score> scores) {
        requireAllNonNull(scores);
        if (!scoresAreUnique(scores)) {
            throw new DuplicateFlashcardException();
        }

        internalList.setAll(scores);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Score> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Score> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFlashcardList // instanceof handles nulls
                && internalList.equals(((UniqueScoreHistoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code scores} contains only unique attempt Scores.
     */
    private boolean scoresAreUnique(List<Score> scores) {
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                if (scores.get(i).isSameAttempt(scores.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
