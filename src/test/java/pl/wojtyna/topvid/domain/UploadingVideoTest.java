package pl.wojtyna.topvid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Uploading Video test")
class UploadingVideoTest {

    private VideoUploader videoUploader;

    @BeforeEach
    void setup() {
        this.videoUploader = new VideoUploader();
    }

    // @formatter:off
    @DisplayName(
        """
         only videos up to 256 MBs can be uploaded
        """
    )
    @ParameterizedTest(name = "given size: {0} MB; can video be uploaded? {1}")
    @CsvSource({
        "0, true",
        "1, true",
        "64, true",
        "128, true",
        "256, true",
        "257, false",
        "512, false",
        "1024, false"
    })
    // @formatter:on
    void uploadVideoTest(int sizeInMB, boolean expectedResult) {
        // given
        var video = videoOfSize(sizeInMB);
        var george = new Uploader(10);

        // when
        var events = videoUploader.upload(video, george);

        // then
        boolean videoIsUploaded = events.hasOccurredEventOfType(VideoUploaded.class);
        assertThat(videoIsUploaded).isEqualTo(expectedResult);
    }

    // @formatter:off
    @DisplayName(
        """
         given violent video,
         when video is about to be uploaded,
         then video is rejected
        """
    )
    // @formatter:on
    @Test
    void illegalContentTest() {
        // given
        byte[] content = "some violent video".getBytes(StandardCharsets.UTF_8);
        var video = new Video(64, content);
        var george = new Uploader(10);

        // when
        var domainEvents = videoUploader.upload(video, george);

        // then
        assertThat(domainEvents.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }

    // @formatter:off
    @DisplayName(
        """
         given George who can upload up to 2 videos,
         when George tries to upload the third video,
         then this video is rejected
        """
    )
    // @formatter:on
    @Test
    void uploadLimitTest() {
        // given
        var george = new Uploader(2);
        videoUploader.upload(videoOfSize(64), george);
        videoUploader.upload(videoOfSize(64), george);
        var thirdVideo = videoOfSize(64);

        // when
        var domainEvents = videoUploader.upload(thirdVideo, george);

        // then
        assertThat(domainEvents.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }

    // @formatter:off
    @DisplayName(
        """
         given George who can upload only 1 video,
         when George tries to upload the second video,
         then this video is rejected
        """
    )
    // @formatter:on
    @Test
    void uploadLimit1VideoTest() {
        // given
        var george = new Uploader(1);
        videoUploader.upload(videoOfSize(64), george);
        var thirdVideo = videoOfSize(64);

        // when
        var domainEvents = videoUploader.upload(thirdVideo, george);

        // then
        assertThat(domainEvents.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }

    private Video videoOfSize(int sizeInMB) {
        return new Video(sizeInMB, "any content".getBytes(StandardCharsets.UTF_8));
    }

}
