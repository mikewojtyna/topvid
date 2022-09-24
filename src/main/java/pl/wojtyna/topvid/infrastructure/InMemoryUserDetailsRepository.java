package pl.wojtyna.topvid.infrastructure;

import lombok.NonNull;
import pl.wojtyna.topvid.domain.UserDetails;
import pl.wojtyna.topvid.domain.UserDetailsRepository;
import pl.wojtyna.topvid.domain.UserId;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryUserDetailsRepository implements UserDetailsRepository {

    private final ConcurrentMap<UserId, UserDetails> map;

    public InMemoryUserDetailsRepository() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<UserDetails> by(@NonNull UserId id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void save(@NonNull UserDetails userDetails) {
        map.put(userDetails.id(), userDetails);
    }
}
