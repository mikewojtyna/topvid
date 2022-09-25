package pl.wojtyna.topvid.watching;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ServicePattern;
import pl.wojtyna.topvid.store.VideoStore;

import java.util.Optional;

@ServicePattern
public class VideoWatcher {

    @NonNull
    private final VideoStore videoStore;

    public VideoWatcher(@NonNull VideoStore videoStore) {
        this.videoStore = videoStore;
    }

    public Optional<byte[]> download(String name) {

        return videoStore.contentOf(name);
    }
}
