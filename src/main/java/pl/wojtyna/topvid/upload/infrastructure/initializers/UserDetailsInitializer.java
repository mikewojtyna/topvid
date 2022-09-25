package pl.wojtyna.topvid.upload.infrastructure.initializers;

import lombok.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.upload.domain.UserDetails;
import pl.wojtyna.topvid.upload.domain.UserDetailsRepository;

public class UserDetailsInitializer implements ApplicationRunner {

    @NonNull
    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsInitializer(@NonNull UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userDetailsRepository.save(new UserDetails(new UserId("george"), 0, Integer.MAX_VALUE));
        userDetailsRepository.save(new UserDetails(new UserId("martin"), 0, Integer.MAX_VALUE));
        userDetailsRepository.save(new UserDetails(new UserId("henry"), 0, Integer.MAX_VALUE));
    }
}
