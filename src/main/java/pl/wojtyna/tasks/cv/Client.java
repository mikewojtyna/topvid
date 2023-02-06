package pl.wojtyna.tasks.cv;

import java.nio.file.Path;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        JsonCvBuilder jsonBuilder = new JsonCvBuilder();
        CvParser cvParser = new CvParser(List.of(jsonBuilder));
        cvParser.parse(Path.of("/tmp", "cv.txt"));

        System.out.println(jsonBuilder.getJsonResult());
    }
}
