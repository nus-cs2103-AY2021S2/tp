package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private String helpMessage = "";
    private String specifiedCommand;

    private final Logger logger = LogsCenter.getLogger(HelpCommand.class);

    public HelpCommand() {}

    public HelpCommand(String specifiedCommand) {
        requireNonNull(specifiedCommand);
        this.specifiedCommand = specifiedCommand;
    }

    @Override
    public CommandResult execute(Model model) {
        if (specifiedCommand != null) {
            return executeSpecific(specifiedCommand);
        } else {
            return executeNonSpecific();
        }

    }

    private CommandResult executeSpecific(String specifiedCommand) {
        // logger.info("specifiedCommand: " + specifiedCommand);
        // if (specifiedCommand != "find") {
        //     return null;
        // }

        String plainCommandTitle = "";
        String plainCommandInfo = "";

        try {
            String projectDir = System.getProperty("user.dir");
            BufferedReader reader = new BufferedReader(new FileReader(projectDir + "/docs/UserGuide.md"));


            String currLine = reader.readLine();
            while (currLine != null && !currLine.equals("## Features")) {
                currLine = reader.readLine();
            }

            // currLine = "## Features" at this pt

            currLine = reader.readLine();
            while (currLine != null) {
                if (currLine.startsWith("###")) { // is a subheading aka start of command explanation
                    logger.info(currLine);
                    String[] splitSubheading = currLine.split("`");
                    logger.info(Arrays.toString(splitSubheading));
                    logger.info("splitSubheading[0]: " + splitSubheading[0]);
                    logger.info("splitSubheading[1]: " + splitSubheading[1]);
                    if (!splitSubheading[1].equals(specifiedCommand)) { // not the command we want: keep looping
                        currLine = reader.readLine();
                        continue;
                    }

                    break; // is the command we want: stop looping
                }
                // logger.info("currLine: " + currLine);

                currLine = reader.readLine();
            }

            String commandTitle = currLine;
            String commandInfo = "";

            currLine = reader.readLine();
            while (!currLine.startsWith("###") && !currLine.startsWith("---")) {
                commandInfo += "\n" + currLine;
                currLine = reader.readLine();
            }

            plainCommandTitle = formatPlainText(commandTitle, "title");
            plainCommandInfo = formatPlainText(commandInfo, "info");

            // logger.info("help message now: \n" + helpMessage);


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

        // return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        return new CommandResult(SHOWING_HELP_MESSAGE, plainCommandInfo, true, false);
    }

    private String formatPlainText(String markdown, String type) {
        logger.info("command title/info:\n" + markdown);
        if (type == "title") {
            return markdown.substring(4);
        } else if (type == "info") {
            Scanner s = new Scanner(markdown);
            String plainText = "";

            while (s.hasNext()) {
                String next = s.nextLine();
                if (next.startsWith("<div")) {
                    String[] nextSplit = next.split("<|\\>");
                    String divContent = nextSplit[2];
                    plainText += "\n" + divContent.substring(7);
                } else if (next.contains("</div>")) {
                    String[] nextSplit = next.split("<");
                    if (!nextSplit[0].equals("")) {
                        plainText += "\n" + next;
                    }
                } else if (!next.startsWith("![")){
                    plainText += "\n" + next;
                }

                // next = s.nextLine();
            }

            return plainText;
        } else {
            return "error formatting";
        }
    }

    private CommandResult executeNonSpecific() {
        try {
            String projectDir = System.getProperty("user.dir");
            BufferedReader reader = new BufferedReader(new FileReader(projectDir + "/docs/UserGuide.md"));


            String currLine = reader.readLine();
            while (currLine != null && !currLine.equals("--------|------------------")) {
                currLine = reader.readLine();
            }

            currLine = reader.readLine();
            while (currLine != null) {
                // logger.info("currLine: " + currLine);

                helpMessage += commandSummaryParser(currLine);

                currLine = reader.readLine();
            }

            // logger.info("help message now: \n" + helpMessage);


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

        // return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        return new CommandResult(SHOWING_HELP_MESSAGE, helpMessage, true, false);
    }

    private String commandSummaryParser(String info) {
        String[] separatedInfo = info.split("\\|");
        // logger.info("separatedInfo: " + Arrays.toString(separatedInfo));
        String[] commandName = separatedInfo[0].split("\\*");
        // logger.info("commandName[]: " + Arrays.toString(commandName));

        String[] commandDesc = separatedInfo[1].split("`");
        // logger.info("commandDesc[]: " + Arrays.toString(commandDesc));

        // logger.info("\n" + commandName[2] + ": " + commandDesc[1]);

        return commandName[2] + ": " + commandDesc[1] + "\n";
    }
}
