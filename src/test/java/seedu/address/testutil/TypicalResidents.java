package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;

/**
 * A utility class containing a list of {@code Resident} objects to be used in tests.
 */
public class TypicalResidents {

    public static final Resident ALICE = new ResidentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withYear("1")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();
    public static final Resident BENSON = new ResidentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withYear("2")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();
    public static final Resident CARL = new ResidentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withYear("3").withRoom(Room.UNALLOCATED_REGEX).build();
    public static final Resident DANIEL = new ResidentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withYear("4")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();
    public static final Resident ELLE = new ResidentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com")
            .withYear("5")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();
    public static final Resident FIONA = new ResidentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com")
            .withYear("1")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();
    public static final Resident GEORGE = new ResidentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com")
            .withYear("3")
            .withRoom(Room.UNALLOCATED_REGEX)
            .build();

    // Manually added
    public static final Resident HOON = new ResidentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withYear("1").withRoom(Room.UNALLOCATED_REGEX).build();
    public static final Resident IDA = new ResidentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withYear("2").withRoom(Room.UNALLOCATED_REGEX).build();

    // Manually added - Resident's details found in {@code CommandTestUtil}
    public static final Resident AMY = new ResidentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withYear(VALID_YEAR_AMY).withRoom(VALID_ROOM_AMY).build();
    public static final Resident BOB = new ResidentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withYear(VALID_YEAR_BOB)
            .withRoom(VALID_ROOM_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalResidents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical residents.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Resident resident : getTypicalResidents()) {
            ab.addResident(resident);
        }
        return ab;
    }

    public static List<Resident> getTypicalResidents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
