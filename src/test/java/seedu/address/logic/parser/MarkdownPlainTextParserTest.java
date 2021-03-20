package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MarkdownPlainTextParserTest {
    private MarkdownPlainTextParser parser = new MarkdownPlainTextParser();

    @Test
    void formatTitleText_success() {
        String actualPlainText = parser.formatPlainText("### This is a title", "title");
        String expectedPlainText = "This is a title";

        assertEquals(expectedPlainText, actualPlainText);
    }

    @Test
    void formatLine_success() {
        String actualPlainText = parser.formatPlainText("Hello, this is a sentence.", "info");
        String expectedPlainText = "Hello, this is a sentence.\n";

        assertEquals(expectedPlainText, actualPlainText);
    }

    @Test
    void formatTipDiv_success() {
        String actualPlainText = parser.formatPlainText(
                "<div markdown=\"span\" class=\"alert alert-primary\">:bulb: **Tip:**\n"
                        + "This is a tip.\n" + "</div>", "info");
        String expectedPlainText = "\uD83D\uDCA1 Tip: This is a tip.\n";

        assertEquals(expectedPlainText, actualPlainText);
    }

    @Test
    void formatWarningDiv_success() {
        String actualPlainText = parser.formatPlainText(
                "<div markdown=\"span\" class=\"alert alert-warning\">:exclamation: **Caution:**\n"
                        + "This is a warning.\n</div>", "info");
        String expectedPlainText = "❗ Caution: This is a warning.\n";

        assertEquals(expectedPlainText, actualPlainText);
    }
}
