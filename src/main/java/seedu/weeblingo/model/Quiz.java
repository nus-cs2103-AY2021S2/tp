package seedu.weeblingo.model;

import static seedu.weeblingo.storage.LocalDatabasePopulator.getDatabaseOfFlashcards;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * Class Quiz represents a quiz session.
 */
public class Quiz {

    public static final String QUIZ_END_MESSAGE = "The Quiz is over! \n"
            + "Enter \"end\" to end the quiz. \n";

    private static Queue<Flashcard> quizSessionQueue;

    private Flashcard currentQuiz;
    private int currentQuizIndex = 0;
    private Instant startTime;

    /**
     * Initializes the quiz session with a queue of all flashcards with randomized order.
     */
    public Quiz() {
        Flashcard[] flashcardsReadFromDB = getDatabaseOfFlashcards();
        quizSessionQueue = getRandomizedQueue(flashcardsReadFromDB);
        startTime = Instant.now();
    }

    /**
     * Initializes the quiz session with a queue of all flashcards with
     * randomized order and the specified number of questions.
     */
    public Quiz(int numberOfQuestions) {
        Flashcard[] flashcardsReadFromDB = getDatabaseOfFlashcards();
        quizSessionQueue = getRandomizedSubsetQueue(flashcardsReadFromDB, numberOfQuestions);
        startTime = Instant.now();
    }

    /**
     * Checks whether the quiz session is supposed to have ended. A session has to end if there is
     * no flashcard to display as quiz question.
     *
     * @return True if there is no flashcard to display.
     */
    public static boolean hasSessionEnded() {
        return quizSessionQueue.isEmpty();
    }

    /**
     * Gets the question number of the current question in the quiz.
     */
    public int getCurrentQuizIndex() {
        return currentQuizIndex;
    }

    /**
     * Gets the next flashcard question to show to the user.
     *
     * @return The next flashcard in the queue, if the queue is not empty.
     * Returns null if the queue is empty and the session should be ended.
     */
    public Flashcard getNextQuestion() {
        if (hasSessionEnded()) {
            return null;
        } else {
            currentQuiz = quizSessionQueue.poll();
            currentQuizIndex++;
            return currentQuiz;
        }
    }

    /**
     * Gets the current flashcard question shown to the user.
     *
     * @return The current flashcard shown.
     */
    public Flashcard getCurrentQuestion() {
        return currentQuiz;
    }

    public boolean isCorrectAttempt(Answer attempt) {
        return currentQuiz.getAnswer().equals(attempt);
    }

    public Queue<Flashcard> getQuizSessionQueue() {
        return quizSessionQueue;
    }

    /**
     * Generates randomized queue from the given array of flashcards.
     *
     * @param flashcardsReadFromDB An array of flashcards, previously read from database.
     * @return A queue of flashcards with randomized order.
     */
    private Queue<Flashcard> getRandomizedQueue(Flashcard[] flashcardsReadFromDB) {
        List<Flashcard> flashcardsToShuffle = Arrays.asList(flashcardsReadFromDB);
        Collections.shuffle(flashcardsToShuffle);
        Queue<Flashcard> randomizedQueue = new LinkedList<>();
        for (Flashcard f : flashcardsToShuffle) {
            randomizedQueue.offer(f);
        }
        return randomizedQueue;
    }

    /**
     * Generates randomized queue that is a subset from the given array of flashcards.
     *
     * @param flashcardsReadFromDB An array of flashcards, previously read from database.
     * @return A queue of flashcards with randomized order.
     */
    private Queue<Flashcard> getRandomizedSubsetQueue(Flashcard[] flashcardsReadFromDB, int numberOfQuestions) {
        List<Flashcard> flashcardsToShuffle = Arrays.asList(flashcardsReadFromDB);
        Collections.shuffle(flashcardsToShuffle);
        Queue<Flashcard> randomizedQueue = new LinkedList<>();
        for (int i = 1; i <= numberOfQuestions; i++) {
            randomizedQueue.offer(flashcardsToShuffle.get(i));
        }
        return randomizedQueue;
    }

    /**
     * Gets the duration of the quiz session.
     *
     * @return the duration in (hh:mm:ss) hours, minutes, seconds format
     */
    public String getQuizSessionDuration() {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        return String.format("%d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }
}
