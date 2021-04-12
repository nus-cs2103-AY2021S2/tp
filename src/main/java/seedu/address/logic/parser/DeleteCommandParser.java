package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (args.trim().length() == 0 || args.trim().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        List<String> input = new ArrayList<>();
        String[] splitBySpace = args.split(" ");
        for (int i = 0; i < splitBySpace.length; i++) {
            String[] splitByComma = splitBySpace[i].split(",");
            input.addAll(Arrays.asList(splitByComma));
        }

        boolean isSomeWord = false;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                continue;
            }
            boolean isWord = !StringUtil.isNumbersOnly(input.get(i).trim());
            isSomeWord |= isWord;
        }

        if (isSomeWord) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getLocalizedMessage() + "\n" + DeleteCommand.MESSAGE_USAGE, pe);
        }
    }

}
