package seedu.address.model.util;

import seedu.address.model.plan.Module;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class History extends HashMap<Semester, List<Module>> {
    public History(Plan plan, Semester currentSemester) {
        for (Semester semester : plan.getSemesters()) {
            if (semester.equals(currentSemester)) {
                break;
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
            stringBuilder.append("\n------------------");
        }
        return stringBuilder.toString();
    }
}
