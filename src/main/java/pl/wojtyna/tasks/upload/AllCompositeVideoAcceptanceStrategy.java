package pl.wojtyna.tasks.upload;

import java.util.List;

public class AllCompositeVideoAcceptanceStrategy implements VideoAcceptanceStrategy {

    private List<VideoAcceptanceStrategy> acceptanceStrategies;

    @Override
    public boolean isAcceptable(Video video, User user) {
        return !acceptanceStrategies.stream()
                                    .anyMatch(videoAcceptanceStrategy -> !videoAcceptanceStrategy.isAcceptable(video,
                                                                                                               user));
    }
}
