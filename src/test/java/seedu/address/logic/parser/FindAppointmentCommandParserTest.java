package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentDate;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDatePredicate;
import seedu.address.model.appointment.AppointmentNamePredicate;
import seedu.address.model.appointment.AppointmentPredicateList;
import seedu.address.model.appointment.AppointmentRemarksPredicate;
import seedu.address.model.appointment.AppointmentTimePredicate;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;

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

        predicates.add(new AppointmentNamePredicate(Arrays.asList("John", "Alex")));

        List<AppointmentPredicateList> orList = Collections.singletonList(
                new AppointmentPredicateList(Collections.singletonList(new
                        AppointmentNamePredicate(Arrays.asList("John", "Alex")))));

        FindAppointmentCommand expectedFindCommand =
                new FindAppointmentCommand(new AppointmentPredicateList(new ArrayList<>(), orList));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/John Alex", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ John \t   Alex ", expectedFindCommand);
    }

    @Test
    public void parseMultipleValidKeywordsTest() {
        List<Predicate<Appointment>> predicates = new ArrayList<>();

        List<AppointmentPredicateList> orList = Collections.singletonList(new AppointmentPredicateList(Arrays.asList(new
                        AppointmentNamePredicate(Collections.singletonList("John")),
                new AppointmentNamePredicate(Collections.singletonList("Alex")))));

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
    public void validDateTest() throws ParseException {
        List<AppointmentPredicateList> predicates = new ArrayList<>();

        predicates.add(new AppointmentPredicateList(
                Collections.singletonList(new AppointmentDatePredicate(parseAppointmentDate("12-09-2021")))));

        FindAppointmentCommand expected =
                new FindAppointmentCommand(new AppointmentPredicateList(new ArrayList<>(), predicates));

        assertParseSuccess(parser, " d/12-09-2021", expected);
    }

    @Test
    public void invalidDateTest() {
        String expected = "Wrong date format! \n"
                + Date.MESSAGE_CONSTRAINTS
                + "\n"
                + FindAppointmentCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " d/not date ", expected);
    }

    @Test
    public void validTimeTest() throws ParseException {
        List<AppointmentPredicateList> predicates = new ArrayList<>();

        predicates.add(new AppointmentPredicateList(
                Collections.singletonList(new AppointmentTimePredicate(parseAppointmentTime("1000")))));

        FindAppointmentCommand expected =
                new FindAppointmentCommand(new AppointmentPredicateList(new ArrayList<>(), predicates));

        assertParseSuccess(parser, " t/1000", expected);
    }

    @Test
    public void invalidTimeTest() {
        String expected = "Wrong time format! \n"
                + Time.MESSAGE_CONSTRAINTS
                + "\n"
                + FindAppointmentCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " t/not time ", expected);
    }
}
