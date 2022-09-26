package pl.wojtyna.topvid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Uploading video examples")
class UploadingVideoTest {

    // @formatter:off
    @DisplayName(
        """
         given any video of size less than 257 MB,
         when try to upload this video,
         then video is uploaded successfully
        """
    )
    @ParameterizedTest(name = "given size {0}; can video be uploaded? {1}")
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
    void uploadTest(int size, boolean canBeUploaded) {
        // given
        Video video = new Video(size, "any acceptable content".getBytes(StandardCharsets.UTF_8));
        Uploader george = new Uploader("George");
        UserAccountDetailsRepository accountDetailsRepository = new InMemoryAccountsDetailsRepository();
        UserAccountDetails accountDetails = new UserAccountDetails(george.username(),
                                                                   0,
                                                                   10);
        accountDetailsRepository.save(accountDetails);
        VideoUploaderSystem videoUploader = new VideoUploaderSystem(accountDetailsRepository);

        // when
        DomainEvents events = videoUploader.uploadBy(video, george);

        // then
        boolean videoWasUploaded = events.hasOccurredEventOfType(VideoUploaded.class);
        assertThat(videoWasUploaded).isEqualTo(canBeUploaded);
    }

    // @formatter:off
    @DisplayName(
        """
         given video with violent content,
         when video is about to be uploaded,
         then video is rejected
        """
    )
    // @formatter:on
    @Test
    void rejectViolentVideoTest() {
        // given
        Uploader george = new Uploader("George");
        UserAccountDetailsRepository accountDetailsRepository = new InMemoryAccountsDetailsRepository();
        UserAccountDetails accountDetails = new UserAccountDetails(george.username(),
                                                                   0,
                                                                   10);
        accountDetailsRepository.save(accountDetails);
        var videoUploader = new VideoUploaderSystem(accountDetailsRepository);
        var violentVideo = new Video(64, "some violent content".getBytes(StandardCharsets.UTF_8));

        // when
        var events = videoUploader.uploadBy(violentVideo, george);

        // then
        assertThat(events.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }

    // @formatter:off
    @DisplayName(
        """
         given George who is allowed to upload only up to 2 videos,
         when George tries to upload his third video,
         then the third video is rejected
        """
    )
    // @formatter:on
    @Test
    void uploadLimitTest() {
        // given
        Uploader george = new Uploader("George");
        UserAccountDetailsRepository accountDetailsRepository = new InMemoryAccountsDetailsRepository();
        int uploadedVideos = 0;
        int maxVideosToUpload = 2;
        UserAccountDetails accountDetails = new UserAccountDetails(george.username(),
                                                                   uploadedVideos,
                                                                   maxVideosToUpload);
        VideoUploaderSystem videoUploaderSystem = new VideoUploaderSystem(accountDetailsRepository);
        accountDetailsRepository.save(accountDetails);
        Video video = anySmallVideo();
        assertThat(videoUploaderSystem.uploadBy(video, george).hasOccurredEventOfType(VideoUploaded.class)).isTrue();
        assertThat(videoUploaderSystem.uploadBy(video, george).hasOccurredEventOfType(VideoUploaded.class)).isTrue();

        // when
        DomainEvents events = videoUploaderSystem.uploadBy(video, george);

        // then
        assertThat(events.hasOccurredEventOfType(VideoRejected.class)).isTrue();
        assertThat(events.hasOccurredEventOfType(VideoUploaded.class)).isFalse();
    }

    private Video anySmallVideo() {
        return new Video(32, "some content".getBytes(StandardCharsets.UTF_8));
    }

}
