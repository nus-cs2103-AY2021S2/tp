package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Statistics;

/**
 * A class representing the command to update statistics whenever the user reviews a flash card.
 */
public class UpdateStatisticsCommand extends Command {
    private static final String CORRECT_ANS_MSG = "The correct answer is provided. Good Job!";
    private static final String WRONG_ANS_MSG = "An incorrect answer is provided. Try harder next time!";
    private final Flashcard cardToUpdate;
    private final boolean isCorrect;

    /**
     * Constructor of the UpdateStatisticsCommand.
     *
     * @param cardToUpdate The reviewed flash card.
     * @param isCorrect Boolean variable representing whether the user got the correct answer or not.
     */
    public UpdateStatisticsCommand(Flashcard cardToUpdate, boolean isCorrect) {
        this.cardToUpdate = cardToUpdate;
        this.isCorrect = isCorrect;
    }

    @Override
    public CommandResult execute(Model model) {
        CommandResult commandResult;
        Flashcard updatedCard;
        Statistics updatedStats = cardToUpdate.getStats().incrementReviewCount();
        if (isCorrect) {
            updatedStats = updatedStats.incrementCorrectCount();
            commandResult = new CommandResult(CORRECT_ANS_MSG);
        } else {
            commandResult = new CommandResult(WRONG_ANS_MSG);
        }
        updatedCard = new Flashcard(cardToUpdate.getQuestion(), cardToUpdate.getAnswer(), cardToUpdate.getCategory(),
                cardToUpdate.getPriority(), cardToUpdate.getRemark(), cardToUpdate.getTags(), updatedStats);
        model.setFlashcard(cardToUpdate, updatedCard);
        return commandResult;
    }
}
