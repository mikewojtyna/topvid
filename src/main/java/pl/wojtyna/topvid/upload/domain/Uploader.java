package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record Uploader(@NonNull UserId id) {
}
