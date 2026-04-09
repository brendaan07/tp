package seedu.tutor.ui;

import java.util.Comparator;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tutor.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane relations;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        name.setWrapText(true);
        name.setMinWidth(0);
        name.maxWidthProperty().bind(Bindings.max(0,
                cardPane.widthProperty().subtract(id.widthProperty()).subtract(35)));

        configureFieldWrapping(phone);
        configureFieldWrapping(address);
        configureFieldWrapping(email);

        configureFlowPaneWrapping(tags);
        configureFlowPaneWrapping(subjects);
        configureFlowPaneWrapping(relations);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.labelName))
                .forEach(tag -> tags.getChildren().add(createWrappingChipLabel(tag.labelName)));

        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.labelName))
                .forEach(subject -> subjects.getChildren().add(createWrappingChipLabel(subject.labelName)));

        person.formatRelationNames().stream()
                .sorted(Comparator.comparing(String::toString))
                .forEach(relation -> relations.getChildren().add(createWrappingChipLabel(relation)));
    }

    private void configureFieldWrapping(Label label) {
        label.setWrapText(true);
        label.setMinWidth(0);
        label.maxWidthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
    }

    private void configureFlowPaneWrapping(FlowPane pane) {
        pane.setMinWidth(0);
        pane.prefWidthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
        pane.maxWidthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
        pane.prefWrapLengthProperty().bind(pane.widthProperty());
    }

    private Label createWrappingChipLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMinWidth(0);
        label.maxWidthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(45)));
        return label;
    }

}
