package pl.wojtyna.topvid.upload.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wojtyna.topvid.patterns.ProxyPattern;
import pl.wojtyna.topvid.upload.domain.policies.CachingThirdPartyIllegalContentDetector;
import pl.wojtyna.topvid.upload.domain.policies.ThirdPartyIllegalContentDetector;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@DisplayName("Caching third party illegal content service calls")
@ProxyPattern
class CachingThirdPartyIllegalContentDetectorTest {

    // @formatter:off
    @DisplayName(
        """
         given third party illegal content detector is wrapped with caching proxy
         and video is checked for illegal content once,
         when check again three times,
         then third party detector is not called again
        """
    )
    // @formatter:on
    @Test
    void useCacheTest() {
        // given
        var thirdPartyIllegalContentDetector = mock(ThirdPartyIllegalContentDetector.class);
        var video = new Video("test-file.mp4", 128, "test content".getBytes(StandardCharsets.UTF_8));
        when(thirdPartyIllegalContentDetector.isIllegal(video)).thenReturn(true);
        var cachingThirdPartyIllegalContentDetector = new CachingThirdPartyIllegalContentDetector(
            thirdPartyIllegalContentDetector);
        cachingThirdPartyIllegalContentDetector.isIllegal(video);

        // when
        cachingThirdPartyIllegalContentDetector.isIllegal(video);
        cachingThirdPartyIllegalContentDetector.isIllegal(video);
        cachingThirdPartyIllegalContentDetector.isIllegal(video);

        // then
        verify(thirdPartyIllegalContentDetector, times(1)).isIllegal(video);
    }
}
