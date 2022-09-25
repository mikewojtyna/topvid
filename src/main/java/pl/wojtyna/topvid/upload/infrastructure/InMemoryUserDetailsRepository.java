package pl.wojtyna.topvid.upload.infrastructure;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.UserDetailsRepository;

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
