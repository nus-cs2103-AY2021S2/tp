package seedu.address.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class StreakCard extends UiPart<Region> {

    private static final String FXML = "StreakCard.fxml";

    public final Person person;

    @FXML
    public Label name;

    @FXML
    public Label streak;

    public StreakCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        streak.setText("100");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreakCard that = (StreakCard) o;
        return Objects.equals(person, that.person) && Objects.equals(name, that.name) && Objects.equals(streak,
                that.streak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, name, streak);
    }
}
