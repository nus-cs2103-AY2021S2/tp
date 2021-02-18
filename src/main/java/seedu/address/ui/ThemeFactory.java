package seedu.address.ui;

import seedu.address.commons.util.JsonUtil;

import java.io.IOException;

public class ThemeFactory {
	private static final String DEFAULT_FOREGROUND = "#f8f8f2";
	private static final String DEFAULT_BACKGROUND = "#272822";
	private static final String[] DEFAULT_COLOR;

	static {
		DEFAULT_COLOR = new String[] {
				"#272822",
				"#f92672",
				"#a6e22e",
				"#f4bf75",
				"#66d9ef",
				"#ae81ff",
				"#a1efe4",
				"#f8f8f2",
				"#75715e",
				"#f92672",
				"#a6e22e",
				"#f4bf75",
				"#66d9ef",
				"#ae81ff",
				"#a1efe4",
				"#f9f8f5"
		};
	}

	public static Theme build(String json) {
		Theme theme;
		try {
			theme = JsonUtil.fromJsonString(json, Theme.class);
		} catch (IOException exception) {
			theme = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);
		}
		return theme;
	}
}
