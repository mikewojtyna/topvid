package pl.wojtyna.tasks.watching;

public class Hint {

    private String resolution;
    private Format format;
    private Offset offset;
    private Medium medium;

    public Hint(String resolution) {
        this.resolution = resolution;
    }

    public Hint(Format format) {
        this.format = format;
    }

    public Hint(Offset offset) {
        this.offset = offset;
    }

    public Hint(Medium medium) {
        this.medium = medium;
    }

    public Hint(String resolution, Format format) {
        this.resolution = resolution;
        this.format = format;
    }

    public Hint(String resolution, Offset offset) {
        this.resolution = resolution;
        this.offset = offset;
    }

    public Hint(String resolution, Format format, Offset offset, Medium medium) {
        this.resolution = resolution;
        this.format = format;
        this.offset = offset;
        this.medium = medium;
    }

    public class Builder {

        private String resolution;

        public Builder() {
        }

        Builder start() {
            return new Builder();
        }

        Builder withResolution(String resolution) {
            return this;
        }

        Builder withFormat(Format format) {
            return this;
        }

        Builder withOffset(Offset offset) {
            return this;
        }

        Builder withMedium(Medium medium) {
            return this;
        }

        Hint build() {
            return new Hint();
        }
    }
}
