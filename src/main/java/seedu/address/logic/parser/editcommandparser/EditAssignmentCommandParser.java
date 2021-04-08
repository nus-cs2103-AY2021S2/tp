package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editcommand.EditAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Parses input arguments and create a new EditAssignmentCommand object.
 */
public class EditAssignmentCommandParser extends EditCommandParser implements Parser<EditAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAssignmentCommand and
     * returns an EditAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_DEADLINE, PREFIX_ASSIGNMENT, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_ASSIGNMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                    EditAssignmentCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE)
                .filter(Title::isValidTitle)
                .orElseThrow(() -> new ParseException(
                        String.format(Title.MESSAGE_CONSTRAINTS, "Modules"))));
        Module module = new Module(title);

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        int intIndex = index.getOneBased();

        Description descriptionEdit = null;
        LocalDateTime dateEdit = null;
        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            descriptionEdit = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                    .filter(Description::isValidDescription)
                    .orElseThrow(() -> new ParseException(Description.MESSAGE_CONSTRAINTS)));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            dateEdit = argMultimap.getValue(PREFIX_DEADLINE)
                    .map(ParserUtil::parseDeadline)
                    .orElseThrow(() -> new ParseException(Assignment.DATE_CONSTRAINTS));
        }

        return new EditAssignmentCommand(module, intIndex, descriptionEdit, dateEdit);
    }
}
