package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDY_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDY_LEVEL_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
            .withRelationship("Mother")
                        .addSession(
                                new SessionBuilder().withSessionDate("2020-01-01", "12:00").build()
                        )
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withStudyLevel("Primary 2").withGuardianPhone("81902144").withRelationship("Father")
                        .addSession(
                                new SessionBuilder().withSessionDate("2020-01-02", "12:00").build()
                        )
            .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withStudyLevel("University CS1101S").withGuardianPhone("98621032").withRelationship("Mother")
            .build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withStudyLevel("Junior College 2").withGuardianPhone("97213021").withRelationship("Father")
            .build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("94822242")
            .withEmail("werner@example.com").withAddress("michigan ave")
            .withStudyLevel("Primary 2").withGuardianPhone("92134012").withRelationship("Mother")
            .build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("94824272")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withStudyLevel("Sec 2").withGuardianPhone("99021234").withRelationship("Mother")
            .build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("94824422")
            .withEmail("anna@example.com").withAddress("4th street")
            .withStudyLevel("Sec 3").withGuardianPhone("87620000").withRelationship("Father")
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("84824242")
            .withEmail("stefan@example.com").withAddress("little india")
            .withStudyLevel("Secondary 4").withGuardianPhone("98110022").withRelationship("Father").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("84821312")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withStudyLevel("JC 4").withGuardianPhone("87621200").withRelationship("Uncle").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withStudyLevel(VALID_STUDY_LEVEL_AMY)
            .withGuardianPhone(VALID_GUARDIAN_PHONE_AMY).withRelationship(VALID_RELATIONSHIP_AMY)
            .build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withStudyLevel(VALID_STUDY_LEVEL_BOB)
            .withGuardianPhone(VALID_GUARDIAN_PHONE_BOB).withRelationship(VALID_RELATIONSHIP_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
