package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.time.LocalDateTime;

import seedu.address.logic.commands.addcommand.AddAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddAssignmentCommand object
 */
public class AddAssignmentCommandParser extends AddCommandParser implements Parser<AddAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAssignmentCommand
     * and returns an AddAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_ASSIGNMENT, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE)
                .filter(Title::isValidTitle)
                .orElseThrow(() -> new ParseException(
                        String.format(Title.MESSAGE_CONSTRAINTS, "Modules"))));
        Module module = new Module(title);

        Description description = ParserUtil.parseDescription(argMultimap
                .getValue(PREFIX_ASSIGNMENT)
                .filter(Description::isValidDescription)
                .orElseThrow(() -> new ParseException(Assignment.DESCRIPTION_CONSTRAINTS)));
        LocalDateTime deadline = argMultimap.getValue(PREFIX_DEADLINE)
                .map(ParserUtil::parseDeadline)
                .orElseThrow(() -> new ParseException(Assignment.DATE_CONSTRAINTS));
        Tag tag = new Tag(title.modTitle.replaceAll(" ", ""));
        Assignment assignment = new Assignment(description, deadline, tag);

        return new AddAssignmentCommand(module, assignment);
    }
}

