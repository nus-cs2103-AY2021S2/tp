package seedu.address.logic.parser.gradeparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;

import seedu.address.logic.commands.gradecommands.AddGradeCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.subject.SubjectName;

/**
 * Parses input arguments and creates a new AddGradeCommand object
 */
public class AddGradeCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGradeCommand
     * and returns an AddGradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT_NAME, PREFIX_GRADED_ITEM,
                        PREFIX_GRADE);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_SUBJECT_NAME,
                PREFIX_GRADED_ITEM,
                PREFIX_GRADE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE));
        }

        SubjectName subjectName =
                ParserUtil.parseSubjectName(argMultimap.getValue(PREFIX_SUBJECT_NAME).get());
        GradedItem gradedItem =
                ParserUtil.parseGradedItem(argMultimap.getValue(PREFIX_GRADED_ITEM).get());
        GradeEnum grade =
                ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());

        Grade gradeToAdd = new Grade(subjectName, gradedItem, grade);

        return new AddGradeCommand(gradeToAdd);
    }
}
