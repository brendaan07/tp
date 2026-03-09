package seedu.tutor.model;

import java.nio.file.Path;

import seedu.tutor.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTutorMapFilePath();

}
