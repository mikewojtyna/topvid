package pl.wojtyna.topvid.domain;

import lombok.NonNull;

import java.nio.charset.StandardCharsets;

public class DefaultUploadPolicy implements UploadPolicy {

    @Override
    public boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails) {
        return video.size() <= 256 && !new String(video.content(),
                                                  StandardCharsets.UTF_8).matches("^.*violen.*") && userDetails.uploadedVideos() < userDetails.uploadSoftLimit();
    }
}
