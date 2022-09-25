package pl.wojtyna.topvid.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ValueObject;

@ValueObject
public record UserDetails(@NonNull UserId id, int uploadedVideos, int uploadSoftLimit) {
}
