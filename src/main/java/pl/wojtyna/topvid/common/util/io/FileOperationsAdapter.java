package pl.wojtyna.topvid.common.util.io;

import pl.wojtyna.topvid.common.util.io.legacy.LegacyFileOperations;
import pl.wojtyna.topvid.patterns.AdapterPattern;

import java.nio.file.Path;

@AdapterPattern
public class FileOperationsAdapter implements FileOperations {

    private final LegacyFileOperations fileOperations;

    public FileOperationsAdapter(LegacyFileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    @Override
    public void createFile(Path path) {
        fileOperations.createMultipleFiles(false, path.toFile());
    }

    @Override
    public void deleteFile(Path path) {
        fileOperations.deleteFileByPath(path.toString());
    }
}
