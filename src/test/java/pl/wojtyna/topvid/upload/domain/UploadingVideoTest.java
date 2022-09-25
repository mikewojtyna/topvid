package pl.wojtyna.topvid.upload.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.upload.domain.policies.UploadPolicies;
import pl.wojtyna.topvid.upload.infrastructure.InMemoryUserDetailsRepository;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Uploading Video test")
class UploadingVideoTest {

    private VideoUploader videoUploader;
    private UserDetailsRepository userDetailsRepository;

    @BeforeEach
    void setup() {
        userDetailsRepository = new InMemoryUserDetailsRepository();
        setLimits(george(), Integer.MAX_VALUE);
        var uploadPolicies = UploadPolicies.instance();
        var uploadPolicy = uploadPolicies.maxSize(256)
                                         .and(uploadPolicies.userUploadLimits())
                                         .and(uploadPolicies.violentContent());
        this.videoUploader = new VideoUploader(uploadPolicy, userDetailsRepository);
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
        var george = george();

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
        var george = george();

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
        var george = george();
        setLimits(george, 2);
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
        var george = george();
        setLimits(george, 1);
        videoUploader.upload(videoOfSize(64), george);
        var thirdVideo = videoOfSize(64);

        // when
        var domainEvents = videoUploader.upload(thirdVideo, george);

        // then
        assertThat(domainEvents.hasOccurredEventOfType(VideoRejected.class)).isTrue();
    }

    private void setLimits(Uploader uploader, int limits) {
        userDetailsRepository.save(new UserDetails(uploader.id(), 0, limits));
    }

    private Uploader george() {
        return new Uploader(new UserId("george"));
    }

    private Video videoOfSize(int sizeInMB) {
        return new Video(sizeInMB, "any content".getBytes(StandardCharsets.UTF_8));
    }

}
