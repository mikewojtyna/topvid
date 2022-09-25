package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.upload.domain.UploadPolicy;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.Video;

class UserLimitsUploadPolicy implements UploadPolicy {

    @Override
    public boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails) {
        return userDetails.uploadedVideos() < userDetails.uploadSoftLimit();
    }
}
