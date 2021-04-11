package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.Question;
import seedu.flashback.testutil.TypicalIndexes;

public class UpdateStatisticsCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void execute_review_incorrectAnswer() {
        Model dummyModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        int idx = TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased();
        Flashcard reviewedCard = dummyModel.getFilteredFlashcardList().get(idx);
        int prevCorrectCount = reviewedCard.getStats().getCorrectCount();
        Question qn = reviewedCard.getQuestion();
        UpdateStatisticsCommand cmd = new UpdateStatisticsCommand(reviewedCard, false);
        cmd.execute(dummyModel);
        for (Flashcard card : dummyModel.getFilteredFlashcardList()) {
            if (card.getQuestion().equals(qn)) {
                assertTrue(prevCorrectCount == card.getStats().getCorrectCount());
            }
        }
    }

    @Test
    public void execute_review_correctAnswer() {
        Model dummyModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        int idx = TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased();
        Flashcard reviewedCard = dummyModel.getFilteredFlashcardList().get(idx);
        int prevCorrectCount = reviewedCard.getStats().getCorrectCount();
        Question qn = reviewedCard.getQuestion();
        UpdateStatisticsCommand cmd = new UpdateStatisticsCommand(reviewedCard, true);
        cmd.execute(dummyModel);
        for (Flashcard card : dummyModel.getFilteredFlashcardList()) {
            if (card.getQuestion().equals(qn)) {
                assertTrue(prevCorrectCount + 1 == card.getStats().getCorrectCount());
            }
        }
    }
}
