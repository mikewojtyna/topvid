package pl.wojtyna.topvid.store.resolvers;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ChainOfResponsibilityPattern;
import pl.wojtyna.topvid.store.VideoContentResolver;
import pl.wojtyna.topvid.store.VideoContentResolverException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@ChainOfResponsibilityPattern
public class RemoteHttpResourceVideoContentResolver implements VideoContentResolver {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    @NonNull
    private final String remoteResourceUri;

    public RemoteHttpResourceVideoContentResolver(@NonNull String remoteResourceUri) {
        this.remoteResourceUri = remoteResourceUri;
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        try {
            return Optional.of(httpClient.send(HttpRequest.newBuilder(new URI(remoteResourceUri)).GET().build(),
                                               HttpResponse.BodyHandlers.ofByteArray()).body());
        }
        catch (IOException | InterruptedException | URISyntaxException e) {
            throw new VideoContentResolverException("Failed to resolve content of %s".formatted(name), e);
        }
    }
}
