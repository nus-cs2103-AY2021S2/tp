package seedu.address.logic.parser;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ThemeCommandParser implements Parser<ThemeCommand> {
	@Override
	public ThemeCommand parse(String args) throws ParseException {
		String trimmedArgs = args.trim();
		if (trimmedArgs.isEmpty()) {
			throw new ParseException(
					String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE)
			);
		}
		String[] nameKeywords = trimmedArgs.split("\\s+");

		return new ThemeCommand(nameKeywords[0]);
	}
}
