package seedu.iscam.logic.parser;

import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.parser.exceptions.ParseException;

public interface BookParser {
    Command parseCommand(String userInput) throws ParseException;
}
