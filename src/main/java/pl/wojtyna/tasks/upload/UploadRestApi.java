package pl.wojtyna.tasks.upload;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/videos")
public class UploadRestApi extends BaseApi {

    private static final int MAX_SIZE = 1024;
    private final StoreManager storeManager = StoreManager.getInstance();

    @PostMapping
    public void upload(@RequestBody Video video, HttpServletRequest request) {
        User user = currentUser(request);
        var executionContext = ExecutionContext.of(user);
        var videoStorage = storeManager.storageInstance(executionContext);
        if (video.size() > MAX_SIZE) {
            throw new RuntimeException("size is too big");
        }
        videoStorage.store(video);
    }
}
