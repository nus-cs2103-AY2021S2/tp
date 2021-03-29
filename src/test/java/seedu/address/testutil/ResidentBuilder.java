package seedu.address.testutil;

import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;

/**
 * A utility class to help with building Resident objects.
 */
public class ResidentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_YEAR = "1";
    public static final String DEFAULT_ROOM = Room.UNALLOCATED_REGEX;

    private Name name;
    private Phone phone;
    private Email email;
    private Year year;
    private Room room;

    /**
     * Creates a {@code ResidentBuilder} with the default details.
     */
    public ResidentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        year = new Year(DEFAULT_YEAR);
        room = new Room(DEFAULT_ROOM);
    }

    /**
     * Initializes the ResidentBuilder with the data of {@code residentToCopy}.
     */
    public ResidentBuilder(Resident residentToCopy) {
        name = residentToCopy.getName();
        phone = residentToCopy.getPhone();
        email = residentToCopy.getEmail();
        year = residentToCopy.getYear();
        room = residentToCopy.getRoom();
    }

    /**
     * Sets the {@code Name} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    public Resident build() {
        return new Resident(name, phone, email, year, room);
    }

}
