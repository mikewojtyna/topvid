package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository {

    Optional<UserDetails> by(@NonNull UserId id);

    void save(@NonNull UserDetails userDetails);
}
