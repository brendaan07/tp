package seedu.tutor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tutor.commons.exceptions.DataLoadingException;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.ReadOnlyUserPrefs;
import seedu.tutor.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TutorMapStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTutorMapFilePath();

    @Override
    Optional<ReadOnlyTutorMap> readTutorMap() throws DataLoadingException;

    @Override
    void saveTutorMap(ReadOnlyTutorMap tutorMap) throws IOException;

}
