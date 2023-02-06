package pl.wojtyna.tasks.watching;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/videos/{id}/content")
public class WatchingRestApi {

    public byte[] watch(Hint hint) {
        throw new UnsupportedOperationException("Implement this method");
    }
}
