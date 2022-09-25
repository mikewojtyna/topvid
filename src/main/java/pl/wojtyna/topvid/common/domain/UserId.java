package pl.wojtyna.topvid.common.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ValueObject;

@ValueObject
public record UserId(@NonNull String id) {
}
