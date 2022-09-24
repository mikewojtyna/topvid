package pl.wojtyna.topvid.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.Strategy;

@Strategy
public interface UploadPolicy {

    boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails);
}
