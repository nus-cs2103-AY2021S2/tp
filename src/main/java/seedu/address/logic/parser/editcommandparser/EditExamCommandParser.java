package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editcommand.EditExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;


/**
 * Parses input arguments and create a new EditAssignmentCommand object.
 */
public class EditExamCommandParser extends EditCommandParser implements Parser<EditExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAssignmentCommand and
     * returns an EditAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_EXAM, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditExamCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE).get());
        Module module = new Module(title);

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EXAM).get());
        int intIndex = index.getOneBased();

        LocalDateTime edit = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DATE).get());

        return new EditExamCommand(module, intIndex, edit);
    }
}
