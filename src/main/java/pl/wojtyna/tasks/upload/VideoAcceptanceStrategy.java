package pl.wojtyna.tasks.upload;

public interface VideoAcceptanceStrategy {

    boolean isAcceptable(Video video, User user);

    VideoAcceptanceStrategy and(VideoAcceptanceStrategy videoAcceptanceStrategy);

    VideoAcceptanceStrategy or(VideoAcceptanceStrategy videoAcceptanceStrategy);

    VideoAcceptanceStrategy not(VideoAcceptanceStrategy videoAcceptanceStrategy);
}
