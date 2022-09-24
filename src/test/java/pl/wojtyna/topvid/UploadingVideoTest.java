package pl.wojtyna.topvid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Uploading Video test")
class UploadingVideoTest {

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
        var video = new Video(sizeInMB);
        var videoUploader = new VideoUploader();

        // when
        var events = videoUploader.upload(video);

        // then
        boolean videoIsUploaded = events.hasOccurredEventOfType(VideoUploaded.class);
        assertThat(videoIsUploaded).isEqualTo(expectedResult);
    }

}
