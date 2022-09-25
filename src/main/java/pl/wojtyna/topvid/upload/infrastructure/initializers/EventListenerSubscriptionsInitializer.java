package pl.wojtyna.topvid.upload.infrastructure.initializers;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.wojtyna.topvid.common.domain.DomainEventListener;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;

import java.util.List;

public class EventListenerSubscriptionsInitializer implements ApplicationRunner {


    private final DomainEventPublisher domainEventPublisher;
    private final List<DomainEventListener> eventListeners;

    public EventListenerSubscriptionsInitializer(DomainEventPublisher domainEventPublisher,
                                                 List<DomainEventListener> eventListeners) {
        this.domainEventPublisher = domainEventPublisher;
        this.eventListeners = eventListeners;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventListeners.forEach(domainEventPublisher::register);
    }
}
