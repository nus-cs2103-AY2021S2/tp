package seedu.address.logic.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

import java.util.logging.Logger;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    private final Logger logger = LogsCenter.getLogger(HelpCommand.class);

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public String helpMessage = "Commands available";

    @Override
    public CommandResult execute(Model model) {
        try {
            String projectDir = System.getProperty("user.dir");
//            BufferedReader reader = new BufferedReader(new FileReader("../../../../../../../../docs/UserGuide.md"));
            BufferedReader reader = new BufferedReader(new FileReader(projectDir + "/docs/UserGuide.md"));


            String currLine = reader.readLine();
            while (currLine != null && !currLine.equals("--------|------------------")) {
                currLine = reader.readLine();
            }

            currLine = reader.readLine();
            while (currLine != null) {
                logger.info("currLine: " + currLine);

                helpMessage += commandSummaryParser(currLine);
//                logger.info("help message now: " + HELP_MESSAGE);

                currLine = reader.readLine();
            }

            logger.info("help message now: \n" + helpMessage);


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

//        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        return new CommandResult(helpMessage, true, false);
    }

    private String commandSummaryParser(String info) {
        String[] separatedInfo = info.split("\\|");
//        logger.info("separatedInfo: " + Arrays.toString(separatedInfo));
        String[] commandName = separatedInfo[0].split("\\*");
//        logger.info("commandName[]: " + Arrays.toString(commandName));

        String[] commandDesc = separatedInfo[1].split("`");
//        logger.info("commandDesc[]: " + Arrays.toString(commandDesc));

//        logger.info("\n" + commandName[2] + ": " + commandDesc[1]);

        return "\n" + commandName[2] + ": " + commandDesc[1];
    }
}
