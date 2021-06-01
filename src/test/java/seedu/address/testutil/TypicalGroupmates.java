package seedu.address.testutil;

import seedu.address.model.groupmate.Groupmate;

public class TypicalGroupmates {

    public static final Groupmate SYLPH = new GroupmateBuilder().withName("Sylphiette Greyrat")
            .withRoles("leader", "magician").build();

    public static final Groupmate ROXY = new GroupmateBuilder().withName("Roxy Migurdia")
            .withRoles("magician").build();

    public static final Groupmate ERIS = new GroupmateBuilder().withName("Eris Boreas Greyrat")
            .withRoles("member", "swordsman").build();

}
