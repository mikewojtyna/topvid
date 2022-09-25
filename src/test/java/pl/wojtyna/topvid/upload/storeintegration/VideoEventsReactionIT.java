package pl.wojtyna.topvid.upload.storeintegration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.store.VideoStore;
import pl.wojtyna.topvid.upload.domain.Uploader;
import pl.wojtyna.topvid.upload.domain.Video;
import pl.wojtyna.topvid.upload.ports.primary.VideoUploaderPort;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Reactions to video events [IT]")
@SpringBootTest
class VideoEventsReactionIT {

    @Autowired
    private VideoUploaderPort videoUploaderPort;
    @Autowired
    private VideoStore videoStore;

    // @formatter:off
    @DisplayName(
        """
         when video is uploaded,
         then it's stored in the video store
        """
    )
    // @formatter:on
    @Test
    void whenUploadedThenStoredTest() {
        // given
        var testFileContent = "test content".getBytes(StandardCharsets.UTF_8);
        var video = new Video("test-file.mp4", 128, testFileContent);

        // when
        videoUploaderPort.upload(video, new Uploader(new UserId("george")));

        // then
        assertThat(videoStore.contentOf("test-file.mp4")).hasValueSatisfying(bytes -> assertThat(bytes).containsExactly(
            testFileContent));
    }
}
