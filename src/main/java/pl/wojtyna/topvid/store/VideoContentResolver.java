package pl.wojtyna.topvid.store;

import lombok.NonNull;

import java.util.Optional;

public interface VideoContentResolver {

    Optional<byte[]> contentOf(@NonNull String name);
}
