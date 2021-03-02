package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;

/**
 * A utility class containing a list of {@code Resident} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Resident ALICE = new ResidentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withRoom("#01-23")
            .build();
    public static final Resident BENSON = new ResidentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withRoom("#02-23")
            .build();
    public static final Resident CARL = new ResidentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withRoom(Room.UNALLOCATED_REGEX).build();
    public static final Resident DANIEL = new ResidentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withRoom("#03-23")
            .build();
    public static final Resident ELLE = new ResidentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com")
            .withRoom("#04-23")
            .build();
    public static final Resident FIONA = new ResidentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com")
            .withRoom("#05-23")
            .build();
    public static final Resident GEORGE = new ResidentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com")
            .withRoom("#06-23")
            .build();

    // Manually added
    public static final Resident HOON = new ResidentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRoom("#07-23").build();
    public static final Resident IDA = new ResidentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withRoom("#08-23").build();

    // Manually added - Resident's details found in {@code CommandTestUtil}
    public static final Resident AMY = new ResidentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY).build();
    public static final Resident BOB = new ResidentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withRoom(VALID_ROOM_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Resident resident : getTypicalPersons()) {
            ab.addResident(resident);
        }
        return ab;
    }

    public static List<Resident> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
