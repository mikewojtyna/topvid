package pl.wojtyna.topvid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.domain.DefaultUploadPolicy;
import pl.wojtyna.topvid.domain.VideoUploader;
import pl.wojtyna.topvid.infrastructure.InMemoryUserDetailsRepository;

@Configuration
public class TopVidSpringConfig {

    @Bean
    public VideoUploader videoUploader() {
        return new VideoUploader(new DefaultUploadPolicy(), new InMemoryUserDetailsRepository());
    }
}
