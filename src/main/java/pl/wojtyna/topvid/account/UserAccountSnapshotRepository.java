package pl.wojtyna.topvid.account;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.MementoPattern;
import pl.wojtyna.topvid.patterns.RepositoryPattern;

import java.util.Optional;

@RepositoryPattern
@MementoPattern
public interface UserAccountSnapshotRepository {

    Optional<UserAccount.Snapshot> byId(@NonNull String snapshotId);

    void save(@NonNull UserAccount.Snapshot snapshot);
}
