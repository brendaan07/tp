package seedu.tutor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tutor.commons.core.LogsCenter;
import seedu.tutor.commons.exceptions.DataLoadingException;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.ReadOnlyUserPrefs;
import seedu.tutor.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TutorMapStorage tutorMapStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TutorMapStorage tutorMapStorage, UserPrefsStorage userPrefsStorage) {
        this.tutorMapStorage = tutorMapStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getTutorMapFilePath() {
        return tutorMapStorage.getTutorMapFilePath();
    }

    @Override
    public Optional<ReadOnlyTutorMap> readTutorMap() throws DataLoadingException {
        return readTutorMap(tutorMapStorage.getTutorMapFilePath());
    }

    @Override
    public Optional<ReadOnlyTutorMap> readTutorMap(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tutorMapStorage.readTutorMap(filePath);
    }

    @Override
    public void saveTutorMap(ReadOnlyTutorMap tutorMap) throws IOException {
        saveTutorMap(tutorMap, tutorMapStorage.getTutorMapFilePath());
    }

    @Override
    public void saveTutorMap(ReadOnlyTutorMap tutorMap, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tutorMapStorage.saveTutorMap(tutorMap, filePath);
    }

}
