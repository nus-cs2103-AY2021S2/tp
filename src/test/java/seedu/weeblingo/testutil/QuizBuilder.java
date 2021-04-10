package seedu.weeblingo.testutil;

import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Quiz;
import seedu.weeblingo.model.flashcard.Flashcard;


/**
 * A utility class to help with building Quiz objects.
 */
public class QuizBuilder {
    private Quiz quiz;

    /**
     * Constructor for QuizBuilder.
     *
     * @throws CommandException if
     */
    public QuizBuilder() throws CommandException {
        ArrayList<Flashcard> list = new ArrayList<>();
        list.add(A_CARD);
        quiz = new Quiz(list, 0, new HashSet<>());
    }

    public Quiz build() {
        return this.quiz;
    }



}
