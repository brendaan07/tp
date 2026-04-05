package seedu.tutor.model.label;

import static java.util.Objects.requireNonNull;
import static seedu.tutor.commons.util.AppUtil.checkArgument;

/**
 * Represents a Label in the tutormap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLabelName(String)}
 */
public class Label {

    public static final String MESSAGE_CONSTRAINTS = "Label names (tag / subject) should be "
            + "alphanumeric and cannot consist only of whitespace";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String labelName;

    /**
     * Constructs a {@code Label}.
     *
     * @param labelName A valid label name.
     */
    public Label(String labelName) {
        requireNonNull(labelName);
        checkArgument(isValidLabelName(labelName), MESSAGE_CONSTRAINTS);
        this.labelName = labelName;
    }

    /**
     * Returns true if a given string is a valid label name.
     */
    public static boolean isValidLabelName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Label)) {
            return false;
        }

        Label otherLabel = (Label) other;
        return labelName.equals(otherLabel.labelName);
    }

    @Override
    public int hashCode() {
        return labelName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + labelName + ']';
    }

}
