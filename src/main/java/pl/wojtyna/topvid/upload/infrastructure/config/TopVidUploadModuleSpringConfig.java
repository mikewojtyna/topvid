package pl.wojtyna.topvid.upload.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.upload.domain.DefaultUploadPolicy;
import pl.wojtyna.topvid.upload.domain.VideoUploader;
import pl.wojtyna.topvid.upload.infrastructure.InMemoryUserDetailsRepository;

@Configuration
public class TopVidUploadModuleSpringConfig {

    @Bean
    public VideoUploader videoUploader() {
        return new VideoUploader(new DefaultUploadPolicy(), new InMemoryUserDetailsRepository());
    }
}
