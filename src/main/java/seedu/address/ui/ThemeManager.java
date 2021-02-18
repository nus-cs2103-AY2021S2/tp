package seedu.address.ui;

import javafx.scene.Scene;
import seedu.address.MainApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ThemeManager {
	private static String CSS_TEMPLATE;
	private static String css;
	private static Theme theme = null;
	private static String tmpUrl = null;

	static {
		ThemeManager.theme = ThemeFactory.build("");
		InputStream templateStream = MainApp.class.getResourceAsStream("/view/Template.css");
		ThemeManager.CSS_TEMPLATE = new BufferedReader(new InputStreamReader(templateStream))
				.lines().collect(Collectors.joining("\n"));
		ThemeManager.updateCss();
	}

	static String getThemePath() {
		return ThemeManager.tmpUrl;
	}

	public static void setTheme(Theme newTheme) {
		if (!newTheme.equals(ThemeManager.theme)) {
			ThemeManager.updateCss();
		}
		ThemeManager.theme = newTheme;
	}

	public static String getCss() {
	 	return ThemeManager.css;
	}

	private static void updateCss() {
		ThemeManager.css = ThemeManager.CSS_TEMPLATE;
		ThemeManager.css = ThemeManager.css.replaceAll("\\$foreground", ThemeManager.theme.foreground);
		ThemeManager.css = ThemeManager.css.replaceAll("\\$background", ThemeManager.theme.background);
		for (int i = 0; i < 16; i++) {
			ThemeManager.css = ThemeManager.css
					.replaceAll("\\$c" + Integer.toHexString(i), ThemeManager.theme.color[i]);
		}
		try {
			File temp = File.createTempFile("current", ".tmp");
			temp.deleteOnExit();
			BufferedWriter out = new BufferedWriter(new FileWriter(temp));
			out.write(ThemeManager.css);
			out.close();
			ThemeManager.tmpUrl = "file:///" + temp.getAbsolutePath().replace(File.separator, "/");
		} catch (IOException ioException) {

		}
	}
}
