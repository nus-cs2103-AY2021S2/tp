package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.logic.commands.DeleteFrCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteFrCommandParser implements BudgetBabyCommandParser<DeleteFrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFrCommand
     * and returns a DeleteFrCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFrCommand parse(String args) throws ParseException {
        try {
            String[] argList = args.trim().split(" ");
            List<Index> indices = new ArrayList<>();
            for (String arg : argList) {
                Index index = ParserUtil.parseIndex(arg);
                indices.add(index);
            }
            Collections.sort(indices);
            return new DeleteFrCommand(indices);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFrCommand.MESSAGE_USAGE), pe);
        }
    }

}
