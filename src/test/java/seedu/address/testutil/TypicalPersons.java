package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Patient ALICE = new PersonBuilder().withName("Alice Pauline").withDateOfBirth("31121996")
            .withGender("F").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBloodType("B-").withHeight("169cm").withWeight("69kg")
            .withTags("friends").build();
    public static final Patient BENSON = new PersonBuilder().withName("Benson Meier").withDateOfBirth("04041990")
            .withGender("M").withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withBloodType("AB+").withHeight("162cm").withWeight("64kg")
            .withTags("owesMoney", "friends").build();
    public static final Patient CARL = new PersonBuilder().withName("Carl Kurz").withDateOfBirth("10062001")
            .withGender("M").withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withBloodType("O+").withHeight("182cm").withWeight("72kg").build();
    public static final Patient DANIEL = new PersonBuilder().withName("Daniel Meier").withDateOfBirth("09091975")
            .withGender("M").withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withBloodType("AB-").withHeight("168cm").withWeight("76kg").withTags("friends").build();
    public static final Patient ELLE = new PersonBuilder().withName("Elle Meyer").withDateOfBirth("18072002")
            .withGender("F").withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withBloodType("A+").withHeight("175cm").withWeight("65kg").build();
    public static final Patient FIONA = new PersonBuilder().withName("Fiona Kunz").withDateOfBirth("29031999")
            .withGender("F").withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withBloodType("AB+").withHeight("160cm").withWeight("55kg").build();
    public static final Patient GEORGE = new PersonBuilder().withName("George Best").withDateOfBirth("01011990")
            .withGender("M").withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withBloodType("O+").withHeight("182cm").withWeight("59kg").build();

    // Manually added
    public static final Patient HOON = new PersonBuilder().withName("Hoon Meier").withDateOfBirth("16121997")
            .withGender("M").withPhone("8482424").withEmail("stefan@example.com").withAddress("little india")
            .withBloodType("B-").withHeight("163cm").withWeight("69kg").build();
    public static final Patient IDA = new PersonBuilder().withName("Ida Mueller").withDateOfBirth("09101993")
            .withGender("F").withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave")
            .withBloodType("AB+").withHeight("169cm").withWeight("62kg").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PersonBuilder().withName(VALID_NAME_AMY).withDateOfBirth(VALID_DOB_AMY)
            .withGender(VALID_GENDER_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withBloodType(VALID_BLOODTYPE_AMY).withHeight(VALID_HEIGHT_AMY)
            .withWeight(VALID_WEIGHT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PersonBuilder().withName(VALID_NAME_BOB).withDateOfBirth(VALID_DOB_BOB)
            .withGender(VALID_GENDER_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withBloodType(VALID_BLOODTYPE_BOB).withHeight(VALID_HEIGHT_BOB)
            .withWeight(VALID_WEIGHT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPersons()) {
            ab.addPerson(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
