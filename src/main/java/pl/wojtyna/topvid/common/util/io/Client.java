package pl.wojtyna.topvid.common.util.io;

import pl.wojtyna.topvid.patterns.AdapterPattern;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AdapterPattern
public class Client {

    private final FileOperations fileOperations;

    public Client(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    void runDemo() throws IOException {
        Path pathToFile = Paths.get("adapter-file-example.txt");
        fileOperations.createFile(pathToFile);
        try (var stream = Files.lines(pathToFile, StandardCharsets.UTF_8)) {
            stream.forEach(System.out::println);
        }
        fileOperations.deleteFile(pathToFile);
    }
}
