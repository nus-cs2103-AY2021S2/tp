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
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ThemeManager {
	private static String CSS_TEMPLATE;

	/* Current theme used by the application */
	private static Theme theme = null;

	/* Path of the current theme */
	private static String themePath = null;

	/* Path of the css file currently in use */
	private static String cssCacheUrl = null;

	/* Scene to be applied to */
	private static Scene scene = null;

	static {
		ThemeManager.theme = ThemeFactory.getDefaultTheme();
		InputStream templateStream = MainApp.class.getResourceAsStream("/view/Template.css");
		ThemeManager.CSS_TEMPLATE = new BufferedReader(new InputStreamReader(templateStream))
				.lines().collect(Collectors.joining("\n"));
		ThemeManager.updateCss();
	}

	/**
	 * Sets the scene for themes to be applied to.
	 * @param scene The scene for themes to be applied to.
	 */
	public static void setScene(Scene scene) {
		ThemeManager.scene = scene;
	}

	/**
	 * Gets the theme currently in use by the application.
	 * @return The theme currently in use by the application.
	 */
	public static Theme getTheme() {
		return ThemeManager.theme;
	}

	public static String getThemePath() {
		return themePath;
	}

	public static void setTheme(Theme newTheme, String themePath) {
		ThemeManager.theme = newTheme;
		ThemeManager.themePath = themePath;
		ThemeManager.updateCss();
	}

	private static boolean updateCss() {
		String cssString = ThemeManager.CSS_TEMPLATE;
		cssString = cssString
				.replaceAll("\\$foreground", ThemeManager.theme.foreground)
				.replaceAll("\\$background", ThemeManager.theme.background);
		for (int i = 0; i < 16; i++) {
			cssString = cssString
					.replaceAll("\\$c" + Integer.toHexString(i), ThemeManager.theme.color[i]);
		}
		try {
			File temp = File.createTempFile("current", ".tmp");
			temp.deleteOnExit();
			BufferedWriter out = new BufferedWriter(new FileWriter(temp));
			out.write(cssString);
			out.close();
			ThemeManager.cssCacheUrl = "file:///" + temp.getAbsolutePath().replace(File.separator, "/");
		} catch (IOException ioException) {
			return false;
		}
		return true;
	}

	public static void applyThemeToScene() {
		ThemeManager.scene.getStylesheets().clear();
		ThemeManager.scene.getStylesheets().add(ThemeManager.cssCacheUrl);
	}
}
