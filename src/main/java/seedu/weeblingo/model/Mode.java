package seedu.weeblingo.model;

/**
 * Container for the current mode of WeebLingo.
 */
public class Mode {
    public static final Integer MODE_MENU = 1;
    public static final Integer MODE_QUIZ = 2;
    public static final Integer MODE_LEARN = 3;

    private Integer currentMode;

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

    public Integer getMode() {
        return this.currentMode;
    }

}
