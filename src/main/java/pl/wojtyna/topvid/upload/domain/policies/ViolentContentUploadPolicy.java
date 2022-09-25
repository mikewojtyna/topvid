package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.upload.domain.UploadPolicy;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.Video;

import java.nio.charset.StandardCharsets;

class ViolentContentUploadPolicy implements UploadPolicy {

    @Override
    public boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails) {
        return !new String(video.content(), StandardCharsets.UTF_8).matches("^.*violen.*");
    }
}
