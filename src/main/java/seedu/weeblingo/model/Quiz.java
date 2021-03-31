package seedu.weeblingo.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import seedu.weeblingo.logic.commands.StartCommand;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.model.tag.Tag;

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

    // Support for storing the quiz attempt history
    private int numberOfQuestionsAttempted;
    private int numberOfQuestionsCorrect;

    private Optional<String> optionalDurationString;

    /**
     * Initializes the quiz session with a queue of flashcards tagged
     * with the specified tags in randomized order. The quiz has a length of numberOfQuestions.
     * @param flashcards The list of flashcards to use.
     * @param numberOfQuestions The length to limit the quiz to.
     * @param tags The specified tags by which to filter the questions.
     */
    public Quiz(List<Flashcard> flashcards, int numberOfQuestions, Set<Tag> tags) throws CommandException {
        Flashcard[] flashcardsReadFromDB = flashcards.stream().toArray(Flashcard[]::new);
        quizSessionQueue = getRandomizedQueue(flashcardsReadFromDB, numberOfQuestions, tags);
        initStatistics();
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

    /**
     * Checks whether the attempt is correct with respect to the current flashcard in the quiz.
     *
     * @param attempt The answer to check.
     * @return True if the attempt is correct; false otherwise. Statistics of the quiz will be updated as well.
     */
    public boolean isCorrectAttempt(Answer attempt) {
        numberOfQuestionsAttempted++;
        boolean result = currentQuiz.getAnswer().equals(attempt);
        if (result) {
            numberOfQuestionsCorrect++;
        }
        return result;
    }

    public Queue<Flashcard> getQuizSessionQueue() {
        return quizSessionQueue;
    }

    /**
     * Generates randomized queue that is a subset from the given array of flashcards.
     *
     * @param flashcardsReadFromDB An array of flashcards, previously read from database.
     * @param numberOfQuestions The number of questions to limit the quiz to. Is ignored if zero.
     * @param tags Tags used to filter the array of flashcards. Can be empty.
     * @return A queue of flashcards with randomized order.
     */
    private Queue<Flashcard> getRandomizedQueue(Flashcard[] flashcardsReadFromDB,
            int numberOfQuestions, Set<Tag> tags) throws CommandException {
        List<Flashcard> flashcardsToProcess = Arrays.asList(flashcardsReadFromDB);
        Collections.shuffle(flashcardsToProcess);
        Queue<Flashcard> randomizedQueue = new LinkedList<>();

        // Filter by tags if needed
        for (Flashcard f : flashcardsToProcess) {
            if (f.getWeeblingoTags().containsAll(tags) || f.getUserTags().containsAll(tags)) {
                randomizedQueue.offer(f);
            }
        }

        if (randomizedQueue.isEmpty()) {
            throw new CommandException(StartCommand.MESSAGE_TAG_NOT_FOUND);
        }

        // Shorten to numberOfQuestions if needed
        if (numberOfQuestions != 0) {
            while (numberOfQuestions < randomizedQueue.size()) {
                randomizedQueue.poll();
            }
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
        String result = String.format("%d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
        optionalDurationString = Optional.of(result);
        return result;
    }

    /**
     * Initializes the state of the quiz statistics.
     */
    private void initStatistics() {
        numberOfQuestionsCorrect = 0;
        numberOfQuestionsAttempted = 0;
        startTime = Instant.now();
        optionalDurationString = Optional.empty();
    }

    /**
     * Gets the statistics information of Quiz as a string for display purposes.
     *
     * @return The string representation of the quiz statistics.
     */
    public String getStatisticString() {
        return String.format("Number of attempts: %d; Number of correct attempts: %d; Time spent: %s",
                numberOfQuestionsAttempted,
                numberOfQuestionsCorrect, getQuizSessionDuration());
    }

    /**
     * Gives a score representing the quiz attempt.
     *
     * @return The score containing the statistic data of the quiz attempt.
     */
    public Score giveScore() {
        return Score.of(numberOfQuestionsAttempted, numberOfQuestionsCorrect,
                optionalDurationString.orElse(getQuizSessionDuration()));
    }
}
