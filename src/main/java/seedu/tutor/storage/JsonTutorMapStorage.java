package seedu.tutor.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tutor.commons.core.LogsCenter;
import seedu.tutor.commons.exceptions.DataLoadingException;
import seedu.tutor.commons.exceptions.IllegalValueException;
import seedu.tutor.commons.util.FileUtil;
import seedu.tutor.commons.util.JsonUtil;
import seedu.tutor.model.ReadOnlyTutorMap;

/**
 * A class to access TutorMap data stored as a json file on the hard disk.
 */
public class JsonTutorMapStorage implements TutorMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTutorMapStorage.class);

    private Path filePath;

    public JsonTutorMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTutorMapFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTutorMap> readTutorMap() throws DataLoadingException {
        return readTutorMap(filePath);
    }

    /**
     * Similar to {@link #readTutorMap()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyTutorMap> readTutorMap(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTutorMap> jsonTutorMap = JsonUtil.readJsonFile(
                filePath, JsonSerializableTutorMap.class);
        if (!jsonTutorMap.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTutorMap.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTutorMap(ReadOnlyTutorMap tutorMap) throws IOException {
        saveTutorMap(tutorMap, filePath);
    }

    /**
     * Similar to {@link #saveTutorMap(ReadOnlyTutorMap)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTutorMap(ReadOnlyTutorMap tutorMap, Path filePath) throws IOException {
        requireNonNull(tutorMap);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTutorMap(tutorMap), filePath);
    }

}
