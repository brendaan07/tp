package seedu.tutor.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private static final String STARTUP_MESSAGE = "Type 'help' to get started\n"
            + "For full information, check out the full user guide "
            + "at https://ay2526s2-cs2103t-w12-3.github.io/tp/UserGuide.html";

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a {@code ResultDisplay} and shows the default startup hint.
     */
    public ResultDisplay() {
        super(FXML);
        setFeedbackToUser(STARTUP_MESSAGE);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
