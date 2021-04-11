package seedu.plan.model.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.plan.model.plan.Module;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;


/**
 * Represents the user's history, as defined by semesters prior to the current semester of the master plan.
 *
 * The History is a HashMap where the keys are the various completed Semesters and the value of each key
 * is a List containing the modules that were taken in that semester.
 */
public class History extends HashMap<Semester, List<Module>> {
    /**
     * Initialises the History object according to the given Plan and the current Semester.
     *
     * @param plan The Plan to create a History for.
     * @param currentSemester The current semester the user is at. The history will only contain
     *                        previous semesters.
     */
    public History(Plan plan, Semester currentSemester) {
        int currentSemNumber = currentSemester.getSemNumber();
        for (Semester semester : plan.getSemesters()) {
            if (semester.getSemNumber() >= currentSemNumber) {
                continue;
            }
            // HashMap of current semester -> modules taken in current semester
            this.put(semester, semester.getModules());
        }
    }

    @Override
    public String toString() {
        List<Semester> sortedSemesters = this.keySet().stream()
                .sorted(Comparator.comparingInt(Semester::getSemNumber))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        for (Semester semester : sortedSemesters) {
            stringBuilder.append(semester.toString());
            stringBuilder.append("------------------\n");
        }
        return stringBuilder.toString();
    }
}
