package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.logic.commands.RequestCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.Request;

public class RequestCommandParser implements Parser<RequestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RequestCommand
     * and returns an RequestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RequestCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REQUEST);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RequestCommand.MESSAGE_USAGE), ive);
        }

        String request = argMultimap.getValue(PREFIX_REQUEST).orElse("");

        return new RequestCommand(index, new Request(request));
    }
}
