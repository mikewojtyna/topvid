package pl.wojtyna.topvid.common.util.io;

import pl.wojtyna.topvid.patterns.AdapterPattern;

import java.nio.file.Path;

@AdapterPattern
public interface FileOperations {

    void createFile(Path path);

    void deleteFile(Path path);
}
