package seedu.address.ui;

import seedu.address.model.group.Group;
import seedu.address.model.meeting.*;

import java.util.HashSet;

public class Test {
    public static final Meeting MEETING1 = new Meeting(new MeetingName("Hello"),
            new DateTime("2021-03-31 16:00"),
            new DateTime("2021-03-31 18:00"),
            new Priority("2"),
            new Description("nah"),
            new HashSet<>()
    );

    public static final Meeting MEETING2 = new Meeting(new MeetingName("Nobody"),
            new DateTime("2021-03-30 05:00"),
            new DateTime("2021-03-30 14:00"),
            new Priority("2"),
            new Description("nah"),
            new HashSet<>()
    );

    public static final Meeting MEETING3 = new Meeting(new MeetingName("CS3244"),
            new DateTime("2021-04-02 20:00"),
            new DateTime("2021-04-02 23:00"),
            new Priority("2"),
            new Description("nah"),
            new HashSet<>()
    );
}
