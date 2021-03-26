package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeDebtCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Debt;

public class ChangeDebtParser implements Parser<ChangeDebtCommand> {

    private final boolean isAdd;

    /**
     * Constructs the ChangeDebtParser.
     * @param isAdd true if adding debt, otherwise will subtract.
     */
    public ChangeDebtParser(boolean isAdd) {
        this.isAdd = isAdd;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeDebtCommand
     * and returns an ChangeDebtCommand object for execution.
     * @param args arguments
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ChangeDebtCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argArr = args.trim().split("\\s+");

        if (argArr.length < 2) {
            if (isAdd) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeDebtCommand.MESSAGE_USAGE_ADD));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeDebtCommand.MESSAGE_USAGE_SUBTRACT));
            }
        }
        Index index = ParserUtil.parseIndex(argArr[0]);
        Debt debt = ParserUtil.parseDebt(argArr[1]);
        if (debt.value < 0) {
            if (isAdd) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeDebtCommand.MESSAGE_USAGE_ADD));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeDebtCommand.MESSAGE_USAGE_SUBTRACT));
            }
        }
        return new ChangeDebtCommand(index, debt, isAdd);
    }
}


