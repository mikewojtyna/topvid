package pl.wojtyna.topvid;

import java.util.Optional;

public interface UserAccountDetailsRepository {

    void save(UserAccountDetails accountDetails);

    Optional<UserAccountDetails> loadBy(String username);
}
