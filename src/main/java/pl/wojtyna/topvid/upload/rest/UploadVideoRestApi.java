package pl.wojtyna.topvid.upload.rest;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wojtyna.topvid.common.domain.UserId;
import pl.wojtyna.topvid.upload.domain.Uploader;
import pl.wojtyna.topvid.upload.domain.Video;
import pl.wojtyna.topvid.upload.domain.VideoUploader;

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
