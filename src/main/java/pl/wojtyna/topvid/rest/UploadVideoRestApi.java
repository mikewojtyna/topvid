package pl.wojtyna.topvid.rest;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wojtyna.topvid.domain.Uploader;
import pl.wojtyna.topvid.domain.UserId;
import pl.wojtyna.topvid.domain.Video;
import pl.wojtyna.topvid.domain.VideoUploader;

import java.io.IOException;

@RestController
@RequestMapping("/api/v0/videos")
public class UploadVideoRestApi {

    @NonNull
    private final VideoUploader videoUploader;

    public UploadVideoRestApi(@NonNull VideoUploader videoUploader) {
        this.videoUploader = videoUploader;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("file") MultipartFile multipartFile,
                       @RequestParam("identity") String identity) throws IOException {
        videoUploader.upload(new Video(Math.toIntExact(multipartFile.getSize()), multipartFile.getBytes()),
                             new Uploader(new UserId(identity)));
    }
}
