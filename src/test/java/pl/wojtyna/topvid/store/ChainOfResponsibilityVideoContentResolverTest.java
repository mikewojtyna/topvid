package pl.wojtyna.topvid.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wojtyna.topvid.patterns.ChainOfResponsibilityPattern;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Show how we can use chain of responsibility when resolving video content")
@ChainOfResponsibilityPattern
class ChainOfResponsibilityVideoContentResolverTest {

    // @formatter:off
    @DisplayName(
        """
         given chain of resolvers: first resolver->second resolver->third resolver
         and all resolvers return some content,
         when resolve video content using chain of responsibility,
         then value of the first resolver is returned
        """
    )
    // @formatter:on
    @Test
    void chainOfResponsibility1Test() throws IOException {
        // given
        var videoName = "test-file.mp4";
        var thirdResolver = mock(VideoContentResolver.class);
        when(thirdResolver.contentOf(videoName)).thenReturn(Optional.of("3".getBytes(StandardCharsets.UTF_8)));
        var secondResolver = mock(VideoContentResolver.class);
        when(secondResolver.contentOf(videoName)).thenReturn(Optional.of("2".getBytes(StandardCharsets.UTF_8)));
        VideoContentResolver middleLink = new LinkVideoContentResolver(secondResolver, thirdResolver);
        var firstResolver = mock(VideoContentResolver.class);
        when(firstResolver.contentOf(videoName)).thenReturn(Optional.of("1".getBytes(StandardCharsets.UTF_8)));
        VideoContentResolver chainOfResolvers = new LinkVideoContentResolver(firstResolver, middleLink);

        // when
        var content = chainOfResolvers.contentOf(videoName).orElseThrow();

        // test
        assertThat(content).containsExactly("1".getBytes(StandardCharsets.UTF_8));
    }

    // @formatter:off
    @DisplayName(
        """
         given chain of resolvers: first resolver->second resolver->third resolver
         and only second and third resolvers return some content,
         when resolve video content using chain of responsibility,
         then value of the second resolver is returned
        """
    )
    // @formatter:on
    @Test
    void chainOfResponsibility2Test() throws IOException {
        // given
        var videoName = "test-file.mp4";
        var thirdResolver = mock(VideoContentResolver.class);
        when(thirdResolver.contentOf(videoName)).thenReturn(Optional.of("3".getBytes(StandardCharsets.UTF_8)));
        var secondResolver = mock(VideoContentResolver.class);
        when(secondResolver.contentOf(videoName)).thenReturn(Optional.of("2".getBytes(StandardCharsets.UTF_8)));
        VideoContentResolver middleLink = new LinkVideoContentResolver(secondResolver, thirdResolver);
        var firstResolver = mock(VideoContentResolver.class);
        when(firstResolver.contentOf(videoName)).thenReturn(Optional.empty());
        VideoContentResolver chainOfResolvers = new LinkVideoContentResolver(firstResolver, middleLink);

        // when
        var content = chainOfResolvers.contentOf(videoName).orElseThrow();

        // test
        assertThat(content).containsExactly("2".getBytes(StandardCharsets.UTF_8));
    }

    // @formatter:off
    @DisplayName(
        """
         given chain of resolvers: first resolver->second resolver->third resolver
         and only third resolver return some content,
         when resolve video content using chain of responsibility,
         then value of the third resolver is returned
        """
    )
    // @formatter:on
    @Test
    void chainOfResponsibility3Test() throws IOException {
        // given
        var videoName = "test-file.mp4";
        var thirdResolver = mock(VideoContentResolver.class);
        when(thirdResolver.contentOf(videoName)).thenReturn(Optional.of("3".getBytes(StandardCharsets.UTF_8)));
        var secondResolver = mock(VideoContentResolver.class);
        when(secondResolver.contentOf(videoName)).thenReturn(Optional.empty());
        VideoContentResolver middleLink = new LinkVideoContentResolver(secondResolver, thirdResolver);
        var firstResolver = mock(VideoContentResolver.class);
        when(firstResolver.contentOf(videoName)).thenReturn(Optional.empty());
        VideoContentResolver chainOfResolvers = new LinkVideoContentResolver(firstResolver, middleLink);

        // when
        var content = chainOfResolvers.contentOf(videoName).orElseThrow();

        // test
        assertThat(content).containsExactly("3".getBytes(StandardCharsets.UTF_8));
    }
}
