package pl.wojtyna.topvid.store;

import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryVideoStore implements VideoStore {

    @NonNull
    private final ConcurrentMap<String, byte[]> map;

    public InMemoryVideoStore() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void store(@NonNull Video video) {
        map.put(video.name(), video.content());
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        return Optional.ofNullable(map.get(name));
    }
}
