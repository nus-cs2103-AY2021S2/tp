package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HALL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindVenueCommand;
import seedu.booking.model.booking.VenueNameContainsKeywordsPredicate;
import seedu.booking.model.venue.Venue;

public class FindVenueCommandParserTest {

    private FindVenueCommandParser parser = new FindVenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindVenueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindVenueCommand() {
        List<Predicate<Venue>> predicateList = new ArrayList<>();
        VenueNameContainsKeywordsPredicate venueNamePredicate =
                new VenueNameContainsKeywordsPredicate(Arrays.asList("Victoria", "Hall"));
        predicateList.add(venueNamePredicate);

        // no leading and trailing whitespaces
        FindVenueCommand expectedFindCommand =
                new FindVenueCommand(predicateList);
        assertParseSuccess(parser, VENUE_NAME_DESC_HALL, expectedFindCommand);

        // multiple whitespaces in front and behind of keywords
        assertParseSuccess(parser, " " + PREFIX_VENUE + "\n \n Victoria Hall \t", expectedFindCommand);
    }

}

