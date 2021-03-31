package seedu.booking.testutil;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.person.Person;

/**
 * A utility class to help with building BookingSystem objects.
 * Example usage: <br>
 *     {@code BookingSystem ab = new BookingSystemBuilder().withPerson("John", "Doe").build();}
 */
public class BookingSystemBuilder {

    private BookingSystem bookingSystem;

    public BookingSystemBuilder() {
        bookingSystem = new BookingSystem();
    }

    public BookingSystemBuilder(BookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    /**
     * Adds a new {@code Person} to the {@code BookingSystem} that we are building.
     */
    public BookingSystemBuilder withPerson(Person person) {
        bookingSystem.addPerson(person);
        return this;
    }

    public BookingSystem build() {
        return bookingSystem;
    }
}
