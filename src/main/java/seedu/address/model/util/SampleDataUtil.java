package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
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
        BookingList bookingList_A = new BookingList();
        BookingList bookingList_B = new BookingList();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start_A = LocalDate.parse("27-03-2021", dateFormat);
        LocalDate end_A = LocalDate.parse("02-04-2021", dateFormat);
        LocalDate start_B = LocalDate.parse("10-04-2021", dateFormat);
        LocalDate end_B = LocalDate.parse("21-04-2021", dateFormat);
        LocalDate start_C = LocalDate.parse("05-05-2021", dateFormat);
        LocalDate end_C = LocalDate.parse("31-05-2021", dateFormat);


        Booking booking_A = new Booking(new Name("Alice Carol"), new Phone("90098118"), start_A, end_A);
        Booking booking_B = new Booking(new Name("David Michael"), new Phone("82316788"), start_B, end_B);
        Booking booking_C = new Booking(new Name("Cara Delevingne"), new Phone("96221200"), start_C, end_C);

        bookingList_A.add(booking_A);
        bookingList_A.add(booking_B);
        bookingList_B.add(booking_C);

        return new Residence[]{
            new Residence(new ResidenceName("HDB"), new ResidenceAddress("Blk 30 Geylang Street 29, #06-40"),
                    new BookingList(bookingList_A), getCleanStatusTag("y"), getTagSet("Booked")),
            new Residence(new ResidenceName("Condo"), new ResidenceAddress("Blk 45 Tampines Street 29, #08-01"),
                    new BookingList(bookingList_B), getCleanStatusTag("n"), getTagSet("Reserved")),
            new Residence(new ResidenceName("Melville Park"), new ResidenceAddress("22 Simei Street 1, #10-02"),
                    new BookingList(), getCleanStatusTag("n"), getTagSet("Available"))
        };
    }

    public static ReadOnlyResidenceTracker getSampleResidenceTracker() {
        ResidenceTracker sampleRt = new ResidenceTracker();
        for (Residence sampleResidence : getSampleResidence()) {
            sampleRt.addResidence(sampleResidence);
        }
        return sampleRt;
    }

    /**
     * Returns a CleanStatusTag containing the list of strings given.
     */
    public static CleanStatusTag getCleanStatusTag(String status) {
        return new CleanStatusTag(status);
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
