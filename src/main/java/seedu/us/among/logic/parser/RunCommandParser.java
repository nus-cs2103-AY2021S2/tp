package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.us.among.logic.commands.RunCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.header.Header;
import seedu.us.among.model.tag.Tag;

public class RunCommandParser implements Parser<RunCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RunCommand
     * and returns a RunCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RunCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_METHOD,
                PREFIX_ADDRESS,
                PREFIX_DATA,
                PREFIX_HEADER,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_METHOD, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RunCommand.MESSAGE_USAGE));
        }

        Method method = ParserUtil.parseMethod(argMultimap.getValue(PREFIX_METHOD).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Header> headerList = ParserUtil.parseHeaders(argMultimap.getAllValues(PREFIX_HEADER));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Endpoint endpoint;

        if (argMultimap.getValue(PREFIX_DATA).isEmpty()) {
            endpoint = new Endpoint(method, address, headerList, tagList);
        } else {
            Data data = ParserUtil.parseData(argMultimap.getValue(PREFIX_DATA).orElse(""));
            endpoint = new Endpoint(method, address, data, headerList, tagList);
        }

        return new RunCommand(endpoint);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
