package seedu.address.storage;

import java.util.Arrays;

/**
 * A class to access Module Information as a json file on the hard disk.
 */

public class JsonModule {
    public String module_code;
    public String moduleTitle;
    public String num_mc;
    public String avail_sems;
    public String[] prereqs;
    public String[] preclusions;

    /**
     * Properly formats the elements for user viewing
     * @return module information in a readable format
     */
    @Override
    public String toString() {
        return  module_code + ", " + moduleTitle + ", " + num_mc + " mc" +
                "\navailable sem: " + avail_sems + '\n' +
                "prereqs: " + Arrays.toString(prereqs) +
                "\npreclusions: " + Arrays.toString(preclusions);
    }
}
