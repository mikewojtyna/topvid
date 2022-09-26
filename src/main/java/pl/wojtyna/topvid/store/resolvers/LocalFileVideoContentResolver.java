package pl.wojtyna.topvid.store.resolvers;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ChainOfResponsibilityPattern;
import pl.wojtyna.topvid.store.VideoContentResolver;
import pl.wojtyna.topvid.store.VideoContentResolverException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@ChainOfResponsibilityPattern
public class LocalFileVideoContentResolver implements VideoContentResolver {

    @NonNull
    private final Path directory;

    public LocalFileVideoContentResolver(@NonNull Path directory) {
        this.directory = directory;
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        var fullPath = directory.resolve(name);
        if (Files.exists(fullPath)) {
            try {
                return Optional.of(Files.readAllBytes(fullPath));
            }
            catch (IOException e) {
                throw new VideoContentResolverException("Failed to resolve local video file content.", e);
            }
        }
        return Optional.empty();
    }
}
