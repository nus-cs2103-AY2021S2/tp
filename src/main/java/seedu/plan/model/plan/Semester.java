package seedu.plan.model.plan;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.plan.commons.util.CollectionUtil;

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
        CollectionUtil.requireAllNonNull(semNumber);
        this.semNumber = semNumber;
        modules = new ArrayList<>(10);
    }

    /**
     * Constructor of Semester object.
     * @param semNumber Number of Semester.
     * @param modules List of modules in this semester
     */
    public Semester(int semNumber, List<Module> modules) {
        CollectionUtil.requireAllNonNull(semNumber, modules);
        this.semNumber = semNumber;
        this.modules = modules;
    }

    /** Command to get number of modules in semester.
     * * @return Number of modules in semester.
     */
    public int getNumModules() {
        return modules.size();
    }

    public int getSemNumber() {
        return semNumber;
    }

    public List<Module> getModules() {
        return Collections.unmodifiableList((modules));
    }

    public int getTotalMCs() {
        int totalMCs = 0;
        for (Module mod : modules) {
            totalMCs += mod.getMCs();
        }
        return totalMCs;
    }

    /**
     * Adds a module to the semester's list of modules
     *
     * @param module
     * @see Module
     */
    public void addModule(Module module) {
        requireNonNull(module);
        modules.add(module);
    }

    /**
     * Returns true if Semesters contain the modules with the module code passed as parameter.
     */
    public boolean hasModule(String moduleCode) {
        for (Module m : modules) {
            if (m.getModuleCode().equals(moduleCode)) {
                return true;
            }
        }
        return false;
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
        boolean isSameSemNumber = getSemNumber() == otherSemester.getSemNumber();
        HashSet<Module> thisModuleSet = new HashSet<>(getModules());
        HashSet<Module> otherModuleSet = new HashSet<>(otherSemester.getModules());
        boolean hasSameModules = thisModuleSet.equals(otherModuleSet);
        return isSameSemNumber && hasSameModules;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getModules());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String formattedSemesterDescription = String.format(semesterDescription, getSemNumber(), getTotalMCs());
        stringBuilder.append(formattedSemesterDescription + "\n");
        for (Module mod : modules) {
            stringBuilder.append(mod.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Search through the module list and find the module with the same module code
     */
    public Module getModuleByModuleCode(String moduleCode) {
        for (Module m : modules) {
            if (m.getModuleCode().equals(moduleCode)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Remove a module given as parameter, return a new semester object
     */
    public Semester removeModule(Module module) {
        modules.removeIf(m -> m.getModuleCode().equals(module.getModuleCode()));
        return new Semester(this.semNumber, modules);
    }
}
