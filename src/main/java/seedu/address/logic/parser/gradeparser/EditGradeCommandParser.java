package seedu.address.logic.parser.gradeparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.gradecommands.EditGradeCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditGradeCommand object
 */
public class EditGradeCommandParser implements Parser<EditGradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditGradeCommand
     * and returns an EditGradeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT_NAME, PREFIX_GRADED_ITEM,
                        PREFIX_GRADE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGradeCommand.MESSAGE_USAGE), pe);
        }

        EditGradeCommand.EditGradeDescriptor editGradeDescriptor =
                new EditGradeCommand.EditGradeDescriptor();
        if (argMultimap.getValue(PREFIX_SUBJECT_NAME).isPresent()) {
            editGradeDescriptor.setSubjectName(
                    ParserUtil.parseSubjectName(argMultimap.getValue(PREFIX_SUBJECT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADED_ITEM).isPresent()) {
            editGradeDescriptor.setGradedItem(
                    ParserUtil.parseGradedItem(argMultimap.getValue(PREFIX_GRADED_ITEM).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editGradeDescriptor.setGrade(
                    ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        if (!editGradeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditGradeCommand(index, editGradeDescriptor);
    }
}
