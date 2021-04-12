package seedu.plan.logic.parser;

import static seedu.plan.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.commons.core.Messages;
import seedu.plan.logic.commands.InfoCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InfoCommand object
 */

public class InfoCommandParser implements Parser<InfoCommand> {

    public static final Logger LOGGER = LogsCenter.getLogger(InfoCommand.class);

    /**
     * Parses the given {@code String} of arguments in the context of the InfoCommand
     * and returns an InfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InfoCommand parse(String args) throws ParseException {
        LOGGER.info("----------------[EXECUTE][START][INFOCOMMANDPARSER]");
        if (args.equals("")) {
            LOGGER.info("[EXECUTION SUCCESS][NO ARGUMENT][INFO COMMAND CREATED]");
            return new InfoCommand();
        } else {
            assert args.charAt(0) == ' ' : "Prefix parsing error";
            LOGGER.info("[CHECKING IF ARGUMENTS ARE FORMATTED PROPERLY]");
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE);
            if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                    || !argMultimap.getPreamble().isEmpty()) {
                LOGGER.info("[WRONGLY FORMATTED ARGUMENTS FOUND][ERROR MESSAGE THROWN]");
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        InfoCommand.MESSAGE_USAGE));
            }
            LOGGER.info("[ARGUMENT FORMATTED PROPERLY]");
            String moduleCode = argMultimap.getValue(PREFIX_MODULE_CODE).get();

            assert moduleCode != null : "Error getting module code";
            assert moduleCode.length() > 0 : "Empty module code";
            LOGGER.info("[EXECUTION SUCCESS][ARGUMENT FOUND][INFO COMMAND CREATED WITH MODULE CODE]");
            return new InfoCommand(moduleCode);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        assert argumentMultimap != null : "Missing argumentMultimap";
        assert prefixes.length != 0 : "No prefixes entered for matching";
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent()
                && !argumentMultimap.getValue(prefix).get().equals(""));
    }
}
