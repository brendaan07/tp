package seedu.tutor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tutor.commons.exceptions.DataLoadingException;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.TutorMap;

/**
 * Represents a storage for {@link TutorMap}.
 */
public interface TutorMapStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTutorMapFilePath();

    /**
     * Returns TutorMap data as a {@link ReadOnlyTutorMap}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTutorMap> readTutorMap() throws DataLoadingException;

    /**
     * @see #getTutorMapFilePath()
     */
    Optional<ReadOnlyTutorMap> readTutorMap(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTutorMap} to the storage.
     * @param tutorMap cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTutorMap(ReadOnlyTutorMap tutorMap) throws IOException;

    /**
     * @see #saveTutorMap(ReadOnlyTutorMap)
     */
    void saveTutorMap(ReadOnlyTutorMap tutorMap, Path filePath) throws IOException;

}
