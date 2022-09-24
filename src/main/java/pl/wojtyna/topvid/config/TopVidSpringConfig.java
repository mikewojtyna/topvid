package pl.wojtyna.topvid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.domain.VideoUploader;

@Configuration
public class TopVidSpringConfig {

    @Bean
    public VideoUploader videoUploader() {
        return new VideoUploader();
    }
}
