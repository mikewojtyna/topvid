package pl.wojtyna.topvid;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryAccountsDetailsRepository implements UserAccountDetailsRepository {

    private final ConcurrentMap<String, UserAccountDetails> map = new ConcurrentHashMap<>();

    @Override
    public void save(UserAccountDetails accountDetails) {
        map.put(accountDetails.username(), accountDetails);
    }

    @Override
    public Optional<UserAccountDetails> loadBy(String username) {
        return Optional.ofNullable(map.get(username));
    }
}
