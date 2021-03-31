package seedu.weeblingo.model;

/**
 * Container for the current mode of WeebLingo.
 */
public class Mode {
    public static final int MODE_MENU = 1;
    public static final int MODE_QUIZ = 2;
    public static final int MODE_LEARN = 3;
    public static final int MODE_QUIZ_SESSION = 4;
    public static final int MODE_CHECK_SUCCESS = 5;
    public static final int MODE_HISTORY = 6;
    public static final int MODE_QUIZ_SESSION_ENDED = 7;

    private int currentMode;

    public Mode() {
        this.currentMode = MODE_MENU;
    }

    public void switchModeQuiz() {
        this.currentMode = MODE_QUIZ;
    }

    public void switchModeLearn() {
        this.currentMode = MODE_LEARN;
    }

    public void switchModeMenu() {
        this.currentMode = MODE_MENU;
    }

    public void switchModeQuizSession() {
        this.currentMode = MODE_QUIZ_SESSION;
    }

    public void switchModeCheckSuccess() {
        this.currentMode = MODE_CHECK_SUCCESS;
    }

    public void switchModeHistory() {
        this.currentMode = MODE_HISTORY;
    }

    public void switchModeQuizSessionEnded() {
        this.currentMode = MODE_QUIZ_SESSION_ENDED;
    }

    /**
     * Returns the current mode of the app.
     *
     * @return an integer representing current mode of the app
     */
    public int getCurrentMode() {
        return this.currentMode;
    }

}
