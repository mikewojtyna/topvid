package pl.wojtyna.tasks.upload;

public interface StoreManager {

    public static StoreManager getInstance() {
        return null;
    }

    VideoStorage storageInstance(ExecutionContext executionContext);
}
