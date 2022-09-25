package pl.wojtyna.topvid.upload.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.common.domain.DomainEventListener;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.store.VideoStore;
import pl.wojtyna.topvid.upload.domain.UserDetailsRepository;
import pl.wojtyna.topvid.upload.domain.VideoUploader;
import pl.wojtyna.topvid.upload.domain.policies.UploadPolicies;
import pl.wojtyna.topvid.upload.infrastructure.InMemoryUserDetailsRepository;
import pl.wojtyna.topvid.upload.infrastructure.initializers.EventListenerSubscriptionsInitializer;
import pl.wojtyna.topvid.upload.infrastructure.initializers.UserDetailsInitializer;
import pl.wojtyna.topvid.upload.ports.primary.VideoUploaderPort;
import pl.wojtyna.topvid.upload.storeintegration.VideoUploadEventsListener;

import java.util.List;

@Configuration
public class TopVidUploadModuleSpringConfig {

    @Bean
    public UserDetailsRepository userDetailsRepository() {
        return new InMemoryUserDetailsRepository();
    }

    @Bean
    public UserDetailsInitializer userDetailsInitializer(UserDetailsRepository userDetailsRepository) {
        return new UserDetailsInitializer(userDetailsRepository);
    }

    @Bean
    public EventListenerSubscriptionsInitializer eventListenerSubscriptionsInitializer(DomainEventPublisher eventPublisher,
                                                                                       List<DomainEventListener> eventListeners) {
        return new EventListenerSubscriptionsInitializer(eventPublisher, eventListeners);
    }

    @Bean
    public VideoUploaderPort videoUploaderPort(VideoUploader videoUploader,
                                               DomainEventPublisher domainEventPublisher) {
        return new VideoUploaderPort(videoUploader, domainEventPublisher);
    }

    @Bean
    public VideoUploadEventsListener videoUploadEventsListener(VideoStore videoStore) {
        return new VideoUploadEventsListener(videoStore);
    }

    @Bean
    public VideoUploader videoUploader(UserDetailsRepository userDetailsRepository) {
        var uploadPolicies = UploadPolicies.instance();
        var uploadPolicy = uploadPolicies.maxSize(256)
                                         .and(uploadPolicies.userUploadLimits())
                                         .and(uploadPolicies.violentContent());
        return new VideoUploader(uploadPolicy,
                                 userDetailsRepository);
    }
}
