package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final LocalDate DEFAULT_START = LocalDate.of(2021, 3, 22);
    public static final LocalDate DEFAULT_END = LocalDate.of(2021, 3, 25);

    private TenantName tenantName;
    private Phone phone;
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        tenantName = new TenantName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        start = DEFAULT_START;
        end = DEFAULT_END;
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        tenantName = bookingToCopy.getTenantName();
        phone = bookingToCopy.getPhone();
        start = bookingToCopy.getStart();
        end = bookingToCopy.getEnd();
    }

    /**
     * Sets the {@code TenantName} of the {@code Booking} that we are building.
     */
    public BookingBuilder withName(String name) {
        this.tenantName = new TenantName(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Booking} that we are building.
     */
    public BookingBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Start} of the {@code Booking} that we are building.
     */
    public BookingBuilder withStart(LocalDate start) {
        this.start = start;
        return this;
    }

    /**
     * Sets the {@code End} of the {@code Booking} that we are building.
     */
    public BookingBuilder withEnd(LocalDate end) {
        this.end = end;
        return this;
    }

    public Booking build() {
        return new Booking(tenantName, phone, start, end);
    }

}
