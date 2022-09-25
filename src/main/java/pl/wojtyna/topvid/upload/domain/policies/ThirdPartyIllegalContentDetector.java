package pl.wojtyna.topvid.upload.domain.policies;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ProxyPattern;
import pl.wojtyna.topvid.patterns.StrategyPattern;
import pl.wojtyna.topvid.upload.domain.Video;

@ProxyPattern
@StrategyPattern
public interface ThirdPartyIllegalContentDetector {

    boolean isIllegal(@NonNull Video video);
}
