package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_METHOD,
                PREFIX_ADDRESS,
                PREFIX_DATA,
                PREFIX_HEADER,
                PREFIX_TAG);

        if (!argMultimap.arePrefixesPresent(PREFIX_METHOD, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
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

        return new AddCommand(endpoint);
    }

}
