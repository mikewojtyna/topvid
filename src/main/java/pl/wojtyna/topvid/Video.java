package pl.wojtyna.topvid;

public record Video(int size, byte[] content) {

    public Video {
        if (size < 0) {
            throw new IllegalArgumentException("Video size cannot be negative");
        }
    }
}
