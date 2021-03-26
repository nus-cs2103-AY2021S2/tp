package seedu.address.model.residence;

//import java.time.LocalDate;
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
        } else { //TODO: SORT BY BOOKING DATE
            return 0;
        }
    }
}
          /*LocalDate r1BookingStartDate = r1.getBookingDetails().get(0).getStart();
            LocalDate r2BookingStartDate = r2.getBookingDetails().get(0).getStart();
            if (r1BookingStartDate.compareTo(r2BookingStartDate) < 0) {
            //earlier booking date should be in higher priority to be clean
                return -1;
            } else if (r1BookingStartDate.compareTo(r2BookingStartDate) > 0) {
                return 1;
            } else { //beyond the first date we do not care about priority since both are equally as important
                return 0;
            }
        }*/


