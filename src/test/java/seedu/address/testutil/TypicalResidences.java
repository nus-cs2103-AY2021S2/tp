package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLEAN_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_RESERVED;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ResidenceTracker;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;
import seedu.address.model.residence.Residence;

/**
 * A utility class containing a list of {@code Residence} objects to be used in tests.
 */
public class TypicalResidences {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final Residence RESIDENCE_A = new ResidenceBuilder().withName("Amber Heights")
            .withAddress("123, Jurong West Ave 6, #08-111").withBookingList().withTags("valuable").build();
    public static final Residence RESIDENCE_B = new ResidenceBuilder().withName("Beverly Hills")
            .withAddress("311, Clementi Ave 2, #02-25").withTags("repair", "popular")
            .withBookingList(new ArrayList<Booking>(Arrays.asList(
                    new Booking(new TenantName("Amy"), new Phone("91910606"),
                            LocalDate.parse("03-03-2020", DATE_TIME_FORMATTER),
                            LocalDate.parse("04-03-2020", DATE_TIME_FORMATTER)),
                    new Booking(new TenantName("Bob"), new Phone("90241134"),
                            LocalDate.parse("05-03-2020", DATE_TIME_FORMATTER),
                            LocalDate.parse("06-03-2020", DATE_TIME_FORMATTER))
            ))).withCleanStatusTag("n").build();
    public static final Residence RESIDENCE_C = new ResidenceBuilder().withName("Casuarina Apartment")
            .withAddress("wall street").build();
    public static final Residence RESIDENCE_D = new ResidenceBuilder().withName("Dover Condominium")
            .withAddress("10th street").withTags("forFriends").build();
    public static final Residence RESIDENCE_E = new ResidenceBuilder().withName("Emerald Hills")
            .withAddress("michegan ave").build();
    public static final Residence RESIDENCE_F = new ResidenceBuilder().withName("Floravale")
            .withAddress("little tokyo").withCleanStatusTag("n").build();
    public static final Residence RESIDENCE_G = new ResidenceBuilder().withName("Gem Residences")
            .withAddress("4th street").build();

    // Manually added
    public static final Residence EXTRA_R1 = new ResidenceBuilder().withName("Hillview")
            .withAddress("little india").build();
    public static final Residence EXTRA_R2 = new ResidenceBuilder().withName("iSuites")
            .withAddress("chicago ave").build();

    // Manually added - Residence's details found in {@code CommandTestUtil}
    public static final Residence RESIDENCE1 = new ResidenceBuilder().withName(VALID_NAME_RESIDENCE1)
            .withAddress(VALID_ADDRESS_RESIDENCE1).withBookingList()
            .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_RESERVED).build();
    public static final Residence RESIDENCE2 = new ResidenceBuilder().withName(VALID_NAME_RESIDENCE2)
            .withAddress(VALID_ADDRESS_RESIDENCE2).withBookingList()
            .withCleanStatusTag(VALID_CLEAN_TAG).withTags(VALID_TAG_REPAIR, VALID_TAG_RESERVED).build();

    public static final String KEYWORD_MATCHING_HILLS = "Hills"; // A keyword that matches MEIER

    private TypicalResidences() {} // prevents instantiation

    /**
     * Returns an {@code ResidenceTracker} with all the typical residences.
     */
    public static ResidenceTracker getTypicalResidenceTracker() {
        ResidenceTracker rt = new ResidenceTracker();
        for (Residence residence : getTypicalResidences()) {
            rt.addResidence(residence);
        }
        return rt;
    }

    public static List<Residence> getTypicalResidences() {
        return new ArrayList<>(Arrays.asList(RESIDENCE_A, RESIDENCE_B, RESIDENCE_C,
                RESIDENCE_D, RESIDENCE_E, RESIDENCE_F, RESIDENCE_G));
    }
}
