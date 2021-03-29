package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.plan.Module;

/**
 * A utility class containing a list of {@code Plan} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module SOFTWARE_ENGINEERING_MODULE = new Module("Software Engineering",
            "CS2103", 4);
    public static final Module COMPUTER_NETWORKING_MODULE = new Module("Computer Networking",
            "CS2105", 4);
    public static final Module COMPUTER_ORGANIZATION_MODULE = new Module("Computer Organization",
            "CS2100", 4);

    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalSemesters() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEERING_MODULE, COMPUTER_NETWORKING_MODULE));
    }
}
