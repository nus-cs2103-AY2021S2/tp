package seedu.address.logic.parser.filterparser;

// import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.AddPersonFilterCommand;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.TutorFilter;
import seedu.address.model.subject.SubjectExperience;
import seedu.address.model.subject.SubjectLevel;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectQualification;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;

public class AddTutorFilterCommandParserTest {
    private AddPersonFilterCommandParser parser = new AddPersonFilterCommandParser();

    private Set<Predicate<Name>> nameFilters;
    private Set<Predicate<Gender>> genderFilters;
    private Set<Predicate<Phone>> phoneFilters;
    private Set<Predicate<Email>> emailFilters;
    private Set<Predicate<Address>> addressFilters;

    private Set<Predicate<SubjectName>> subjectNameFilters;
    private Set<Predicate<SubjectLevel>> subjectLevelFilters;
    private Set<Predicate<SubjectRate>> subjectRateFilters;
    private Set<Predicate<SubjectExperience>> subjectExperienceFilters;
    private Set<Predicate<SubjectQualification>> subjectQualificationFilters;

    @BeforeEach
    public void setUp() {
        this.nameFilters = new LinkedHashSet<>();
        this.genderFilters = new LinkedHashSet<>();
        this.phoneFilters = new LinkedHashSet<>();
        this.emailFilters = new LinkedHashSet<>();
        this.addressFilters = new LinkedHashSet<>();

        this.subjectNameFilters = new LinkedHashSet<>();
        this.subjectLevelFilters = new LinkedHashSet<>();
        this.subjectRateFilters = new LinkedHashSet<>();
        this.subjectExperienceFilters = new LinkedHashSet<>();
        this.subjectQualificationFilters = new LinkedHashSet<>();
    }

    @Test
    public void parse_allFieldsPresent_success() {
        nameFilters.add(new NameFilter("Alice"));
        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters, phoneFilters, emailFilters, addressFilters,
                subjectNameFilters, subjectLevelFilters, subjectRateFilters,
                subjectExperienceFilters, subjectQualificationFilters);

        AddPersonFilterCommand addPersonFilterCommand = new AddPersonFilterCommand(tutorFilter);

        assertParseSuccess(parser, " n/Alice", addPersonFilterCommand);
    }

    // TODO: ParseException Failures
    // @Test
    // public void parse_invalidValue_failure() {
    //     assertParseFailure(parser, " n/-", NameFilter.MESSAGE_CONSTRAINTS);
    // }
}
