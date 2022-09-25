package pl.wojtyna.topvid.upload.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.upload.domain.VideoUploader;
import pl.wojtyna.topvid.upload.domain.policies.UploadPolicies;
import pl.wojtyna.topvid.upload.infrastructure.InMemoryUserDetailsRepository;

@Configuration
public class TopVidUploadModuleSpringConfig {

    @Bean
    public VideoUploader videoUploader() {
        var uploadPolicies = UploadPolicies.instance();
        var uploadPolicy = uploadPolicies.maxSize(256)
                                         .and(uploadPolicies.userUploadLimits())
                                         .and(uploadPolicies.violentContent());
        return new VideoUploader(uploadPolicy,
                                 new InMemoryUserDetailsRepository());
    }
}
