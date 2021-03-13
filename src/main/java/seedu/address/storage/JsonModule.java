package seedu.address.storage;

import java.util.Arrays;

/**
 * A class to access Module Information as a json file on the hard disk.
 */

public class JsonModule {
    private String moduleCode;
    private String moduleTitle;
    private String numMc;
    private String availSems;
    private String[] prereqs;
    private String[] preclusions;

    /**
     * Properly formats the elements for user viewing
     * @return module information in a readable format
     */
    @Override
    public String toString() {
        return moduleCode + ", " + moduleTitle + ", " + numMc + " mc"
                + "\navailable sem: " + availSems + '\n'
                + "prereqs: " + Arrays.toString(prereqs)
                + "\npreclusions: " + Arrays.toString(preclusions);
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getNumMc() {
        return numMc;
    }

    public void setNumMc(String numMc) {
        this.numMc = numMc;
    }

    public String getAvailSems() {
        return availSems;
    }

    public void setAvailSems(String availSems) {
        this.availSems = availSems;
    }

    public String[] getPrereqs() {
        return prereqs;
    }

    public void setPrereqs(String[] prereqs) {
        this.prereqs = prereqs;
    }

    public String[] getPreclusions() {
        return preclusions;
    }

    public void setPreclusions(String[] preclusions) {
        this.preclusions = preclusions;
    }
}
