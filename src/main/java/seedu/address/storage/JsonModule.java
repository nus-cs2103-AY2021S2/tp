package seedu.address.storage;

import java.util.Arrays;

public class JsonModule {
    public String module_code;
    public String moduleTitle;
    public String num_mc;
    public String avail_sems;
    public String[] prereqs;
    public String[] preclusions;

    @Override
    public String toString() {
        return  module_code + ", " + moduleTitle + ", " + num_mc + " mc" +
                "\navailable sem: " + avail_sems + '\n' +
                "prereqs: " + Arrays.toString(prereqs) +
                "\npreclusions: " + Arrays.toString(preclusions);
    }
}
