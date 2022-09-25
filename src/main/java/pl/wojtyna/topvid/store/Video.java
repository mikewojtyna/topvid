package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;

public record Video(@NonNull String name, int size, @NonNull UserId author, byte[] content) {
}
