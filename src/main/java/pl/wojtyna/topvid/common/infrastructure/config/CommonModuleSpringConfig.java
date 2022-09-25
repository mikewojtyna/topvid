package pl.wojtyna.topvid.common.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.common.infrastructure.InMemoryDomainEventPublisher;

@Configuration
public class CommonModuleSpringConfig {

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new InMemoryDomainEventPublisher();
    }
}
