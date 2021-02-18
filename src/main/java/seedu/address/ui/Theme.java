package seedu.address.ui;

import java.io.Serializable;

public class Theme implements Serializable {
	public final String foreground;
	public final String background;
	public final String[] color;

	public Theme() {
		foreground = "";
		background = "";
		color = null;
	}

	public Theme(String foreground, String background, String[] color) {
		this.foreground = foreground;
		this.background = background;
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Theme)) {
			return false;
		}
		Theme theme = (Theme) o;
		if (!this.foreground.equals(theme.foreground)) {
			return false;
		}
		if (!this.background.equals(theme.background)) {
			return false;
		}
		for (int i = 0; i < 16; i++) {
			if (!this.color[i].equals(theme.color[i])) {
				return false;
			}
		}
		return true;
	}
}
