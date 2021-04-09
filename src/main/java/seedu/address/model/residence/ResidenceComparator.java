package seedu.address.model.residence;

import java.util.Comparator;

public class ResidenceComparator implements Comparator<Residence> {

    @Override
    public int compare(Residence r1, Residence r2) {
        if (r1.getCleanStatusTag().getValue().equals("Unclean")
                && (r2.getCleanStatusTag().getValue().equals("Clean"))) {
            return -1;
        } else if (r1.getCleanStatusTag().getValue().equals("Clean")
                && (r2.getCleanStatusTag().getValue().equals("Unclean"))) {
            return 1;
        } else {
            return 0;
        }
    }
}


