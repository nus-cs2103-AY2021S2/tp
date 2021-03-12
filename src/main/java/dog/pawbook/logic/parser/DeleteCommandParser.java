package dog.pawbook.logic.parser;

import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.DeleteCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public abstract class DeleteCommandParser<T extends DeleteCommand> implements Parser<T> {
    /**
     * Parse the argument into index is possible.
     */
    protected Index extractIndex(String args) throws ParseException {
        return ParserUtil.parseIndex(args);
    }

    protected abstract String getUsageText();
}
