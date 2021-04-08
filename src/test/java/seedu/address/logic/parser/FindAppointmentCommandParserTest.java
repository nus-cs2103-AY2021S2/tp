package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.AppointmentPredicateList;
import seedu.address.model.appointment.AppointmentRemarksPredicate;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyClientContactPredicate;
import seedu.address.model.property.PropertyClientEmailPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyRemarksPredicate;
import seedu.address.model.property.PropertyTagsPredicate;
import seedu.address.model.property.PropertyTypePredicate;
import seedu.address.model.property.client.Contact;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;

public class FindAppointmentCommandParserTest {

    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parseEmptyTest() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " this is invalid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseValidKeywordsTest() {
        List<Predicate<Appointment>> predicates = new ArrayList<>();

        predicates.add(new AppointmentContainsKeywordsPredicate(Arrays.asList("John", "Alex")));

        FindAppointmentCommand expectedFindCommand =
                new FindAppointmentCommand(new AppointmentPredicateList(predicates));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/John Alex", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ John \t   Alex ", expectedFindCommand);
    }

    @Test
    public void parseMultipleValidKeywordsTest() {
        List<Predicate<Appointment>> predicates = new ArrayList<>();


        List<AppointmentPredicateList> orList = Collections.singletonList(new AppointmentPredicateList(Arrays.asList(new
                AppointmentContainsKeywordsPredicate(Collections.singletonList("John")),
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("Alex")))));

        FindAppointmentCommand expectedFindCommand =
                new FindAppointmentCommand(new AppointmentPredicateList(predicates, orList));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/John n/Alex", expectedFindCommand);
    }

    @Test
    public void validRemarksTest() {
        List<Predicate<Appointment>> predicates = new ArrayList<>();

        predicates.add(new AppointmentRemarksPredicate("this is a remark"));

        FindAppointmentCommand expected =
                new FindAppointmentCommand(new AppointmentPredicateList(predicates));

        assertParseSuccess(parser, " r/this is a remark", expected);
    }

    @Test
    public void invalidRemarksTest() {
        String expected = "r/ used but no remarks found! \n"
                + Remark.MESSAGE_CONSTRAINTS
                + "\n"
                + FindAppointmentCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " r/ ", expected);
    }

    @Test
    public void validDeadlineTest() throws ParseException {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline("12-09-2021")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " d/12-09-2021", expected);
    }

    @Test
    public void invalidDeadlineTest() {
        String expected = "Wrong deadline format! \n"
                + Deadline.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " d/not deadline ", expected);
    }

    @Test
    public void validTagsTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyTagsPredicate("3 bedrooms, need renovation, need this"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " tags/3 bedrooms, need renovation, need this", expected);

        predicates = new ArrayList<>();
        predicates.add(new PropertyTagsPredicate("3 bedrooms"));
        expected = new FindPropertyCommand(new PropertyPredicateList(predicates));
        assertParseSuccess(parser, " tags/3 bedrooms", expected);
    }

    @Test
    public void invalidTagsTest() {
        String expected = "Wrong tag format! \n"
                + Tag.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " tags/ ", expected);
    }

    @Test
    public void validClientContactTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientContactPredicate("91234567"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " cc/91234567", expected);
    }

    @Test
    public void invalidClientContactTest() {
        String expected = "Wrong client contact format! \n"
                + Contact.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " cc/ ", expected);
    }


    @Test
    public void validClientEmailTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientEmailPredicate("example@gmail.com"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " ce/example@gmail.com", expected);
    }

    @Test
    public void multipleClientEmailTest() {
        String expected = "Too many client emails! Please only use 1 client email. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " ce/abc.example.com ce/cdf@gmail.com", expected);
    }

    @Test
    public void clientNameTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientNamePredicate(Collections.singletonList("bob")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " cn/bob", expected);
    }
}
