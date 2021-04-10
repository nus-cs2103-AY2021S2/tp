package seedu.weeblingo.testutil;

import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcards;

import java.util.HashSet;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Quiz;


/**
 * A utility class to help with building Quiz objects.
 */
public class QuizBuilder {
    private Quiz quiz;

    /**
     * Constructor for QuizBuilder.
     *
     * @@throws CommandException if there are no flashcards with specified tag or
     * number of questions specified is larger than number of flashcards in database.
     */
    public QuizBuilder() throws CommandException {
        quiz = new Quiz(getTypicalFlashcards(), 1, new HashSet<>());
    }

    public Quiz build() {
        return this.quiz;
    }

}

