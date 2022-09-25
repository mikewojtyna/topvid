package pl.wojtyna.topvid.upload.adapters.primary;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.patterns.PrimaryAdapterPattern;
import pl.wojtyna.topvid.upload.domain.Uploader;
import pl.wojtyna.topvid.upload.domain.Video;
import pl.wojtyna.topvid.upload.ports.primary.VideoUploaderPort;

import java.io.IOException;

@RestController
@PrimaryAdapterPattern(port = VideoUploaderPort.class)
@RequestMapping("/api/v0/videos")
public class UploadVideoRestApi {

    @NonNull
    private final VideoUploaderPort videoUploaderPort;

    public UploadVideoRestApi(@NonNull VideoUploaderPort videoUploaderPort) {
        this.videoUploaderPort = videoUploaderPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("file") MultipartFile multipartFile,
                       @RequestParam("identity") String identity) throws IOException {
        videoUploaderPort.upload(new Video(multipartFile.getName(),
                                           Math.toIntExact(multipartFile.getSize()),
                                           multipartFile.getBytes()), new Uploader(new UserId(identity)));
    }
}
