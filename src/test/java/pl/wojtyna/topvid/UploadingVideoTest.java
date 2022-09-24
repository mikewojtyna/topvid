package pl.wojtyna.topvid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Uploading Video test")
class UploadingVideoTest {

    // @formatter:off
    @DisplayName(
        """
         any video can be uploaded
        """
    )
    // @formatter:on
    @Test
    void uploadAnyVideoTest() {
        // given
        var video = new Video(64);
        var videoUploader = new VideoUploader();

        // when
        var events = videoUploader.upload(video);

        // then
        boolean videoIsUploaded = events.hasOccurredEventOfType(VideoUploaded.class);
        assertThat(videoIsUploaded).isTrue();
    }

    // @formatter:off
    @DisplayName(
        """
         cannot upload video bigger than 256 MB
        """
    )
    // @formatter:on
    @Test
    void uploadBigVideoTest() {
        // given
        int size = 257;
        var video = new Video(size);
        var videoUploader = new VideoUploader();

        // when
        var events = videoUploader.upload(video);

        // then
        assertThat(events.hasOccurredEventOfType(VideoAccepted.class)).isFalse();
        assertThat(events.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }
}
