package pl.wojtyna.topvid.store;

import pl.wojtyna.topvid.common.TopVidException;

public class VideoContentResolverException extends TopVidException {


    public VideoContentResolverException(String message) {
        super(message);
    }

    public VideoContentResolverException(String message, Throwable cause) {
        super(message, cause);
    }
}
