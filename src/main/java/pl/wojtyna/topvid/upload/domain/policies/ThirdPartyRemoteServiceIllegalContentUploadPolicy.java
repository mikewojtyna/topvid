package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ProxyPattern;
import pl.wojtyna.topvid.upload.domain.UploadPolicy;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.Video;

@ProxyPattern
public class ThirdPartyRemoteServiceIllegalContentUploadPolicy implements UploadPolicy {

    @NonNull
    private final ThirdPartyIllegalContentDetector thirdPartyIllegalContentDetector;

    public ThirdPartyRemoteServiceIllegalContentUploadPolicy(@NonNull ThirdPartyIllegalContentDetector thirdPartyIllegalContentDetector) {
        this.thirdPartyIllegalContentDetector = thirdPartyIllegalContentDetector;
    }

    @Override
    public boolean isVideoAcceptable(@NonNull Video video, @NonNull UserDetails userDetails) {
        return thirdPartyIllegalContentDetector.isIllegal(video);
    }
}
