package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_EXPERIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_LEVEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_QUALIFICATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_RATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TutorBook;
import seedu.address.model.tutor.Tutor;

/**
 * A utility class containing a list of {@code Tutor} objects to be used in tests.
 */
public class TypicalTutors {

    public static final Tutor ALICE =
            new TutorBuilder().withName("Alice Tan").withGender("Female")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withNotes("Some notes about Alice")
            .withFavourite(true)
            .withSubject("Mathematics", "Secondary 4", "60", "5", "A-Level")
            .withTags("friends").build();
    public static final Tutor BENSON = new TutorBuilder().withName("Benson Meier").withGender("Male")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withNotes("")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withSubject("English", "Secondary 4", "60", "4", "A-Level")
            .withSubject("Geography", "Secondary 4", "60", "4", "A-Level")
            .withTags("owesMoney", "friends").build();
    public static final Tutor CARL = new TutorBuilder().withName("Carl Kurz").withGender("Male")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withNotes("Some notes about Carl")
            .withSubject("English", "Secondary 3", "50", "5", "A-Level")
            .withSubject("Literature", "Secondary 3", "50", "5", "A-Level")
            .build();
    public static final Tutor DANIEL = new TutorBuilder().withName("Daniel Meier").withGender("Male")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withNotes("Some notes about Daniel")
            .withSubject("Physics", "Secondary 3", "50", "6", "A-Level")
            .withTags("friends").build();
    public static final Tutor ELLE = new TutorBuilder().withName("Elle Meyer").withGender("Female")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withNotes("Some notes about Elle")
            .withSubject("Geography", "Secondary 2", "40", "3", "A-Level")
            .build();
    public static final Tutor FIONA = new TutorBuilder().withName("Fiona Kunz").withGender("Female")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withNotes("Some notes about Fiona")
            .withSubject("History", "Secondary 2", "40", "7", "A-Level")
            .build();
    public static final Tutor JOHN =
            new TutorBuilder().withName("John Doe").withGender("Male")
                    .withNotes("Some notes about John")
                    .withPhone("9482442").withEmail("johndoe@example.com").withAddress("4th street")
                    .withSubject("Chemistry", "Secondary 2",
                    "40", "4", "A-Level").build();
    public static final Tutor GEORGE = new TutorBuilder().withName("George Best").withGender("Male")
            .withNotes("Some notes about George")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withSubject("Chemistry", "Secondary 2", "40", "4", "A-Level")
            .build();

    // Manually added
    public static final Tutor HOON = new TutorBuilder().withName("Hoon Meier").withGender("Female")
            .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india").build();
    public static final Tutor IDA = new TutorBuilder().withName("Ida Mueller").withGender("Female")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Tutor AMY = new TutorBuilder().withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .build();
    public static final Tutor BOB = new TutorBuilder().withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSubject(
                    VALID_SUBJECT_NAME,
                    VALID_SUBJECT_LEVEL,
                    VALID_SUBJECT_RATE,
                    VALID_SUBJECT_EXPERIENCE,
                    VALID_SUBJECT_QUALIFICATION)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTutors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static TutorBook getTypicalTutorBook() {
        TutorBook ab = new TutorBook();
        for (Tutor tutor : getTypicalPersons()) {
            ab.addTutor(tutor);
        }
        return ab;
    }

    public static List<Tutor> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE,
                FIONA, JOHN));
    }
}
