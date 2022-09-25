package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ProxyPattern;
import pl.wojtyna.topvid.upload.domain.Video;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

@ProxyPattern
public class CachingThirdPartyIllegalContentDetector implements ThirdPartyIllegalContentDetector {

    @NonNull
    private final ThirdPartyIllegalContentDetector thirdPartyIllegalContentDetector;
    @NonNull
    private final Map<String, Boolean> cache;

    public CachingThirdPartyIllegalContentDetector(@NonNull ThirdPartyIllegalContentDetector thirdPartyIllegalContentDetector) {
        this.thirdPartyIllegalContentDetector = thirdPartyIllegalContentDetector;
        cache = Collections.synchronizedMap(new WeakHashMap<>());
    }

    @Override
    public boolean isIllegal(@NonNull Video video) {
        return cache.computeIfAbsent(video.name(), name -> thirdPartyIllegalContentDetector.isIllegal(video));
    }
}
