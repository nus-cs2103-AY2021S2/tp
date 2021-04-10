package seedu.weeblingo.testutil;

import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Quiz;
import seedu.weeblingo.model.flashcard.Flashcard;

public class QuizACard extends QuizBuilder {
    private Quiz quiz;

    /**
     * Constructor to build a
     *
     * @throws CommandException
     */
    public QuizACard() throws CommandException {
        ArrayList<Flashcard> list = new ArrayList<>();
        list.add(A_CARD);
        quiz = new Quiz(list, 0, new HashSet<>());
    }
}
