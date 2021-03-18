package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModulePlanner;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.testutil.typicalmodules.ModuleBuilder;
/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalRemindMe {
    public static final LocalDateTime DATE_1 = LocalDateTime.parse(VALID_EXAM_DATETIME_1,
            Exam.EXAM_DATE_FORMATTER);
    public static final LocalDateTime DATE_2 = LocalDateTime.parse(VALID_EXAM_DATETIME_2,
            Exam.EXAM_DATE_FORMATTER);

    public static final Module MOD_1 = new ModuleBuilder().withTitle("MOD 1").build();
    public static final Module MOD_2 = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
    public static final Module MOD_1_WITH_ASSIGNMENTS =
            new ModuleBuilder()
                    .withTitle("MOD 1")
                    .withAssignments(VALID_ASSIGNMENT_DESCRIPTION_1, VALID_ASSIGNMENT_DESCRIPTION_2)
                    .build();
    public static final Module MOD_1_WITH_EXAMS =
            new ModuleBuilder()
                    .withTitle("MOD 1")
                    .withExams(VALID_EXAM_DATETIME_1, VALID_EXAM_DATETIME_2)
                    .build();

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRemindMe() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ModulePlanner getTypicalRemindMe() {
        ModulePlanner mp = new ModulePlanner();
        mp.addModule(MOD_1);

        for (Person person : getTypicalPersons()) {
            mp.addPerson(person);
        }

        return mp;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }


}
