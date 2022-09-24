package pl.wojtyna.topvid.domain;

import lombok.NonNull;

public record UserDetails(@NonNull UserId id, int uploadedVideos, int uploadSoftLimit) {
}
