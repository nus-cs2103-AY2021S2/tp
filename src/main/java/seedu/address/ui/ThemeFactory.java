package seedu.address.ui;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.ui.exceptions.InvalidThemeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

public class ThemeFactory {

	private static final String DEFAULT_FOREGROUND = "#f8f8f2";
	private static final String DEFAULT_BACKGROUND = "#272822";
	private static final String[] DEFAULT_COLOR;

	static {
		DEFAULT_COLOR = new String[] {
				"#272822", "#f92672", "#a6e22e", "#f4bf75",
				"#66d9ef", "#ae81ff", "#a1efe4", "#f8f8f2",
				"#75715e", "#f92672", "#a6e22e", "#f4bf75",
				"#66d9ef", "#ae81ff", "#a1efe4", "#f9f8f5"
		};
	}

	public static Theme load(Path path) throws InvalidThemeException, FileNotFoundException, DataConversionException {
		Optional<Theme> optionalTheme = JsonUtil.readJsonFile(path, Theme.class);
		if (optionalTheme.isEmpty()) {
			throw new FileNotFoundException();
		}
		if (!optionalTheme.get().isValid()) {
			throw new InvalidThemeException("Invalid theme supplied");
		} else {
			return optionalTheme.get();
		}
	}

	public static Theme getDefaultTheme() {
		return new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);
	}
}
