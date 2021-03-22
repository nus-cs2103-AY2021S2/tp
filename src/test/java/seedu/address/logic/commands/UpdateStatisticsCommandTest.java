package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.testutil.TypicalIndexes;

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
