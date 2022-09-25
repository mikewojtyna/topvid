package pl.wojtyna.topvid.upload.domain;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.RepositoryPattern;

import java.util.Optional;

@RepositoryPattern
public interface UserDetailsRepository {

    Optional<UserDetails> by(@NonNull UserId id);

    void save(@NonNull UserDetails userDetails);
}
