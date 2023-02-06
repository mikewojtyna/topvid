package pl.wojtyna.tasks.upload;

public class IllegalContentAcceptanceStrategy implements VideoAcceptanceStrategy {

    @Override
    public boolean isAcceptable(Video video, User user) {
        return !containsIllegalContent(video);
    }

    private boolean containsIllegalContent(Video video) {
        // some very specific logic just for this illegal content
        return false;
    }
}
