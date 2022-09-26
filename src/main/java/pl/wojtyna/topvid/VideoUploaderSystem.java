package pl.wojtyna.topvid;

import java.util.List;

public class VideoUploaderSystem {

    private final UserAccountDetailsRepository userAccountDetailsRepository;

    public VideoUploaderSystem(UserAccountDetailsRepository userAccountDetailsRepository) {
        this.userAccountDetailsRepository = userAccountDetailsRepository;
    }

    public DomainEvents uploadBy(Video video, Uploader uploader) {
        if (video.sizeInMB() > 256 || new String(video.bytes()).matches("^.*violen.*")) {
            return new DomainEvents(List.of(new VideoRejected()));
        }
        return userAccountDetailsRepository.loadBy(uploader.username()).map(userAccountDetails -> {
            if (uploaderCanUploadMoreVideos(uploader)) {
                var updatedUserAccountDetails = new UserAccountDetails(uploader.username(),
                                                                       userAccountDetails.uploadedVideos() + 1,
                                                                       userAccountDetails.maxVideosToUpload());
                userAccountDetailsRepository.save(updatedUserAccountDetails);
                return new DomainEvents(List.of(new VideoUploaded()));
            }
            return new DomainEvents(List.of(new VideoRejected()));
        }).orElse(new DomainEvents(List.of(new VideoRejected())));
    }

    private boolean uploaderCanUploadMoreVideos(Uploader uploader) {
        return userAccountDetailsRepository.loadBy(uploader.username())
                                           .filter(userAccountDetails ->
                                                       userAccountDetails.uploadedVideos() < userAccountDetails.maxVideosToUpload())
                                           .isPresent();
    }
}
