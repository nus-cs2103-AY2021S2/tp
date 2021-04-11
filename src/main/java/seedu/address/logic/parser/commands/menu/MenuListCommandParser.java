package seedu.address.logic.parser.commands.menu;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;

import seedu.address.logic.commands.menu.MenuListAvailableCommand;
import seedu.address.logic.commands.menu.MenuListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class MenuListCommandParser implements Parser<MenuListCommand> {

    /**
     * Parses {@code userInput} and lists either all available menu items or only those available
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public MenuListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALL);

        if (argMultimap.getValue(PREFIX_ALL).isEmpty()) {
            return new MenuListCommand();
        } else {
            return new MenuListAvailableCommand();
        }
    }
}
