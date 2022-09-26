package pl.wojtyna.topvid.account;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.MementoPattern;
import pl.wojtyna.topvid.patterns.ServicePattern;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

@MementoPattern
@ServicePattern
public class UserAccountEditor {

    @NonNull
    private final UserAccountRepository userAccountRepository;
    @NonNull
    private final UserAccountSnapshotRepository userAccountSnapshotRepository;
    private final ConcurrentMap<UserId, ConcurrentLinkedDeque<String>> history;

    public UserAccountEditor(@NonNull UserAccountRepository userAccountRepository,
                             @NonNull UserAccountSnapshotRepository userAccountSnapshotRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountSnapshotRepository = userAccountSnapshotRepository;
        this.history = new ConcurrentHashMap<>();
    }

    public UserAccount createAccount(@NonNull String firstName,
                                     @NonNull String lastName,
                                     @NonNull LocalDate birthDate) {
        var userAccount = new UserAccount(new UserId(UUID.randomUUID().toString()), firstName, lastName, birthDate);
        userAccountRepository.save(userAccount);
        var snapshot = userAccount.createSnapshot();
        userAccountSnapshotRepository.save(snapshot);
        history.computeIfAbsent(userAccount.getId(), userId -> new ConcurrentLinkedDeque<>()).add(snapshot.getId());
        return userAccount;
    }

    public void updateFirstName(@NonNull UserId id, @NonNull String firstName) {
        userAccountRepository.byId(id).ifPresent(userAccount -> {
            var snapshot = userAccount.createSnapshot();
            userAccountSnapshotRepository.save(snapshot);
            history.computeIfAbsent(userAccount.getId(), userId -> new ConcurrentLinkedDeque<>()).add(snapshot.getId());
            userAccount.setFirstName(firstName);
            userAccountRepository.save(userAccount);
        });
    }

    public void undo(@NonNull UserId userId) {
        userAccountRepository.byId(userId).ifPresent(userAccount -> {
            var snapshots = history.get(userId);
            if (snapshots != null) {
                if (!snapshots.isEmpty()) {
                    var snapshotId = snapshots.peekLast();
                    userAccountSnapshotRepository.byId(snapshotId).ifPresent(snapshot -> {
                        userAccount.restore(snapshot);
                        userAccountRepository.save(userAccount);
                        snapshots.removeLast();
                    });
                }
            }
        });
    }
}
