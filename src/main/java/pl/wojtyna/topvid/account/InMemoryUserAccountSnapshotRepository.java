package pl.wojtyna.topvid.account;

import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserAccountSnapshotRepository implements UserAccountSnapshotRepository {

    private final ConcurrentMap<String, UserAccount.Snapshot> map;

    public InMemoryUserAccountSnapshotRepository() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<UserAccount.Snapshot> byId(@NonNull String snapshotId) {
        return Optional.ofNullable(map.get(snapshotId));
    }

    @Override
    public void save(UserAccount.Snapshot snapshot) {
        map.put(snapshot.getId(), snapshot);
    }
}
