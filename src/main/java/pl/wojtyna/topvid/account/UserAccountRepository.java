package pl.wojtyna.topvid.account;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.RepositoryPattern;

import java.util.Optional;

@RepositoryPattern
public interface UserAccountRepository {

    Optional<UserAccount> byId(@NonNull UserId userId);

    void save(@NonNull UserAccount userAccount);
}
