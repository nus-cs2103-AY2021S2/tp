package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Wardrobe;
import seedu.address.model.garment.Garment;

/**
 * A utility class containing a list of {@code Garment} objects to be used in tests.
 */
public class TypicalGarments {

    public static final Garment ALICE = new GarmentBuilder().withName("Alice Pauline")
            .withDressCode("FORMAL").withColour("blue")
            .withSize("53")
            .withDescriptions("friends").build();
    public static final Garment BENSON = new GarmentBuilder().withName("Benson Meier")
            .withDressCode("FORMAL")
            .withColour("blue").withSize("32")
            .withDescriptions("owesMoney", "friends").build();
    public static final Garment CARL = new GarmentBuilder().withName("Carl Kurz").withSize("93")
            .withColour("blue").withDressCode("FORMAL").build();
    public static final Garment DANIEL = new GarmentBuilder().withName("Daniel Meier").withSize("33")
            .withColour("blue").withDressCode("CASUAL").withDescriptions("friends").build();
    public static final Garment ELLE = new GarmentBuilder().withName("Elle Meyer").withSize("24")
            .withColour("blue").withDressCode("CASUAL").build();
    public static final Garment FIONA = new GarmentBuilder().withName("Fiona Kunz").withSize("27")
            .withColour("blue").withDressCode("CASUAL").build();
    public static final Garment GEORGE = new GarmentBuilder().withName("George Best").withSize("42")
            .withColour("blue").withDressCode("ACTIVE").build();

    // Manually added
    public static final Garment HOON = new GarmentBuilder().withName("Hoon Meier").withSize("24")
            .withColour("blue").withDressCode("ACTIVE").build();
    public static final Garment IDA = new GarmentBuilder().withName("Ida Mueller").withSize("31")
            .withColour("blue").withDressCode("ACTIVE").build();

    // Manually added - Garment's details found in {@code CommandTestUtil}
    public static final Garment AMY = new GarmentBuilder().withName(VALID_NAME_AMY).withSize(VALID_SIZE_AMY)
            .withColour(VALID_COLOUR_AMY).withDressCode(VALID_DRESSCODE_AMY).withDescriptions(VALID_DESCRIPTION_FRIEND)
            .build();
    public static final Garment BOB = new GarmentBuilder().withName(VALID_NAME_BOB).withSize(VALID_SIZE_BOB)
            .withColour(VALID_COLOUR_BOB).withDressCode(VALID_DRESSCODE_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND,
                    VALID_DESCRIPTION_FRIEND).build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGarments() {} // prevents instantiation

    /**
     * Returns an {@code Wardrobe} with all the typical garments.
     */
    public static Wardrobe getTypicalWardrobe() {
        Wardrobe ab = new Wardrobe();
        for (Garment garment : getTypicalGarments()) {
            ab.addGarment(garment);
        }
        return ab;
    }

    public static List<Garment> getTypicalGarments() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
