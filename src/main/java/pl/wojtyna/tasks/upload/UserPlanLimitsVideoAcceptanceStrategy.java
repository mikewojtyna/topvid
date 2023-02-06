package pl.wojtyna.tasks.upload;

public class UserPlanLimitsVideoAcceptanceStrategy implements VideoAcceptanceStrategy {

    private UserPlanProvider planProvider;

    @Override
    public boolean isAcceptable(Video video, User user) {
        return planProvider.limitsOf(user).isExceededAfterAdding(video);
    }
}
