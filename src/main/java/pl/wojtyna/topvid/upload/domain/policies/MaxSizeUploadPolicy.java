package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.upload.domain.UploadPolicy;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.Video;

class MaxSizeUploadPolicy implements UploadPolicy {

    private final int maxSizeInMB;

    public MaxSizeUploadPolicy(int maxSizeInMB) {
        this.maxSizeInMB = maxSizeInMB;
    }

    @Override
    public boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails) {
        return video.size() <= maxSizeInMB;
    }
}
