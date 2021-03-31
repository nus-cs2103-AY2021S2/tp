package seedu.address.model.person.level;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.level.LevelList.LEVEL_LIST;


/**
 * Represents a Student's education level in TutorsPet.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level implements Comparable<Level> {

    public static final String MESSAGE_CONSTRAINTS =
            "Level can only be [pri1] to [pri6], [sec1] to [sec4] or [jc1] to [jc2].";

    public final String level;
    private int levelIndex;

    /**
     * Constructs a {@code Level}.
     *
     * @param level A valid education level.
     */
    public Level(String level) {
        requireNonNull(level);
        String editedLevel = level.trim().toLowerCase();
        checkArgument(isValidLevel(editedLevel), MESSAGE_CONSTRAINTS);
        this.level = editedLevel;
        this.levelIndex = assignLevelIndex();
    }

    Level(String level, int index) {
        this.level = level;
        this.levelIndex = index;
    }

    public String getLevel() {
        return level;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    /**
     * Returns true if a given string is a valid school.
     */
    public static boolean isValidLevel(String test) {
        // ArrayList<String> levelList = new LevelList().LEVEL_LIST;
        boolean result = false;
        for (int i = 1; i < 12; i++) {
            if (test.equals(LEVEL_LIST.get(i))) {
                result = true;
            }
        }
        return result;

        // return LevelList.isValidLevel(test);
    }

    private int assignLevelIndex() {
        int result = 0;
        for (int i = 1; i < 12; i++) {
            if (level.equals(LEVEL_LIST.get(i))) {
                result = i;
            }
        }
        return result;
    }

    private Level levelUp() {
        int nextIndex = (levelIndex + 1) % 13;
        String nextLevel = LEVEL_LIST.get(nextIndex);
        if (nextLevel.equals("")) {
            // TODO: implement student graduation exception message
        }
        return new Level(nextLevel, nextIndex);
    }

    /*
    private Level levelDown() {
        return
    }*/


    @Override
    public String toString() {
        return level;
    }

    @Override
    public int compareTo(Level other) {
        String thisLevel = this.level;
        String otherLevel = other.level;
        return thisLevel.compareTo(otherLevel);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Level // instanceof handles nulls
                && level.equals(((Level) other).level)); // state check
    }

    @Override
    public int hashCode() {
        return level.hashCode();
    }

}
