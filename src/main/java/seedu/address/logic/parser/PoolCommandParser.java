package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.tag.Tag;

public class PoolCommandParser implements Parser<PoolCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PoolCommand
     * and returns an PoolCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return The PoolCommand created from parsing the arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public PoolCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_TRIPDAY, PREFIX_TRIPTIME, PREFIX_COMMUTER, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_TRIPDAY, PREFIX_TRIPTIME,
                PREFIX_COMMUTER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PoolCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Set<Index> commuterSet = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_COMMUTER));
        TripDay tripDay = ParserUtil.parseTripDay(argMultimap.getValue(PREFIX_TRIPDAY).get());
        TripTime tripTime = ParserUtil.parseTripTime(argMultimap.getValue(PREFIX_TRIPTIME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Driver driver = new Driver(name, phone);

        return new PoolCommand(driver, commuterSet, tripDay, tripTime, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
