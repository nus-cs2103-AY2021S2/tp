package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESIDENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESIDENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.StudentBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withMatric("A3456789D")
            .withFaculty("COM").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withVacStatus("not vaccinated").withMedDetails("shellfish allergy")
            .withSchoolRes("RVRC").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withMatric("A4567890E")
            .withFaculty("DNUS").withSchoolRes("RVRC").withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withVacStatus("not vaccinated").withMedDetails("none")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withMatric("A5678901F")
            .withFaculty("USP").withPhone("95352563").withSchoolRes("USP").withEmail("heinz@example.com")
            .withVacStatus("vaccinated").withMedDetails("none").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withFaculty("SCALE")
            .withPhone("87652533").withSchoolRes("RVRC").withMatric("A6789012G").withEmail("cornelia@example.com")
            .withAddress("10th street").withMedDetails("pollen allergy").withVacStatus("vaccinated").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withFaculty("BIZ").withPhone("9482224")
            .withSchoolRes("RVRC").withMatric("A7890123H").withEmail("werner@example.com")
            .withVacStatus("not vaccinated").withMedDetails("none").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withFaculty("ENG")
            .withPhone("9482427").withSchoolRes("RVRC").withMatric("A8901234I").withEmail("lydia@example.com")
            .withVacStatus("vaccinated").withMedDetails("bee sting allergy").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withFaculty("MED")
            .withPhone("9482442").withSchoolRes("RVRC").withMatric("A9012345J").withEmail("anna@example.com")
            .withVacStatus("not vaccinated").withMedDetails("none").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withMatric("A0126563B").withMedDetails("none").withSchoolRes("USP").withEmail("stefan@example.com")
            .withFaculty("SDE").withVacStatus("vaccinated").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withMatric("A6754321K").withMedDetails("none")
            .withSchoolRes("USP").withFaculty("SDE").withVacStatus("vaccinated").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withFaculty(VALID_FACULTY_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withMedDetails(VALID_DETAILS_AMY).withMatric(VALID_MATRIC_AMY).withVacStatus(VALID_STATUS_AMY)
            .withSchoolRes(VALID_RESIDENCE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withFaculty(VALID_FACULTY_BOB)
            .withVacStatus(VALID_STATUS_BOB).withMedDetails(VALID_DETAILS_BOB).withMatric(VALID_MATRIC_BOB)
            .withSchoolRes(VALID_RESIDENCE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static StudentBook getTypicalAddressBook() {
        StudentBook ab = new StudentBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
