package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;

public class InfoCommand extends  Command{
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    @Override
    public CommandResult execute(Model model) {
        //test
        requireNonNull(model);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path addressBookFilePath = Paths.get("data", "moduleinfo.json");
            String json = Files.readAllLines(addressBookFilePath).get(0);
            Optional<mod[]> opt = JsonUtil.readJsonFile(addressBookFilePath, mod[].class);
            mod[] md = opt.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS);
        //
    }
}

class mod {
    public String module_code;
    public String moduleTitle;
    public String num_mc;
    public String avail_sems;
    public String[] prereqs;
    public String[] preclusions;
}