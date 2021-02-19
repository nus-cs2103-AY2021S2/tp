package seedu.address.logic.parser;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.Theme;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
