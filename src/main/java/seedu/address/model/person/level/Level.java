package seedu.address.model.person.level;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.level.LevelList.LEVEL_LIST;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.subject.Subject;

/**
 * Represents a Student's education level in TutorsPet.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level implements Comparable<Level> {

    public static final String MESSAGE_CONSTRAINTS =
            "Level can only be [pri1] to [pri6], [sec1] to [sec4], [jc1] to [jc2] or [grad].";

    private String level;
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

    public String getFullLevel() {
        switch (level) {

        case "pri1":
            return "Primary 1";

        case "pri2":
            return "Primary 2";

        case "pri3":
            return "Primary 3";

        case "pri4":
            return "Primary 4";

        case "pri5":
            return "Primary 5";

        case "pri6":
            return "Primary 6";

        case "sec1":
            return "Secondary 1";

        case "sec2":
            return "Secondary 2";

        case "sec3":
            return "Secondary 3";

        case "sec4":
            return "Secondary 4";

        case "sec5":
            return "Secondary 5";

        case "jc1":
            return "Junior College 1";

        case "jc2":
            return "Junior College 2";

        default:
            return "Graduated";
        }
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
        for (int i = 0; i < 14; i++) {
            if (test.toLowerCase(Locale.ROOT).equals(LEVEL_LIST.get(i))) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns an {@code Index} that corresponds to the {@level} string input by the
     * user.
     */
    private int assignLevelIndex() {
        int result = 0;
        for (int i = 0; i < 12; i++) {
            if (level.equals(LEVEL_LIST.get(i))) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Alters {@code Level} to reflect a {@code Level} that is one grade higher.
     */
    public void levelUp() {
        int nextIndex;
        String nextLevel;
        if (levelIndex < LEVEL_LIST.size() - 1) {
            nextIndex = levelIndex + 1;
            nextLevel = LEVEL_LIST.get(nextIndex);
        } else {
            // Student graduated
            nextIndex = 13;
            nextLevel = LEVEL_LIST.get(nextIndex);
        }
        this.level = nextLevel;
        this.levelIndex = nextIndex;
        // return new Level(nextLevel, nextIndex);
    }

    /**
     * Alters {@code Level} to reflect a {@code Level} that is one grade lower.
     */
    public void levelDown() {
        int prevIndex;
        String prevLevel;
        if (levelIndex != 0) {
            prevIndex = levelIndex - 1;
            prevLevel = LEVEL_LIST.get(prevIndex);
        } else {
            // Student is already pri1
            prevIndex = 0;
            prevLevel = LEVEL_LIST.get(prevIndex);
        }
        this.level = prevLevel;
        this.levelIndex = prevIndex;
        // return new Level(nextLevel, nextIndex);
    }

    /**
     * Returns a {@code Person} with all the same attributes as the input
     * {@code Person}
     */
    public static Person clonePerson (Person person) {
        Name name = person.getName();
        Phone phone = person.getPhone();
        Optional<School> school = person.getSchool();
        Optional<Email> email = person.getEmail();
        Optional<Address> address = person.getAddress();
        Optional<Name> guardianName = person.getGuardianName();
        Optional<Phone> guardianPhone = person.getGuardianPhone();
        Optional<Level> level = person.getLevel();
        Set<Subject> subjects = person.getSubjects();
        Set<Lesson> lessons = person.getLessons();

        Person newPerson = new Person(name, phone, school, email, address, guardianName, guardianPhone, level,
                subjects, lessons);

        for (Lesson l : lessons) {
            l.removePerson(person);
            l.addPerson(newPerson);
        }

        return newPerson;
    }

    /**
     * Returns a {@code Person} with all the same attributes as the input
     * {@code Person} except for the {@code Level}
     */
    public static Person changeLevel(Person person, Optional<Level> newLevel) {
        Name name = person.getName();
        Phone phone = person.getPhone();
        Optional<School> school = person.getSchool();
        Optional<Email> email = person.getEmail();
        Optional<Address> address = person.getAddress();
        Optional<Name> guardianName = person.getGuardianName();
        Optional<Phone> guardianPhone = person.getGuardianPhone();
        Optional<Level> level = newLevel;
        Set<Subject> subjects = person.getSubjects();
        Set<Lesson> lessons = person.getLessons();

        Person newPerson = new Person(name, phone, school, email, address, guardianName, guardianPhone, level,
                subjects, lessons);

        for (Lesson l : lessons) {
            l.removePerson(person);
            l.addPerson(newPerson);
        }

        return newPerson;
    }

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
