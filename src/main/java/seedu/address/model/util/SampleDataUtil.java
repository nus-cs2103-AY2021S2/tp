package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ResidenceTracker} with sample data.
 */
public class SampleDataUtil {
    public static Residence[] getSampleResidence() {
        BookingList bookingListA = new BookingList();
        BookingList bookingListB = new BookingList();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate startA = LocalDate.parse("27-03-2021", dateFormat);
        LocalDate endA = LocalDate.parse("02-04-2021", dateFormat);
        LocalDate startB = LocalDate.parse("10-04-2021", dateFormat);
        LocalDate endB = LocalDate.parse("21-04-2021", dateFormat);
        LocalDate startC = LocalDate.parse("05-05-2021", dateFormat);
        LocalDate endC = LocalDate.parse("31-05-2021", dateFormat);


        Booking bookingA = new Booking(new TenantName("Alice Carol"), new Phone("90098118"), startA, endA);
        Booking bookingB = new Booking(new TenantName("David Michael"), new Phone("82316788"), startB, endB);
        Booking bookingC = new Booking(new TenantName("Cara Delevingne"), new Phone("96221200"), startC, endC);

        bookingListA.add(bookingA);
        bookingListA.add(bookingB);
        bookingListB.add(bookingC);

        return new Residence[]{
            new Residence(new ResidenceName("Condo"), new ResidenceAddress("Blk 45 Tampines Street 29, #08-01"),
                    new BookingList(bookingListB), new CleanStatusTag("n"), getTagSet("Reserved")),
            new Residence(new ResidenceName("Melville Park"), new ResidenceAddress("22 Simei Street 1, #10-02"),
                    new BookingList(), new CleanStatusTag("n"), getTagSet("Available")),
            new Residence(new ResidenceName("HDB"), new ResidenceAddress("Blk 30 Geylang Street 29, #06-40"),
                    new BookingList(bookingListA), new CleanStatusTag("y"), getTagSet("Booked"))

        };
    }

    /**
     * Returns an unmodifiable view of the residence tracker with the sample data.
     */
    public static ReadOnlyResidenceTracker getSampleResidenceTracker() {
        ResidenceTracker sampleRt = new ResidenceTracker();
        for (Residence sampleResidence : getSampleResidence()) {
            sampleRt.addResidence(sampleResidence);
        }
        return sampleRt;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
