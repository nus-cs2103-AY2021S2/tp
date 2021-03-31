package dog.pawbook.logic.parser;

import java.time.LocalDate;

import dog.pawbook.logic.commands.ScheduleCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    @Override
    public ScheduleCommand parse(String userInput) throws ParseException {
        if (userInput.isBlank()) {
            return new ScheduleCommand(LocalDate.now());
        }

        LocalDate localDate = ParserUtil.parseDate(userInput);
        return new ScheduleCommand(localDate);
    }
}
