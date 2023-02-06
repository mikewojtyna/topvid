package pl.wojtyna.tasks.upload;

import pl.wojtyna.topvid.patterns.RepositoryPattern;

@RepositoryPattern
public interface VideoStorage {

    void store(Video video);
}
