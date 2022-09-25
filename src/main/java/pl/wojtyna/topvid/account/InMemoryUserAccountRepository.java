package pl.wojtyna.topvid.account;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserAccountRepository implements UserAccountRepository {

    private final ConcurrentMap<UserId, UserAccount> map;

    public InMemoryUserAccountRepository() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<UserAccount> byId(@NonNull UserId userId) {
        return Optional.ofNullable(map.get(userId));
    }

    @Override
    public void save(@NonNull UserAccount userAccount) {
        map.put(userAccount.getId(), userAccount);
    }
}
