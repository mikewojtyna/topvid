package pl.wojtyna.topvid.common.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record UserId(@NonNull String id) {
}
