package seedu.plan.logic.parser;

import static seedu.plan.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.logic.commands.HistoryCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

public class HistoryCommandParser implements Parser<HistoryCommand> {
    private final Logger logger = LogsCenter.getLogger(HistoryCommandParser.class);


    /**
     * Parses a History command
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public HistoryCommand parse(String args) throws ParseException {
        logger.info("----------------[PARSE][START]");
        if (!args.isBlank()) {
            logger.info("----------------[PARSE][ADDITIONAL INPUTS SPECIFIED][" + args +"]");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE));
        }
        logger.info("----------------[PARSE][END][" + args + "]");
        return new HistoryCommand();
    }
}
