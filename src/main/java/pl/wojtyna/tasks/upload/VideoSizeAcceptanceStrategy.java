package pl.wojtyna.tasks.upload;

public class VideoSizeAcceptanceStrategy implements VideoAcceptanceStrategy {

    @Override
    public boolean isAcceptable(Video video, User user) {
        // logic abut max video size
        return false;
    }
}
