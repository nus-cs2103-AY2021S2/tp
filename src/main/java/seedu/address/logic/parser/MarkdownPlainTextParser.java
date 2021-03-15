package seedu.address.logic.parser;

import java.util.Scanner;

public class MarkdownPlainTextParser {
    private String plainText;

    /**
     * Parses markdown text from the user guide's "Features" section into plain text.
     * @param markdown  Text in markdown.
     * @param type      Type of text to be formatted. Can be title text, as indicated by "title",
     *                  or body text, as indicated by "info".
     * @return Plain text.
     */
    public String formatPlainText(String markdown, String type) {
        if (type == "title") {
            return markdown.substring(4);
        } else if (type == "info") {
            Scanner s = new Scanner(markdown);
            plainText = "";

            while (s.hasNext()) {
                String next = s.nextLine();
                handleLine(next);
            }

            return plainText;
        } else {
            return "error formatting";
        }
    }

    private void handleLine(String next) {
        if (next.startsWith("<div")) {
            formatDivStart(next);
        } else if (next.contains("</div>")) {
            formatDivEnd(next);
        } else if (!next.contains("![")) {
            plainText += next + "\n";
        }
    }

    private void formatDivEnd(String next) {
        String[] nextSplit = next.split("<");
        if (!nextSplit[0].equals("")) {
            plainText += nextSplit[0] + "\n";
        }
    }

    private String formatDivStart(String next) {
        String[] nextSplit = next.split("<|\\>");
        String divContent = nextSplit[2];

        if (divContent.startsWith(":bulb:")) {
            plainText += "\uD83D\uDCA1 " + divContent.substring(9, 13) + " ";
        } else if (divContent.startsWith(":exclamation:")) {
            plainText += "❗ " + divContent.substring(16, 24) + " ";
        } else {
            plainText += divContent + "\n";
        }
        return plainText;
    }
}
