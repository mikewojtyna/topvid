package pl.wojtyna.topvid.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoStoreModuleSpringConfig {

    @Bean
    public VideoStore videoStore() {
        return new InMemoryVideoStore();
    }
}
