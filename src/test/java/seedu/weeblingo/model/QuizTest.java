package seedu.weeblingo.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.testutil.QuizBuilder;

class QuizTest {

    private Quiz quiz;

    {
        try {
            quiz = new QuizBuilder().build();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasSessionEnded() {
        assertEquals(false, quiz.getQuizSessionQueue().isEmpty());
    }


}
