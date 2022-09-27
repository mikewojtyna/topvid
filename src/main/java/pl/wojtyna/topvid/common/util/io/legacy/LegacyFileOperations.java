package pl.wojtyna.topvid.common.util.io.legacy;

import pl.wojtyna.topvid.patterns.AdapterPattern;

import java.io.File;

@AdapterPattern
public interface LegacyFileOperations {

    void createMultipleFiles(boolean overwrite, File... files);

    void deleteFileByPath(String file);
}
