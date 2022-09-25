package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.SpecificationPattern;
import pl.wojtyna.topvid.patterns.StrategyPattern;

@StrategyPattern
@SpecificationPattern
public interface UploadPolicy {

    boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails);

    default UploadPolicy and(@NonNull UploadPolicy otherPolicy) {
        return (video, userDetails) -> isVideoAcceptable(video, userDetails) && otherPolicy.isVideoAcceptable(video,
                                                                                                              userDetails);
    }

    default UploadPolicy or(@NonNull UploadPolicy otherPolicy) {
        return (video, userDetails) -> isVideoAcceptable(video, userDetails) || otherPolicy.isVideoAcceptable(video,
                                                                                                              userDetails);
    }

    default UploadPolicy not() {
        return (video, userDetails) -> !isVideoAcceptable(video, userDetails);
    }
}
