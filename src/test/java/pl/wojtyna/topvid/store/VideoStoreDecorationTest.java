package pl.wojtyna.topvid.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.DecoratorPattern;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Decorating video store")
@DecoratorPattern
class VideoStoreDecorationTest {

    // @formatter:off
    @DisplayName(
        """
         given in-memory video store decorated with event publishing and logging,
         when store video,
         then video is logged, stored and event is published
        """
    )
    // @formatter:on
    @Test
    void decorationTest() {
        // given
        var eventPublisher = mock(DomainEventPublisher.class);
        VideoStore inMemoryVideoStore = new InMemoryVideoStore();
        VideoStore eventPublishingVideoStore = new EventPublishingVideoStore(inMemoryVideoStore, eventPublisher);
        VideoStore loggingVideoStore = new LoggingVideoStore(eventPublishingVideoStore);

        // when
        loggingVideoStore.store(new Video("test-file.mp4", 128, new UserId("george"), "content".getBytes(
            StandardCharsets.UTF_8)));

        // then
        verify(eventPublisher).publish(any(VideoStored.class));
        // we could also use mock for logger, but you get the point, so let's just manually check the logging
    }
}
