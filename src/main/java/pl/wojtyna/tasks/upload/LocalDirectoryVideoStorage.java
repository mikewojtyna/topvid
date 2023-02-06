package pl.wojtyna.tasks.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalDirectoryVideoStorage implements VideoStorage {

    @Override
    public void store(Video video) {
        try {
            Files.writeString(Paths.get("/tmp", "video"), video.description());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
