package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEvents;
import pl.wojtyna.topvid.patterns.InversionOfControlPattern;
import pl.wojtyna.topvid.patterns.ServicePattern;

import java.util.List;

@ServicePattern
public class VideoUploader {

    @NonNull
    private final UploadPolicy uploadPolicy;
    @NonNull
    private final UserDetailsRepository userDetailsRepository;

    @InversionOfControlPattern
    public VideoUploader(@NonNull UploadPolicy uploadPolicy, @NonNull UserDetailsRepository userDetailsRepository) {
        this.uploadPolicy = uploadPolicy;
        this.userDetailsRepository = userDetailsRepository;
    }

    public DomainEvents upload(@NonNull Video video, @NonNull Uploader uploader) {
        return userDetailsRepository.by(uploader.id())
                                    .filter(userDetails -> uploadPolicy.isVideoAcceptable(video, userDetails))
                                    .map(userDetails -> {
                                        var updatedUserDetails = new UserDetails(uploader.id(),
                                                                                 userDetails.uploadedVideos() + 1,
                                                                                 userDetails.uploadSoftLimit());
                                        userDetailsRepository.save(updatedUserDetails);
                                        return new DomainEvents(List.of(new VideoUploaded()));
                                    }).orElse(new DomainEvents(List.of(new VideoRejected())));
    }
}
