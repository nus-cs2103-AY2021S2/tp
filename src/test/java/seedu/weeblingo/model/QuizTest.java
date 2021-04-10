package seedu.weeblingo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weeblingo.testutil.TypicalFlashcards.A_CARD;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.testutil.QuizACard;

class QuizTest {

    private Quiz quiz;

    {
        try {
            quiz = new QuizACard().build();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasSessionEnded_nonEmptyQueue_returnsFalse() {
        assertEquals(false, quiz.getQuizSessionQueue().isEmpty());
    }

    @Test
    public void getCurrentQuizIndex_success() {
        int actualIndex = quiz.getCurrentQuizIndex();
        assertEquals(0, actualIndex);
    }

    @Test
    public void getNextQuestion_nonEmptyQueue_returnsFlashcard() {
        Flashcard next = quiz.getNextQuestion();
        assertNotNull(next);
    }

    @Test
    public void getNextQuestion_emptyQueue_returnsNull() {
        Queue<Flashcard> queue = quiz.getQuizSessionQueue();
        queue.poll();
        Flashcard next = quiz.getNextQuestion();
        assertNull(next);
    }

    @Test
    public void isCorrectAnswer_correctAttempt_returnsTrue() {
        quiz.getNextQuestion();
        Flashcard currentQuiz = quiz.getCurrentQuiz();
        Answer correctAns = currentQuiz.getAnswer();
        Answer correctAttempt = new Answer("a");
        boolean result = correctAttempt.equals(correctAns);
        assertTrue(result);
    }

    @Test
    public void isCorrectAnswer_wrongAttempt_returnsFalse() {
        quiz.getNextQuestion();
        Flashcard currentQuiz = quiz.getCurrentQuiz();
        Answer correctAns = currentQuiz.getAnswer();
        Answer wrongAttempt = new Answer("b");
        boolean result = wrongAttempt.equals(correctAns);
        assertFalse(result);
    }

    @Test
    public void getQuizSessionDuration_endOfQuiz_returnsDuration() {
        quiz.getNextQuestion();
        assertNull(quiz.getNextQuestion());
        String duration = quiz.getQuizSessionDuration();
        assertNotNull(duration);
    }

    @Test
    public void getStatisticString_endOfQuiz_returnsStatistics() {
        quiz.getNextQuestion();
        assertNull(quiz.getNextQuestion());
        String actualStats = quiz.getStatisticString();
        String expectedStats = String.format("Number of attempts: %d; Number of correct attempts: %d; Time spent: %s",
                0,
                0, quiz.getQuizSessionDuration());
        assertEquals(expectedStats, actualStats);
    }

    @Test
    public void giveScore_afterQuizSession_returnsScore() {
        quiz.getNextQuestion();
        quiz.isCorrectAnswer(new Answer("a"));
        Score actualScore = quiz.giveScore();
        LocalDateTime datetime = actualScore.getLocalDateTime();
        String duration = quiz.getQuizSessionDuration();
        Score expectedScore = Score.of(datetime, 1, 1, duration);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void getAttemptedFlashcards_oneAttempt_returnsOneFlashcard() {
        quiz.getNextQuestion();
        quiz.isCorrectAnswer(new Answer("a"));
        List<Flashcard> expectedList = List.of(A_CARD);
        List<Flashcard> actualList = quiz.getAttemptedFlashcards();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getCorrectlyAnsweredFlashcards_oneCorrectAttempt_returnsOneFlashcard() {
        quiz.getNextQuestion();
        quiz.isCorrectAnswer(new Answer("a"));
        List<Flashcard> expectedList = List.of(A_CARD);
        List<Flashcard> actualList = quiz.getCorrectlyAnsweredFlashcards();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getCorrectlyAnsweredFlashcards_oneWrongAttempt_returnsZeroFlashcard() {
        quiz.getNextQuestion();
        quiz.isCorrectAnswer(new Answer("b"));
        List<Flashcard> expectedList = List.of();
        List<Flashcard> actualList = quiz.getCorrectlyAnsweredFlashcards();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getQuizSessionQueue_queueExists_returnsQueue() {
        Queue<Flashcard> actualQueue = quiz.getQuizSessionQueue();
        Queue<Flashcard> expectedQueue = new LinkedList<Flashcard>(Arrays.asList(A_CARD));
        assertEquals(expectedQueue, actualQueue);
    }

    @Test
    public void getCurrentQuiz_quizExists_returnsFlashcard() {
        quiz.getNextQuestion();
        Flashcard actualQuiz = quiz.getCurrentQuiz();
        Flashcard expectedQuiz = A_CARD;
        assertEquals(expectedQuiz, actualQuiz);
    }

}
