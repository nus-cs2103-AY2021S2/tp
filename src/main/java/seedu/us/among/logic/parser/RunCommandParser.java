package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.logic.commands.RunCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.header.Header;

public class RunCommandParser implements Parser<RunCommand> {

    private static final Logger logger = LogsCenter.getLogger(RunCommandParser.class);

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
                PREFIX_HEADER);

        // handle quick run command
        // format: run url
        if (!argMultimap.arePrefixesPresent(PREFIX_METHOD, PREFIX_ADDRESS)
                &&
                !argMultimap.getPreamble().isEmpty()) {
            Address address = ParserUtil.parseAddress(argMultimap.getPreamble());
            Endpoint endpoint = new Endpoint(address);
            return new RunCommand(endpoint);
        }

        // handle normal run command of wrong format
        if (!argMultimap.arePrefixesPresent(PREFIX_METHOD, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            logger.warning("Error in parsing user input for Run Command");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RunCommand.MESSAGE_USAGE));
        }

        Method method = ParserUtil.parseMethod(argMultimap.getValue(PREFIX_METHOD).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Header> headerList = ParserUtil.parseHeaders(argMultimap.getAllValues(PREFIX_HEADER));
        Data data = ParserUtil.parseData(argMultimap.getValue(PREFIX_DATA).orElse(""));

        Endpoint endpoint = new Endpoint(method, address, data, headerList, new HashSet<>());
        return new RunCommand(endpoint);
    }



}
