package seedu.address.testutil;

import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.Residence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalResidences {

    public static final Residence RESIDENCE_A = new ResidenceBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withBookingDetails("Not booked")
            .withTags("valuable").build();
    public static final Residence RESIDENCE_B = new ResidenceBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withBookingDetails("")
            .withTags("needs repair", "popular").build();
    public static final Residence RESIDENCE_C = new ResidenceBuilder().withName("Carl Kurz")
            .withBookingDetails("C booked").withAddress("wall street").build();
    public static final Residence RESIDENCE_D = new ResidenceBuilder().withName("Daniel Meier")
            .withBookingDetails("Clean").withAddress("10th street").withTags("for friends").build();
    public static final Residence RESIDENCE_E = new ResidenceBuilder().withName("Elle Meyer")
            .withBookingDetails("Not cleaned").withAddress("michegan ave").build();
    public static final Residence RESIDENCE_F = new ResidenceBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo").build();
    public static final Residence RESIDENCE_G = new ResidenceBuilder().withName("George Best")
            .withAddress("4th street").build();

    // Manually added
    public static final Residence EXTRA_R1 = new ResidenceBuilder().withName("Hoon Meier")
            .withBookingDetails("Cleaning").withAddress("little india").build();
    public static final Residence EXTRA_R2 = new ResidenceBuilder().withName("Ida Mueller")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Residence AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
