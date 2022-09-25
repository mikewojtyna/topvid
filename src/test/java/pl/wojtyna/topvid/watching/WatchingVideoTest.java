package pl.wojtyna.topvid.watching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.store.InMemoryVideoStore;
import pl.wojtyna.topvid.store.Video;
import pl.wojtyna.topvid.store.VideoStore;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Watching video")
class WatchingVideoTest {

    // @formatter:off
    @DisplayName(
        """
         given stored video,
         then it can be downloaded
        """
    )
    // @formatter:on
    @Test
    void watchVideoTest() {
        // given
        VideoStore videoStore = new InMemoryVideoStore();
        byte[] expectedContent = "full content".getBytes(StandardCharsets.UTF_8);
        var video = new Video("test-file.mp4",
                              128,
                              new UserId("george"),
                              expectedContent);
        videoStore.store(video);
        var videoWatcher = new VideoWatcher(videoStore);

        // when
        byte[] fullContent = videoWatcher.download("test-file.mp4").orElseThrow();

        // then
        assertThat(fullContent).containsExactly(expectedContent);
    }
}
