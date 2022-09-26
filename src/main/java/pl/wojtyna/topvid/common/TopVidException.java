package pl.wojtyna.topvid.common;

public class TopVidException extends RuntimeException {

    public TopVidException(String message) {
        super(message);
    }

    public TopVidException(String message, Throwable cause) {
        super(message, cause);
    }
}
