package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.plan.Semester;

/**
 * A utility class containing a list of {@code Plan} objects to be used in tests.
 */
public class TypicalSemesters {

    public static final Semester SEMESTER1 = new Semester(1);
    public static final Semester SEMESTER2 = new Semester(2);

    private TypicalSemesters() {} // prevents instantiation

    public static List<Semester> getTypicalSemesters() {
        return new ArrayList<>(Arrays.asList(SEMESTER1, SEMESTER2));
    }
}
