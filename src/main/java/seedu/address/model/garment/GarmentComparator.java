package seedu.address.model.garment;

import java.time.LocalDate;
import java.util.Comparator;

public class GarmentComparator implements Comparator<Garment> {

    @Override
    public int compare(Garment o1, Garment o2) {
        LocalDate d1 = LocalDate.parse(o1.getLastUse().value);
        LocalDate d2 = LocalDate.parse(o2.getLastUse().value);

        return d1.compareTo(d2);
    }
}
