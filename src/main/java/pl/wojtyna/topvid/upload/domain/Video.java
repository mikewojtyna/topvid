package pl.wojtyna.topvid.upload.domain;

import pl.wojtyna.topvid.patterns.ValueObjectPattern;

@ValueObjectPattern
public record Video(int size, byte[] content) {

    public Video {
        if (size < 0) {
            throw new IllegalArgumentException("Video size cannot be negative");
        }
    }
}
