//package seedu.address.testutil;
//
//import java.time.LocalDate;
//
//import seedu.address.model.booking.Booking;
//import seedu.address.model.booking.TenantName;
//import seedu.address.model.booking.Phone;
//
///**
// * A utility class to help with building Booking objects.
// */
//public class BookingListBuilder {
//
//    public static final String DEFAULT_NAME = "Amy Bee";
//    public static final String DEFAULT_PHONE = "85355255";
//    public static final LocalDate DEFAULT_START = LocalDate.of(2021, 3, 22);
//    public static final LocalDate DEFAULT_END = LocalDate.of(2021, 3, 25);
//
//    private TenantName name;
//    private Phone phone;
//    private LocalDate start;
//    private LocalDate end;
//
//    /**
//     * Creates a {@code BookingBuilder} with the default details.
//     */
//    public BookingListBuilder() {
//        name = new TenantName(DEFAULT_NAME);
//        phone = new Phone(DEFAULT_PHONE);
//        start = DEFAULT_START;
//        end = DEFAULT_END;
//    }
//
//    /**
//     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
//     */
//    public BookingListBuilder(Booking bookingToCopy) {
//        name = bookingToCopy.getName();
//        phone = bookingToCopy.getPhone();
//        start = bookingToCopy.getStart();
//        end = bookingToCopy.getEnd();
//    }
//
//    /**
//     * Sets the {@code TenantName} of the {@code Booking} that we are building.
//     */
//    public BookingListBuilder withName(String name) {
//        this.name = new TenantName(name);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Booking} that we are building.
//     */
//    public BookingListBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Start} of the {@code Booking} that we are building.
//     */
//    public BookingListBuilder withStart(LocalDate start) {
//        this.start = start;
//        return this;
//    }
//
//    /**
//     * Sets the {@code End} of the {@code Booking} that we are building.
//     */
//    public BookingListBuilder withEnd(LocalDate end) {
//        this.end = end;
//        return this;
//    }
//
//    public Booking build() {
//        return new Booking(name, phone, start, end);
//    }
//
//}
