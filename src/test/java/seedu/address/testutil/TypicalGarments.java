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

<<<<<<< HEAD:src/test/java/seedu/address/testutil/TypicalGarments.java
    public static final Garment ALICE = new GarmentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withColour("alice@example.com")
            .withSize("53")
            .withDescriptions("friends").build();
    public static final Garment BENSON = new GarmentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withColour("johnd@example.com").withSize("32")
            .withDescriptions("owesMoney", "friends").build();
    public static final Garment CARL = new GarmentBuilder().withName("Carl Kurz").withSize("93")
            .withColour("heinz@example.com").withAddress("wall street").build();
    public static final Garment DANIEL = new GarmentBuilder().withName("Daniel Meier").withSize("33")
            .withColour("cornelia@example.com").withAddress("10th street").withDescriptions("friends").build();
    public static final Garment ELLE = new GarmentBuilder().withName("Elle Meyer").withSize("24")
            .withColour("werner@example.com").withAddress("michegan ave").build();
    public static final Garment FIONA = new GarmentBuilder().withName("Fiona Kunz").withSize("27")
            .withColour("lydia@example.com").withAddress("little tokyo").build();
    public static final Garment GEORGE = new GarmentBuilder().withName("George Best").withSize("42")
            .withColour("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Garment HOON = new GarmentBuilder().withName("Hoon Meier").withSize("24")
            .withColour("stefan@example.com").withAddress("little india").build();
    public static final Garment IDA = new GarmentBuilder().withName("Ida Mueller").withSize("31")
            .withColour("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Garment's details found in {@code CommandTestUtil}
    public static final Garment AMY = new GarmentBuilder().withName(VALID_NAME_AMY).withSize(VALID_SIZE_AMY)
            .withColour(VALID_COLOUR_AMY).withAddress(VALID_ADDRESS_AMY).withDescriptions(VALID_DESCRIPTION_FRIEND)
            .build();
    public static final Garment BOB = new GarmentBuilder().withName(VALID_NAME_BOB).withSize(VALID_SIZE_BOB)
            .withColour(VALID_COLOUR_BOB).withAddress(VALID_ADDRESS_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND,
=======
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withDressCode("FORMAL").withColour("alice@example.com")
            .withSize("53")
            .withDescriptions("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withDressCode("FORMAL")
            .withColour("johnd@example.com").withSize("32")
            .withDescriptions("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withSize("93")
            .withColour("heinz@example.com").withDressCode("FORMAL").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withSize("33")
            .withColour("cornelia@example.com").withDressCode("CASUAL").withDescriptions("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withSize("24")
            .withColour("werner@example.com").withDressCode("CASUAL").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withSize("27")
            .withColour("lydia@example.com").withDressCode("CASUAL").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withSize("42")
            .withColour("anna@example.com").withDressCode("ACTIVE").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withSize("24")
            .withColour("stefan@example.com").withDressCode("ACTIVE").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withSize("31")
            .withColour("hans@example.com").withDressCode("ACTIVE").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withSize(VALID_SIZE_AMY)
            .withColour(VALID_COLOUR_AMY).withDressCode(VALID_DRESSCODE_AMY).withDescriptions(VALID_DESCRIPTION_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withSize(VALID_SIZE_BOB)
            .withColour(VALID_COLOUR_BOB).withDressCode(VALID_DRESSCODE_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND,
>>>>>>> a619ff895d28ff145a1161fbe2a7e1656407e12f:src/test/java/seedu/address/testutil/TypicalPersons.java
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
