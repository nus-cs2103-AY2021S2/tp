package seedu.address.model.plan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Semester {
    /**
     * TODO:
     * 1. Implement SemesterParser
     * 2. Consider including isDone instance member to mark if the Semester has been completed
     */
    private final String semesterDescription = "Semester: %1$s; Total MCs: %2$s";
    private final int semNumber;
    private final List<Module> modules;

    /**
     * Constructor of Semester object.
     * @param semNumber Number of Semester.
     */
    public Semester(int semNumber) {
        this.semNumber = semNumber;
        modules = new ArrayList<>(10);
    }

    public int getSemNumber() {
        return semNumber;
    }

    public List<Module> getModules() {
        return List.copyOf(modules);
    }

    public int getTotalMCs() {
        int totalMCs = 0;
        for (Module mod : modules) {
            totalMCs += mod.getMCs();
        }
        return totalMCs;
    }

    /**
     * Returns true if both Semesters contain the same modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Semester)) {
            return false;
        }
        Semester otherSemester = (Semester) other;
        HashSet<Module> thisModuleSet = new HashSet<>(getModules());
        HashSet<Module> otherModuleSet = new HashSet<>(otherSemester.getModules());
        return thisModuleSet.equals(otherModuleSet);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getModules());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String formattedSemesterDescription = String.format(semesterDescription, getSemNumber(), getTotalMCs(), "\n");
        stringBuilder.append(formattedSemesterDescription);
        for (Module mod : modules) {
            stringBuilder.append(mod.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
