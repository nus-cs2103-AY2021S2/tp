package seedu.address.model.garment;

import java.time.LocalDate;
import java.util.Comparator;

public class GarmentComparator implements Comparator<Garment> {

    @Override
    public int compare(Garment o1, Garment o2) {
        if (o1.getLastUse().value.equals("Never") && !o2.getLastUse().value.equals("Never")) {
            return -1;
        } else if (o2.getLastUse().value.equals("Never") && !o1.getLastUse().value.equals("Never")) {
            return 1;
        } else if (o1.getLastUse().value.equals("Never") && o2.getLastUse().value.equals("Never")) {
            return 0;
        }

        LocalDate d1 = LocalDate.parse(o1.getLastUse().value);
        LocalDate d2 = LocalDate.parse(o2.getLastUse().value);

        return d1.compareTo(d2);
    }
}
