package seedu.weeblingo.testutil;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Quiz;

/**
 * A utility class to help with building Quiz objects.
 */
public class QuizBuilder {
    private Quiz quiz;

    public QuizBuilder() throws CommandException {
        quiz = new Quiz(new ArrayList<>(), 0, new HashSet<>());
    }

    public Quiz build() {
        return this.quiz;
    }

}
