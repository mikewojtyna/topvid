package pl.wojtyna.tasks.upload;

import java.util.Optional;

public interface UserIdentityService {

    Optional<User> identityOf(Credentials credentials);
}
