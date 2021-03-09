package seedu.address.model.plan;

import seedu.address.model.plan.exceptions.ModuleExceptions;

import java.util.ArrayList;
import java.util.List;

public class Module {

    private String moduleTitle;
    private String moduleCode;
    private int MCs;
    private String descriptions;
    private boolean isDone;
    private String grade;

    private List<Module> prerequisites = new ArrayList<>();
    private List<Module> preclusions = new ArrayList<>();

    public Module (String moduleTitle, String moduleCode, int MCs) {
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.MCs = MCs;
        this.isDone = false;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getMCs() {
        return MCs;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getGrade() {
        return grade;
    }

    public boolean isSameModule(Module otherModule) {
        if (this == otherModule) {
            return true;
        }
        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getModuleTitle().equals(getModuleTitle());
    }

    @Override
    public boolean equals (Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleTitle().equals(getModuleTitle())
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getMCs() == getMCs()
                && otherModule.getDescriptions().equals(getDescriptions());
    }

    /**
     * mark module as done and give it a grade
     */
    public void doneModule (String grade) throws ModuleExceptions {
        if (isDone) {
            throw new ModuleExceptions("Module is already mark as done");
        } else if (grade.length() >= 2 || !Character.isUpperCase(grade.charAt(0)) ) { //grade length entered is longer than 2 or first letter is not upper case
            throw new ModuleExceptions("Invalid grade, please try again");
        }
        this.grade = grade;
        this.isDone = true;
    }

    /**
     * convert letter grade to CAP grade
     */
    public double convertGradeToCap() {
        String g = this.getGrade();
        double numGrade;
        switch (g) {
        case "A+":
        case "A":
            numGrade = 5;
            break;
        case "A-":
            numGrade = 4.5;
            break;
        case "B+":
            numGrade = 4;
            break;
        case "B":
            numGrade = 3.5;
            break;
        case "B-":
            numGrade = 3;
            break;
        case "C+":
            numGrade = 2.5;
            break;
        case "C":
            numGrade = 2;
            break;
        case "C-":
            numGrade = 1.5;
            break;
            default: numGrade = 0;
        }
        return numGrade;
    }

    public void addPrerequisites(Module... prereq) {
        for (Module m : prereq) {
            prerequisites.add(m);
        }
    }

    public void addPreclusions (Module ... preclu) {
        for (Module m : preclu) {
            preclusions.add(m);
        }
    }

    @Override
    public String toString() {
        return getModuleCode() + " " + getModuleTitle();
    }
}
