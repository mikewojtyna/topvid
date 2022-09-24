package pl.wojtyna.topvid;

public record Video(int size) {

    public Video {
        if (size < 0) {
            throw new IllegalArgumentException("Video size cannot be negative");
        }
    }
}
