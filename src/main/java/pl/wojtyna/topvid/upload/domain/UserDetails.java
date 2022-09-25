package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record UserDetails(@NonNull UserId id, int uploadedVideos, int uploadSoftLimit) {
}
