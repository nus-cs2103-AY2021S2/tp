package seedu.address.logic.parser.filterparser;

import static seedu.address.commons.core.Messages.MESSAGE_NO_FILTER_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.filtercommands.AddPersonFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.AddressFilter;
import seedu.address.model.filter.EmailFilter;
import seedu.address.model.filter.GenderFilter;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.PhoneFilter;
import seedu.address.model.filter.SubjectExperienceFilter;
import seedu.address.model.filter.SubjectLevelFilter;
import seedu.address.model.filter.SubjectNameFilter;
import seedu.address.model.filter.SubjectQualificationFilter;
import seedu.address.model.filter.SubjectRateFilter;
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

/**
 * Parses input arguments and creates a new AddPersonFilterCommand object
 */
public class AddPersonFilterCommandParser implements Parser<AddPersonFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonFilterCommand
     * and returns an AddPersonFilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SUBJECT_NAME, PREFIX_RATE, PREFIX_EDUCATION_LEVEL,
                        PREFIX_YEAR, PREFIX_QUALIFICATION);

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>(
                argMultimap.getAllValues(PREFIX_NAME).stream()
                        .map(NameFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<Gender>> genderFilters = new LinkedHashSet<Predicate<Gender>>(
                argMultimap.getAllValues(PREFIX_GENDER).stream()
                        .map(GenderFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<Phone>> phoneFilters = new LinkedHashSet<Predicate<Phone>>(
                argMultimap.getAllValues(PREFIX_PHONE).stream()
                        .map(PhoneFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<Email>> emailFilters = new LinkedHashSet<Predicate<Email>>(
                argMultimap.getAllValues(PREFIX_EMAIL).stream()
                        .map(EmailFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<Address>> addressFilters = new LinkedHashSet<Predicate<Address>>(
                argMultimap.getAllValues(PREFIX_ADDRESS).stream()
                        .map(AddressFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<SubjectName>> subjectNameFilters = new LinkedHashSet<Predicate<SubjectName>>(
                argMultimap.getAllValues(PREFIX_SUBJECT_NAME).stream()
                        .map(SubjectNameFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<SubjectLevel>> subjectLevelFilters = new LinkedHashSet<Predicate<SubjectLevel>>(
                argMultimap.getAllValues(PREFIX_EDUCATION_LEVEL).stream()
                        .map(SubjectLevelFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<SubjectRate>> subjectRateFilters = new LinkedHashSet<Predicate<SubjectRate>>(
                argMultimap.getAllValues(PREFIX_RATE).stream()
                        .map(SubjectRateFilter::new)
                        .collect(Collectors.toList()));

        Set<Predicate<SubjectExperience>> subjectExperienceFilters =
                new LinkedHashSet<Predicate<SubjectExperience>>(
                        argMultimap.getAllValues(PREFIX_YEAR).stream()
                                .map(SubjectExperienceFilter::new)
                                .collect(Collectors.toList()));

        Set<Predicate<SubjectQualification>> subjectQualificationFilters =
                new LinkedHashSet<Predicate<SubjectQualification>>(
                        argMultimap.getAllValues(PREFIX_QUALIFICATION).stream()
                                .map(SubjectQualificationFilter::new)
                                .collect(Collectors.toList()));

        if (!CollectionUtil.isAnyNotEmpty(
                nameFilters,
                genderFilters,
                phoneFilters,
                emailFilters,
                addressFilters,
                subjectNameFilters,
                subjectLevelFilters,
                subjectRateFilters,
                subjectExperienceFilters,
                subjectQualificationFilters)) {
            throw new ParseException(MESSAGE_NO_FILTER_PROVIDED);
        }

        // TODO: Throw ParseException
        // TODO: Maybe switch to using ParserUtil

        TutorFilter tutorFilter = new TutorFilter(nameFilters,
                genderFilters,
                phoneFilters,
                emailFilters,
                addressFilters,
                subjectNameFilters,
                subjectLevelFilters,
                subjectRateFilters,
                subjectExperienceFilters,
                subjectQualificationFilters);

        return new AddPersonFilterCommand(tutorFilter);
    }
}
